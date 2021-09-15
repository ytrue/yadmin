package com.ytrue.yadmin.document;

import com.github.xiaoymin.knife4j.spring.filter.ProductionSecurityFilter;
import com.github.xiaoymin.knife4j.spring.filter.SecurityBasicAuthFilter;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ytrue
 * @date 2021/7/5 09:11
 * @description 配置类
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2WebMvc
@ConditionalOnProperty(name = "ytrue.document.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    @Autowired
    SwaggerProperties swaggerProperties;

    private BeanFactory beanFactory;


    /**
     * 配置是否是生成模式
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean myProductionSecurityFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new ProductionSecurityFilter());
        Map<String, String> initParams = new HashMap<>();
        //这里取是否开启文档
        initParams.put("production", String.valueOf(swaggerProperties.getProduction()));
        registration.setInitParameters(initParams);
        registration.addUrlPatterns("/*");
        registration.setName("productionSecurityFilter");
        return registration;
    }

    /**
     * 配置权
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean mySecurityBasicAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new SecurityBasicAuthFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("enableBasicAuth", String.valueOf(swaggerProperties.getBasic().getEnable()));
        initParams.put("userName", swaggerProperties.getBasic().getUsername());
        initParams.put("password", swaggerProperties.getBasic().getPassword());
        registration.setInitParameters(initParams);

        registration.addUrlPatterns("/*");
        registration.setName("securityBasicAuthFilter");
        return registration;
    }


    @Bean
    @ConditionalOnMissingBean
    public List<Docket> createRestApi() {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        List<Docket> docketList = new LinkedList<>();

        // 没有分组
        if (swaggerProperties.getDocket().isEmpty()) {
            Docket docket = createDocket(swaggerProperties);
            configurableBeanFactory.registerSingleton(swaggerProperties.getTitle(), docket);
            docketList.add(docket);
            return docketList;
        }

        // 分组创建
        for (String groupName : swaggerProperties.getDocket().keySet()) {
            SwaggerProperties.DocketInfo docketInfo = swaggerProperties.getDocket().get(groupName);

            ApiInfo apiInfo = new ApiInfoBuilder()
                    .title(docketInfo.getTitle().isEmpty() ? swaggerProperties.getTitle() : docketInfo.getTitle())
                    .description(docketInfo.getDescription().isEmpty() ? swaggerProperties.getDescription() : docketInfo.getDescription())
                    .version(docketInfo.getVersion().isEmpty() ? swaggerProperties.getVersion() : docketInfo.getVersion())
                    .license(docketInfo.getLicense().isEmpty() ? swaggerProperties.getLicense() : docketInfo.getLicense())
                    .licenseUrl(docketInfo.getLicenseUrl().isEmpty() ? swaggerProperties.getLicenseUrl() : docketInfo.getLicenseUrl())
                    .contact(
                            new Contact(
                                    docketInfo.getContact().getName().isEmpty() ? swaggerProperties.getContact().getName() : docketInfo.getContact().getName(),
                                    docketInfo.getContact().getUrl().isEmpty() ? swaggerProperties.getContact().getUrl() : docketInfo.getContact().getUrl(),
                                    docketInfo.getContact().getEmail().isEmpty() ? swaggerProperties.getContact().getEmail() : docketInfo.getContact().getEmail()
                            )
                    )
                    .termsOfServiceUrl(docketInfo.getTermsOfServiceUrl().isEmpty() ? swaggerProperties.getTermsOfServiceUrl() : docketInfo.getTermsOfServiceUrl())
                    .build();

            // base-path处理 这个暂不实现
            // 当没有配置任何path的时候，解析/**
//            if (docketInfo.getBasePath().isEmpty()) {
//                docketInfo.getBasePath().add("/**");
//            }
//            List<Predicate<String>> basePath = new ArrayList<>(docketInfo.getBasePath().size());
//            for (String path : docketInfo.getBasePath()) {
//                basePath.add(PathSelectors.ant(path));
//            }
//
//            // exclude-path处理
//            List<Predicate<String>> excludePath = new ArrayList<>(docketInfo.getExcludePath().size());
//            for (String path : docketInfo.getExcludePath()) {
//                excludePath.add(PathSelectors.ant(path));
//            }


            List<Parameter> parameters = assemblyGlobalOperationParameters(swaggerProperties.getGlobalOperationParameters(),
                    docketInfo.getGlobalOperationParameters());
            Docket docket = new Docket(DocumentationType.SWAGGER_2)
                    .host(swaggerProperties.getHost())
                    .apiInfo(apiInfo)
                    .globalOperationParameters(parameters)
                    .groupName(docketInfo.getGroup())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(docketInfo.getBasePackage()))
                    //加了ApiOperation注解的类，才生成接口文档
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .paths(PathSelectors.any())
//                    .paths(
//                            Predicates.and(
//                                    Predicates.not(Predicates.or(excludePath)),
//                                    Predicates.or(basePath)
//                            )
//                    )
                    .build()
                    .securitySchemes(securitySchemes())
                    .securityContexts(securityContexts())
                    //全局响应参数
                    .globalResponseMessage(RequestMethod.GET, getResponseMessages())
                    .globalResponseMessage(RequestMethod.POST, getResponseMessages())
                    .globalResponseMessage(RequestMethod.PUT, getResponseMessages())
                    .globalResponseMessage(RequestMethod.DELETE, getResponseMessages());

            configurableBeanFactory.registerSingleton(groupName, docket);
            docketList.add(docket);
        }
        return docketList;
    }

    /**
     * 创建 Docket对象
     *
     * @param swaggerProperties swagger配置
     * @return Docket
     */
    private Docket createDocket(SwaggerProperties swaggerProperties) {
        //API 基础信息
        ApiInfo apiInfo = new ApiInfoBuilder()
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .contact(new Contact(swaggerProperties.getContact().getName(),
                        swaggerProperties.getContact().getUrl(),
                        swaggerProperties.getContact().getEmail()))
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .build();

        // base-path处理--这个暂不实现
        // 当没有配置任何path的时候，解析/**
//        if (swaggerProperties.getBasePath().isEmpty()) {
//            swaggerProperties.getBasePath().add("/**");
//        }
//        List<Predicate<String>> basePath = new ArrayList<>();
//        for (String path : swaggerProperties.getBasePath()) {
//            basePath.add(PathSelectors.ant(path));
//        }
//
//        // exclude-path处理
//        List<Predicate<String>> excludePath = new ArrayList<>();
//        for (String path : swaggerProperties.getExcludePath()) {
//            excludePath.add(PathSelectors.ant(path));
//        }

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo)
                .groupName(swaggerProperties.getGroup())
                .globalOperationParameters(
                        buildGlobalOperationParametersFromSwaggerProperties(
                                swaggerProperties.getGlobalOperationParameters()))
                .select()

                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
//                .paths(
//                        Predicates.and(
//                                Predicates.not(Predicates.or(excludePath)),
//                                Predicates.or(basePath)
//                        )
//                )
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .globalResponseMessage(RequestMethod.GET, getResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getResponseMessages())
//                .extensions(Lists.newArrayList(new OrderExtensions(swaggerProperties.getOrder())))
                ;
    }


    /**
     * 全局响应
     *
     * @return
     */
    private List<ResponseMessage> getResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(200).message("成功").build(),
                new ResponseMessageBuilder().code(500).message("服务器错误").build(),
                new ResponseMessageBuilder().code(900).message("错误请求").build(),
                new ResponseMessageBuilder().code(903).message("未登录").build(),
                new ResponseMessageBuilder().code(403).message("权限不足").build()
        );
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> contexts = new ArrayList<>(1);
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
        contexts.add(securityContext);
        return contexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> references = new ArrayList<>(1);
        references.add(new SecurityReference(swaggerProperties.getAuthKey(), authorizationScopes));
        return references;
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>(1);

        ApiKey apiKey = new ApiKey(swaggerProperties.getAuthKey(), swaggerProperties.getAuthKey(), "header");
        apiKeys.add(apiKey);
        return apiKeys;
    }

    private List<Parameter> buildGlobalOperationParametersFromSwaggerProperties(
            List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters) {
        List<Parameter> parameters = Lists.newArrayList();

        if (Objects.isNull(globalOperationParameters)) {
            return parameters;
        }
        for (SwaggerProperties.GlobalOperationParameter globalOperationParameter : globalOperationParameters) {
            parameters.add(new ParameterBuilder()
                    .name(globalOperationParameter.getName())
                    .description(globalOperationParameter.getDescription())
                    .modelRef(new ModelRef(globalOperationParameter.getModelRef()))
                    .parameterType(globalOperationParameter.getParameterType())
                    .required(globalOperationParameter.getRequired())
                    .defaultValue(globalOperationParameter.getDefaultValue())
                    .allowEmptyValue(globalOperationParameter.getAllowEmptyValue())
                    .order(globalOperationParameter.getOrder())
                    .build());
        }
        return parameters;
    }


    /**
     * 局部参数按照name覆盖局部参数
     *
     * @param globalOperationParameters
     * @param docketOperationParameters
     * @return List<Parameter>
     */
    private List<Parameter> assemblyGlobalOperationParameters(
            List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters,
            List<SwaggerProperties.GlobalOperationParameter> docketOperationParameters) {

        if (Objects.isNull(docketOperationParameters) || docketOperationParameters.isEmpty()) {
            return buildGlobalOperationParametersFromSwaggerProperties(globalOperationParameters);
        }

        Set<String> docketNames = docketOperationParameters.stream()
                .map(SwaggerProperties.GlobalOperationParameter::getName)
                .collect(Collectors.toSet());

        List<SwaggerProperties.GlobalOperationParameter> resultOperationParameters = Lists.newArrayList();

        if (Objects.nonNull(globalOperationParameters)) {
            for (SwaggerProperties.GlobalOperationParameter parameter : globalOperationParameters) {
                if (!docketNames.contains(parameter.getName())) {
                    resultOperationParameters.add(parameter);
                }
            }
        }

        resultOperationParameters.addAll(docketOperationParameters);
        return buildGlobalOperationParametersFromSwaggerProperties(resultOperationParameters);
    }
}

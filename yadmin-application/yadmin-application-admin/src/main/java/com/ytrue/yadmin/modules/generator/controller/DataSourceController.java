package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenDataSource;
import com.ytrue.yadmin.modules.generator.service.GenDataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author ytrue
 * @date 2022/5/26 17:00
 * @description DataSourceController
 */
@RestController
@RequestMapping("gen/datasource")
@AllArgsConstructor
@Api(tags = "数据源管理")
public class DataSourceController {

    private final GenDataSourceService genDataSourceService;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<GenDataSource>> page(@RequestBody QueryEntity<GenDataSource> queryEntity) {
        IPage<GenDataSource> page = genDataSourceService.page(queryEntity.getPage(), queryEntity.getQueryModel().orderByDesc(GenDataSource::getId));
        return ApiResultResponse.success(page);
    }

    @GetMapping("list")
    @ApiOperation("列表")
    public ApiResultResponse<List<GenDataSource>> list() {
        List<GenDataSource> list = genDataSourceService.list();
        return ApiResultResponse.success(list);
    }

    @GetMapping("{id}")
    @ApiOperation("详情")
    public ApiResultResponse<GenDataSource> detail(@PathVariable("id") Long id) {
        GenDataSource genDataSource = genDataSourceService.getById(id);
        return ApiResultResponse.success(genDataSource);
    }


    @GetMapping("test/{id}")
    @ApiOperation("测试连接")
    public ApiResultResponse<Object> test(@PathVariable("id") Long id) {
        genDataSourceService.testDatabaseConnect(id);
        return ApiResultResponse.success();
    }


    @PostMapping
    @ApiOperation("保存")
    public ApiResultResponse<Object> save(@Valid @RequestBody GenDataSource genDataSource) {
        genDataSourceService.save(genDataSource);
        return ApiResultResponse.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody GenDataSource genDataSource) {
        genDataSourceService.updateById(genDataSource);
        return ApiResultResponse.success();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        genDataSourceService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }
}

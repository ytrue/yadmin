<template>
    <a-card :bordered="false">
        <div class="card-title">上传设置</div>
        <a-spin :spinning="isLoading">
            <a-form :form="form" @submit="handleSubmit">
                <a-form-item label="默认上传方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-radio-group v-decorator="['defaultEngine', { rules: [{ required: true }] }]">
                        <a-radio
                                v-for="(item, index) in StorageEnum.data"
                                :key="index"
                                :value="item.value"
                        >{{ item.name }} {{ item.value == StorageEnum.LOCAL.value ? '(不推荐)' : '' }}
                        </a-radio>
                    </a-radio-group>
                </a-form-item>

                <!-- 七牛云配置 -->
                <div v-show="form.getFieldValue('defaultEngine') == StorageEnum.LOCAL.value">
                    <a-form-item label="存储位置" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.LOCAL.value}.fileHost`]"/>
                    </a-form-item>
                    <a-form-item label="域名" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.LOCAL.value}.domain`]"/>
                    </a-form-item>
                </div>

                <!-- 七牛云配置 -->
                <div v-show="form.getFieldValue('defaultEngine') == StorageEnum.QINIU.value">
                    <a-form-item label="存储位置" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QINIU.value}.fileHost`]"/>
                    </a-form-item>
                    <a-form-item label="存储空间名称 Bucket" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QINIU.value}.bucket`]"/>
                    </a-form-item>
                    <a-form-item label="ACCESS_KEY AK" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QINIU.value}.accessKey`]"/>
                    </a-form-item>
                    <a-form-item label="SECRET_KEY SK" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QINIU.value}.secretKey`]"/>
                    </a-form-item>
                    <a-form-item label="空间域名 Domain" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QINIU.value}.domain`]"/>
                    </a-form-item>
                </div>

                <!-- 阿里云配置 -->
                <div v-show="form.getFieldValue('defaultEngine') == StorageEnum.ALIYUN.value">
                    <a-form-item label="存储位置" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QCLOUD.value}.fileHost`]"/>
                    </a-form-item>
                    <a-form-item label="存储空间名称 Bucket" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.ALIYUN.value}.bucket`]"/>
                    </a-form-item>
                    <a-form-item label="AccessKeyId" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.ALIYUN.value}.accessKeyId`]"/>
                    </a-form-item>
                    <a-form-item label="AccessKeySecret" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.ALIYUN.value}.accessKeySecret`]"/>
                    </a-form-item>
                    <a-form-item label="空间域名 Domain" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.ALIYUN.value}.domain`]"/>
                    </a-form-item>
                </div>

                <!-- 腾讯云配置 -->
                <div v-show="form.getFieldValue('defaultEngine') == StorageEnum.QCLOUD.value">
                    <a-form-item label="存储位置" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QCLOUD.value}.fileHost`]"/>
                    </a-form-item>
                    <a-form-item label="存储空间名称 Bucket" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QCLOUD.value}.bucket`]"/>
                    </a-form-item>
                    <a-form-item label="所属地域 Region" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QCLOUD.value}.region`]"/>
                    </a-form-item>
                    <a-form-item label="SecretId" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QCLOUD.value}.secretId`]"/>
                    </a-form-item>
                    <a-form-item label="SecretKey" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input v-decorator="[`engine.${StorageEnum.QCLOUD.value}.secretKey`]"/>
                    </a-form-item>
                </div>
                <a-form-item :wrapper-col="{ span: wrapperCol.span, offset: labelCol.span }">
                    <a-button type="primary" html-type="submit">提交</a-button>
                </a-form-item>
            </a-form>
        </a-spin>
    </a-card>
</template>

<script>
    import StorageEnum from '@/enums/file/Storage'
    import _ from "lodash";
    import {info, update} from '@/services/system/files/setting'

    export default {
        name: "Setting",
        data() {
            return {
                // loading状态
                isLoading: false,
                // 当前表单元素
                form: this.$form.createForm(this),
                // 标签布局属性
                labelCol: {span: 4},
                // 输入框布局属性
                wrapperCol: {span: 10},
                // 枚举类
                StorageEnum
            }
        },
        // 初始化数据
        created() {
            // 获取当前详情记录
            this.init()
        },
        methods: {
            // 获取当前详情记录
            init() {
                this.isLoading = true
                info().then(response => {
                    const settingData = response.data.data
                    const data1 = _.pick(settingData, ['defaultEngine', 'engine'])
                    const {form: {setFieldsValue}} = this
                    this.$nextTick(() => {
                        //这样才能避免警告
                        setFieldsValue(_.pick(data1, ['defaultEngine',
                            //本地
                            'engine.local.fileHost',
                            'engine.local.domain',
                            //七牛
                            'engine.qiniu.fileHost',
                            'engine.qiniu.bucket',
                            'engine.qiniu.accessKey',
                            'engine.qiniu.secretKey',
                            'engine.qiniu.domain',
                            //阿里云
                            'engine.aliyun.fileHost',
                            'engine.aliyun.bucket',
                            'engine.aliyun.accessKeyId',
                            'engine.aliyun.accessKeySecret',
                            'engine.aliyun.domain',
                            //腾讯云
                            'engine.qcloud.fileHost',
                            'engine.qcloud.bucket',
                            'engine.qcloud.region',
                            'engine.qcloud.secretId',
                            'engine.qcloud.secretKey',
                        ]))
                    })
                }).finally(result => {
                    this.isLoading = false
                })
            },

            /**
             * 确认按钮
             */
            handleSubmit(e) {
                e.preventDefault()
                // 表单验证
                const {form: {validateFields}} = this
                validateFields((errors, values) => {
                    // 提交到后端api
                    !errors && this.onFormSubmit(values)
                })
            },
            /**
             * 提交到后端api
             */
            onFormSubmit(values) {
                this.isLoading = true
                update(values).then((result) => {
                    // 显示成功
                    this.$message.success(result.data.message, 1.5)
                    this.isLoading = false
                })
            }

        }
    }
</script>

<style lang="less" scoped>
    /deep/ .ant-form-item-control {
        padding-left: 10px;

        .ant-form-item-control {
            padding-left: 0;
        }
    }

    // 内容页card组件标题
    .card-title {
        font-size: 16px;
        margin-bottom: 16px;
        color: rgba(0, 0, 0, 0.75);
    }
</style>

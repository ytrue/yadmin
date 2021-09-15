<template>
    <a-modal
            :title="!formId ? '新增' : '修改'"
            :width="720"
            :visible="visible"
            :confirmLoading="confirmLoading"
            :maskClosable="false"
            @ok="handleSubmit"
            @cancel="handleCancel"
            :bodyStyle="bodyStyle"
    >

        <a-spin :spinning="confirmLoading">
            <a-form :form="form">


                <a-form-item label="物流公司名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input placeholder="物流公司名称"
                             v-decorator="['expressName',{rules: [{required: true, message: '请输入物流公司名称'}]}]"/>
                </a-form-item>

                <a-form-item label="物流公司编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input
                            v-decorator="['code', {rules: [{required: true, message: '请输入物流公司编码'}]}]"
                    />
                    <p class="form-item-help">
                        <small>
                            <span>用于查询物流信息，</span>
                            <span>请参照</span>
                            <a href="https://api.kuaidi100.com/manager/page/document/kdbm" target="_blank">物流公司编码表</a>
                        </small>
                    </p>
                </a-form-item>


                <a-form-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol" extra="数字越大越靠前">
                    <a-input-number :min="0"
                                    v-decorator="['orderNum',{initialValue: 0,rules: [{required: true,message: '排序不得为空'}]}]"/>
                </a-form-item>


            </a-form>
        </a-spin>

    </a-modal>
</template>

<script>

    import {saveAndUpdate, info} from '@/services/mall/delivery/express'

    export default {
        name: "AddOrUpdate",
        data() {
            return {
                //是否弹窗
                visible: false,
                //id
                formId: null,
                // modal(对话框)确定按钮 loading
                confirmLoading: true,
                //弹窗样式
                bodyStyle: {
                    'max-height': '500px',
                    'overflow': 'auto'
                },
                // 当前表单元素
                form: this.$form.createForm(this),
                // 标签布局属性
                labelCol: {
                    span: 7
                },
                // 输入框布局属性
                wrapperCol: {
                    span: 13
                }
            }
        },
        methods: {
            /**
             *数据初始化
             */
            init(id) {
                this.confirmLoading = true
                this.visible = true
                this.formId = id || 0
                if (this.formId) {
                    info(this.formId).then(({data}) => {
                        //设置值
                        const data1 = data.data
                        this.form.setFieldsValue({
                            expressName: data1.expressName,
                            code: data1.code,
                            sort: data1.sort,
                        })

                        this.confirmLoading = false
                    })
                } else {
                    this.confirmLoading = false
                }
            },

            /**
             * 确认按钮
             */
            handleSubmit(e) {
                e.preventDefault()
                const {form: {validateFields}} = this
                // 表单验证
                validateFields((errors, values) => {
                    // 提交到后端api
                    if (errors === null) {
                        values.expressId = this.formId
                        this.onFormSubmit(values)
                    }
                })
            },
            /**
             * 提交到后端api
             */
            onFormSubmit(values) {
                this.confirmLoading = true
                saveAndUpdate(values).then((result) => {
                    // 显示成功
                    this.$message.success(result.data.message, 1.5)
                    // 关闭对话框
                    this.handleCancel()
                    // 通知父端组件提交完成了
                    this.$emit('handleSubmit', values)
                }).finally(() => {
                    this.confirmLoading = false
                })
            },

            /**
             * 关闭对话框事件
             */
            handleCancel() {
                this.visible = false
                this.form.resetFields()
            },
        }
    }
</script>

<style scoped>

</style>

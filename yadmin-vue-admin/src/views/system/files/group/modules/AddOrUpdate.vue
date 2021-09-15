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
                <a-form-item label="分组名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input
                            v-decorator="['name', {rules: [{required: true, min: 2, message: '请输入至少2个字符'}]}]"
                    />
                </a-form-item>
                <a-form-item label="上级分组" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-tree-select
                            v-model="value"
                            style="width: 100%"
                            :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                            :tree-data="treeData"
                            placeholder="请选择上级分组"
                            :replaceFields="replaceFields"
                    >
                    </a-tree-select>

                </a-form-item>
                <a-form-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol" extra="数字越大越靠前">
                    <a-input-number
                            :min="0"
                            v-decorator="['sort', {initialValue: 0, rules: [{required: true, message: '请输入至少1个数字'}]}]"
                    />
                </a-form-item>
            </a-form>
        </a-spin>
    </a-modal>
</template>

<script>

    import _ from "lodash";
    import * as Api from '@/services/system/files/group'
    import {treeDataTranslate} from '@/utils/util'

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
                // 标签布局属性
                labelCol: {
                    span: 7
                },
                // 输入框布局属性
                wrapperCol: {
                    span: 13
                },
                // 当前表单元素
                form: this.$form.createForm(this),
                //下拉菜单
                treeData: [],
                value: undefined,
                //自定义tree的数据结构
                replaceFields: {
                    children: 'children',
                    title: 'name',
                    key: 'groupId',
                    value: 'groupId'
                },
            }
        },
        methods: {
            /**
             *数据初始化
             */
            init(id) {
                //this.confirmLoading = true
                this.visible = true
                this.submitButtonLoading = false
                this.formId = id || 0

                Api.list().then((response) => {
                    const groupData = response.data.data
                    this.treeData = treeDataTranslate(groupData, "groupId", "parentId")
                    //头部插入
                    this.treeData.unshift({
                        "groupId": 0,
                        "name": "无上级分组",
                    });
                    this.value = 0
                }).then(() => {
                    if (this.formId) {
                        Api.info(this.formId).then(({data}) => {
                            //设置值
                            const data1 = _.pick(data.data, ['name','sort']);
                            this.value = data.data.parentId
                            const {form: {setFieldsValue}} = this
                            this.$nextTick(() => {
                                setFieldsValue(data1)
                            })
                            this.confirmLoading = false
                        })
                    } else {
                        this.confirmLoading = false
                    }
                })
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
                        //获得上级id
                        values.parentId = this.value
                        values.groupId = this.formId
                        this.onFormSubmit(values)
                    }
                })
            },
            /**
             * 提交到后端api
             */
            onFormSubmit(values) {
                let that = this
                that.confirmLoading = true
                Api.saveAndUpdate(values).then((result) => {
                    // 显示成功
                    that.$message.success(result.data.message, 1.5)
                    // 关闭对话框
                    that.handleCancel()
                    // 通知父端组件提交完成了
                    that.$emit('handleSubmit', values)
                    that.confirmLoading = false
                }).catch((e) => {
                    setTimeout(function () {
                        console.log(e)
                        that.confirmLoading = false
                    }, 200)
                })
            },


            /**
             * 关闭对话框事件
             */
            handleCancel() {
                this.visible = false
                this.treeData = []
                this.value = undefined
                this.form.resetFields()
            },
        }
    }
</script>

<style scoped>

</style>

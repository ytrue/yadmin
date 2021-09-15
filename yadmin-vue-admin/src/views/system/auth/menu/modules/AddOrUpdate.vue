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

                <a-form-item label="上级菜单" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-tree-select
                            v-model="value"
                            style="width: 100%"
                            :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                            :tree-data="treeData"
                            placeholder="请选择上级菜单"
                            :replaceFields="replaceFields"
                           >
                    </a-tree-select>

                </a-form-item>

                <a-form-item label="菜单类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-radio-group v-decorator="['menuType', {initialValue: 0}]" button-style="solid">
                        <a-radio-button :value="0">
                            头部菜单
                        </a-radio-button>
                        <a-radio-button :value="1">
                            侧栏菜单
                        </a-radio-button>
                        <a-radio-button :value="2">
                            目录
                        </a-radio-button>
                        <a-radio-button :value="3">
                            按钮
                        </a-radio-button>
                    </a-radio-group>
                    </div>
                </a-form-item>

                <a-form-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input v-decorator="['name']"/>
                </a-form-item>

                <a-form-item label="路由" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input v-decorator="['router']"/>
                </a-form-item>

                <a-form-item label="path" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input v-decorator="['path']"/>
                </a-form-item>

                <a-form-item label="跳转地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input v-decorator="['redirect']"/>
                </a-form-item>

                <a-form-item label="权限标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input v-decorator="['perms']"/>
                </a-form-item>

                <a-form-item label="icon图标" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input v-decorator="['icon']"/>
                </a-form-item>

                <a-form-item label="是否显示" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-radio-group
                            v-decorator="['hidden', {initialValue: true}]">
                        <a-radio :value="true">显示</a-radio>
                        <a-radio :value="false">隐藏</a-radio>
                    </a-radio-group>
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
    import _ from "lodash";
    import {list, saveAndUpdate, info} from '@/services/system/auth/menu'
    import {treeDataTranslate} from '@/utils/util'

    export default {
        name: "AddOrUpdate",
        data() {
            return {
                treeData: [],
                value: undefined,
                //是否弹窗
                visible: false,
                //id
                formId: null,
                // modal(对话框)确定按钮 loading
                confirmLoading: true,
                // 当前表单元素
                form: this.$form.createForm(this),
                //弹窗样式
                bodyStyle: {
                    'max-height': '500px',
                    'overflow': 'auto'
                },
                // 当前表单元素
                //form: this.$form.createForm(this),
                // 标签布局属性
                labelCol: {
                    span: 7
                },
                // 输入框布局属性
                wrapperCol: {
                    span: 13
                },
                //自定义tree的数据结构
                replaceFields: {
                    children: 'children',
                    title: 'name',
                    key: 'menuId',
                    value: 'menuId'
                },
            }
        },
        methods: {
            init(id) {
                this.visible = true
                this.formId = id || 0
                list().then(response => {
                    const menuData = response.data.data
                    this.treeData = treeDataTranslate(menuData, "menuId", "parentId")
                    //头部插入
                    this.treeData.unshift({
                        "menuId": 0,
                        "name": "无上级菜单",
                    });
                    this.value = 0
                }).then(() => {
                    if (this.formId) {
                        info(this.formId).then(({data}) => {
                            //处理数据
                            //设置值
                            const data1 = _.pick(data.data, [
                                'name',
                                'router',
                                'path',
                                'redirect',
                                'perms',
                                'icon',
                                'orderNum',
                                'hidden',
                                'menuType'
                            ])
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
                        values.menuId = this.formId
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
                saveAndUpdate(values).then((result) => {
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

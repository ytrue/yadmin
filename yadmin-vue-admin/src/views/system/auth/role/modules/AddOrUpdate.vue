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

                <a-form-item label="角色名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input placeholder="请输入角色名称"
                             v-decorator="['roleName']"/>
                </a-form-item>

                <a-form-item label="权限" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-tree
                            v-if="treeData.length > 0"
                            :default-expand-all="false"
                            :replace-fields="replaceFields"
                            v-model="checkedKeys"
                            checkable
                            :tree-data="treeData"
                            @check="onBusinessSelectChange"
                            :checkedKeys="checkedKeys"
                    />
                </a-form-item>

                <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-textarea type="textarea" v-decorator="['remark']"/>
                </a-form-item>
            </a-form>
        </a-spin>

    </a-modal>
</template>

<script>
    import {table} from '@/services/system/auth/menu'
    import {saveAndUpdate, getUserById} from '@/services/system/auth/role'
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
                // 当前表单元素
                form: this.$form.createForm(this),
                // 标签布局属性
                labelCol: {
                    span: 7
                },
                // 输入框布局属性
                wrapperCol: {
                    span: 13
                },
                //这个是初始值
                checkedKeys: [],
                //tree初始化数据
                treeData: [],
                //半选的节点
                businessSelectedRowKeys: [],
                //全部选中的节点
                allSelectedNodes: [],
                //自定义tree的数据结构
                replaceFields: {
                    children: 'children',
                    title: 'name',
                    key: 'menuId',
                },
            }
        },
        methods: {
            onBusinessSelectChange(selectedKeys, info) {
                // 已勾选子节点以及半勾选状态的父节点
                this.allSelectedNodes = selectedKeys.concat(info.halfCheckedKeys);
                this.businessSelectedRowKeys = selectedKeys;
                console.log(this.allSelectedNodes)
            },
            /**
             *数据初始化
             */
            init(id) {
                this.confirmLoading = true
                this.visible = true
                this.formId = id || 0
                this.checkedKeys = []
                this.allSelectedNodes = []
                table().then(({data}) => {
                    const menuData = data.data
                    this.treeData = treeDataTranslate(menuData, "menuId", "parentId")
                    this.confirmLoading = false
                }).then(() => {
                    if (this.formId) {
                        getUserById(this.formId).then(({data}) => {
                            //设置值
                            const data1 = data.data
                            this.form.setFieldsValue({roleName: data1.roleName, remark: data1.remark})
                            this.checkedKeys = data1.menuIdList
                            this.allSelectedNodes = data1.menuIdList
                        })
                    }
                })
            },
            /**
             * 确认按钮
             */
            handleSubmit(e) {
                // if (this.confirmLoading === true) return
                e.preventDefault()
                const {form: {validateFields}} = this
                // 表单验证
                validateFields((errors, values) => {
                    // 提交到后端api
                    if (errors === null) {
                        //这里要判断一下是否填写了权限
                        //allSelectedNodes
                        // if (this.allSelectedNodes.length === 0) {
                        //     this.$message.error('请选择权限', 1.5)
                        //     return
                        // }
                        values.roleId = this.formId
                        values.menuIdList = this.allSelectedNodes
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
                this.treeData = []
                this.checkedKeys = []
            },
        }
    }
</script>

<style scoped>

</style>

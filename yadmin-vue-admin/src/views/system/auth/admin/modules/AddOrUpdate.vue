<template>
    <drag-modal-vue
            :maskClosable="false"
            :width="720"
            v-model="visible"
            :title="!formId ? '新增' : '修改'"
    >
        <a-spin :spinning="confirmLoading">
            <a-form :form="form">
                <a-form-item label="登录帐号" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input placeholder="请输入登录帐号"
                             :disabled=disabled
                             v-decorator="['username', {rules: [{required: true, min: 2, message: '请输入至少2个字符'}]}]"/>
                </a-form-item>


                <a-form-item label="昵称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input placeholder="昵称"
                             v-decorator="['nickname', {rules: [{required: true, message: '昵称不得为空'}]}]"/>
                </a-form-item>

                <a-form-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input
                            placeholder="请输入邮箱"
                            v-decorator="['email', {rules: [{required: true, pattern: /[a-zA-Z0-9_]{1,}[@][a-z0-9]{2,3}[.][a-z]{2,3}/, message: '请输入正确邮箱'}]}]"/>
                </a-form-item>

                <a-form-item label="手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input
                            placeholder="请输入手机号"
                            v-decorator="['mobile', {rules: [{required: true, pattern: /^[1][3,4,5,6,7,8,9][0-9]{9}$/, message: '请输入正确手机号'}]}]"/>
                </a-form-item>

                <a-form-item label="用户密码" :labelCol="labelCol" :wrapperCol="wrapperCol"
                             extra="后台登录密码"
                             v-if="formId ===0">
                    <a-input placeholder="请输入用户密码" type="password"
                             v-decorator="['password', {rules: [{required: true, min: 6, message: '密码长度不得小于6位'} ]}]"/>
                </a-form-item>
                <a-form-item label="用户密码" :labelCol="labelCol" :wrapperCol="wrapperCol"
                             extra="后台登录密码"
                             v-else>
                    <a-input placeholder="请输入用户密码" type="password"
                             v-decorator="['password', {rules: [{ min: 6, message: '密码长度不得小于6位'}]}]"/>
                </a-form-item>

                <!--密码设置-->
                <a-form-item label="确认密码" :labelCol="labelCol" :wrapperCol="wrapperCol"
                             v-if="formId ===0">
                    <a-input
                            placeholder="请输入用户确认密码"
                            type="password"
                            v-decorator="['password_confirm', {rules: [{required: true, message: '确认密码不得为空'},{validator: compareToFirstPassword} ]}]"/>
                </a-form-item>
                <a-form-item label="确认密码" :labelCol="labelCol" :wrapperCol="wrapperCol"
                             v-else>
                    <a-input
                            placeholder="请输入用户确认密码"
                            type="password"
                            v-decorator="['password_confirm', {rules: [{ message: '确认密码不得为空'},{validator: compareToFirstPassword}]}]"/>
                </a-form-item>

                <a-form-item label="角色" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-select
                            show-search
                            mode="multiple"
                            placeholder="请选择角色"
                            option-filter-prop="children"
                            v-decorator="['roleIdList', {rules: [{required: true, message: '请选择角色'}]}]">
                        <a-select-option
                                v-for="(item, index) in roleList"
                                :key="index"
                                :value="item.roleId">{{ item.roleName }}
                        </a-select-option>
                    </a-select>
                </a-form-item>

                <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-radio-group
                            v-decorator="['status', {initialValue: 1, rules: [{ required: true }]}]">
                        <a-radio :value="1">正常</a-radio>
                        <a-radio :value="0">禁用</a-radio>
                    </a-radio-group>
                </a-form-item>

            </a-form>
        </a-spin>

        <div slot="close">
            <img alt="无图片"
                 @click="handleCancel"
                 src="data:image/png;base64,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"
                 class="drag-modal-vue-close">
        </div>
        <div slot="footer">
            <button @click="handleCancel" type="button" class="ant-btn"><span>取 消</span></button>
            <a-button
                    :loading="submitButtonLoading"
                    @click="handleSubmit"
                    type="button"
                    class="ant-btn ant-btn-primary"
                    style="margin-left: 8px"
            >
                <span>确 定</span>
            </a-button>
        </div>
    </drag-modal-vue>


</template>

<script>
    import _ from "lodash";
    import {saveAndUpdate, info} from '@/services/system/auth/admin'
    import {roleList} from '@/services/system/auth/role'

    export default {
        name: "AddOrUpdate",
        data() {
            return {
                //是否禁用
                disabled: false,
                //是否弹窗
                visible: false,
                // modal(对话框)确定按钮 loading
                confirmLoading: true,
                //确认按钮的 loading
                submitButtonLoading: false,
                // 当前表单元素
                form: this.$form.createForm(this),
                //角色列表
                roleList: [],
                //id
                formId: null,
                // 当前记录
                record: {},
                // 标签布局属性
                labelCol: {
                    span: 7
                },
                // 输入框布局属性
                wrapperCol: {
                    span: 13
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
                roleList().then(({data}) => {
                    this.roleList = data.data
                }).then(() => {
                    if (this.formId) {
                        this.disabled = true
                        info(this.formId).then(({data}) => {
                            //设置值
                            const data1 = _.pick(data.data, ['username', 'mobile', 'email', 'status', 'roleIdList'])
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
                if (this.confirmLoading === true) return
                e.preventDefault()
                const {form: {validateFields}} = this
                // 表单验证
                validateFields((errors, values) => {
                    // 提交到后端api
                    if (errors === null) {
                        this.confirmLoading = true
                        this.submitButtonLoading = true
                        values.userId = this.formId
                        this.onFormSubmit(values)
                    }
                })
            },
            /**
             * 提交到后端api
             */
            onFormSubmit(values) {
                saveAndUpdate(values).then((result) => {
                    // 显示成功
                    this.$message.success(result.data.message, 1.5)
                    // 关闭对话框
                    this.handleCancel()
                    // 通知父端组件提交完成了
                    this.$emit('handleSubmit', values)
                }).finally(() => {
                    this.confirmLoading = false
                    this.submitButtonLoading = false
                })
            },
            /**
             * 关闭对话框事件
             */
            handleCancel() {
                this.confirmLoading = true
                this.visible = false
                this.disabled = false
                this.form.resetFields()
            },
            /**
             * 验证确认密码是否一致
             */
            compareToFirstPassword(rule, value,) {
                const {form} = this
                if (value && value !== form.getFieldValue('password')) {
                    return new Error('您输入的确认密码不一致')
                }
                return true
            },
        }
    }
</script>

<style scoped>

</style>

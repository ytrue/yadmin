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
        <a-form :form="form">
            <a-form-item label="分类名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-input
                        v-decorator="['name', {rules: [{required: true, message: '分类名称不得为空'}]}]"
                />
            </a-form-item>

            <a-form-item label="上级分类" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-tree-select
                        v-model="value"
                        style="width: 100%"
                        :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                        :tree-data="treeData"
                        placeholder="请选择上级分类"
                        :replaceFields="replaceFields"
                >
                </a-tree-select>
            </a-form-item>

            <a-form-item label="分类图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <SelectImage
                        :initData="imgData"
                        v-decorator="['image']"
                />
            </a-form-item>

            <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <a-radio-group
                        v-decorator="['status', {initialValue: true}]">
                    <a-radio :value="true">显示</a-radio>
                    <a-radio :value="false">隐藏</a-radio>
                </a-radio-group>
            </a-form-item>

            <a-form-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol" extra="数字越大越靠前">
                <a-input-number
                        :min="0"
                        v-decorator="['sort', {initialValue: 0, rules: [{required: true, message: '排序不得为空'}]}]"
                />
            </a-form-item>

        </a-form>
    </a-modal>
</template>

<script>
    import {list, saveAndUpdate, info} from '@/services/mall/product/category'
    import {treeDataTranslate} from '@/utils/util'
    import SelectImage from '@/components/image/SelectImage'

    export default {
        components: {
            SelectImage
        },
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
                    key: 'categoryId',
                    value: 'categoryId'
                },
                imgData: []
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
                list({fields: [{column: 'parent_id', type: 'eq', value: 0}]}).then((response) => {
                    const groupData = response.data.data
                    this.treeData = treeDataTranslate(groupData, "categoryId", "parentId")
                    //头部插入
                    this.treeData.unshift({
                        "categoryId": 0,
                        "name": "顶级分类",
                    });
                    this.value = 0
                }).then(() => {
                    if (this.formId) {
                        info(this.formId).then(({data}) => {
                            //设置值
                            const data1 = _.pick(data.data, ['name', 'sort', 'image', 'status']);
                            this.value = data.data.parentId
                            this.value = data.data.parentId
                            if (data1.image !== '') {
                                this.imgData = [{filePath: data1.image}]
                            }
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
                        values.categoryId = this.formId
                        if (values.image === 0) {
                            values.image = ''
                        }
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
                this.imgData = []
            },
        }
    }
</script>

<style scoped>

</style>

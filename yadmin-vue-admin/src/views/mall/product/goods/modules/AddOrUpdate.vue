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

        <a-form :form="form" :selfUpdate="true">
            <a-tabs :activeKey="tabKey" :tabBarStyle="{marginBottom: '30px'}" @change="handleTabs">
                <a-tab-pane :key="0" tab="基本信息"></a-tab-pane>
                <a-tab-pane :key="1" tab="规格/库存"></a-tab-pane>
                <a-tab-pane :key="2" tab="商品详情"></a-tab-pane>
                <a-tab-pane :key="3" tab="更多设置"></a-tab-pane>
            </a-tabs>


            <div class="tabs-content">
                <!-- 基本信息 start -->
                <div class="tab-pane" v-show="tabKey == 0">
                    <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input
                                placeholder="请输入商品名称"
                                v-decorator="['goods_name', {rules: [{required: true, min: 2, message: '请输入至少2个字符'}]}]"
                        />
                    </a-form-item>

                    <a-form-item label="商品分类" :labelCol="labelCol" :wrapperCol="wrapperCol">

                    </a-form-item>

                    <a-form-item
                            label="商品图片"
                            :labelCol="labelCol"
                            :wrapperCol="wrapperCol"
                            extra="建议尺寸：750*750像素, 最多上传10张, 可拖拽图片调整顺序, 第1张将作为商品首图"
                    >
                    </a-form-item>

                    <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-input placeholder="请输入商品编码" v-decorator="['goods_no']"/>
                    </a-form-item>

                    <a-form-item label="运费模板" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    </a-form-item>

                    <a-form-item label="商品状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
                        <a-radio-group
                                v-decorator="['status', {initialValue: 10, rules: [{ required: true }]}]"
                        >
                            <a-radio :value="10">上架</a-radio>
                            <a-radio :value="20">下架</a-radio>
                        </a-radio-group>
                    </a-form-item>
                    <a-form-item label="商品排序" :labelCol="labelCol" :wrapperCol="wrapperCol" extra="数字越小越靠前">
                        <a-input-number
                                :min="0"
                                v-decorator="['sort', {initialValue: 100, rules:[{ required: true }]}]"
                        />
                    </a-form-item>
                </div>
                <!-- 基本信息 end -->
                <!-- 规格/库存 start -->

                <!-- 规格/库存 end -->
            </div>
        </a-form>
    </a-modal>
</template>

<script>
    export default {
        name: "AddOrUpdate",
        data() {
            return {
                //表单的配置
                // 默认的标签索引
                tabKey: 0,


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
                    span: 4
                },
                // 输入框布局属性
                wrapperCol: {
                    span: 18
                },
            }
        },
        methods: {
            /**
             *数据初始化
             */
            init(id) {
                //   this.confirmLoading = true
                this.visible = true
                this.confirmLoading = false
            },

            // 切换tab选项卡
            handleTabs(key) {
                this.tabKey = key
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

<style scoped lang="less">
    .tabs-content {
        .tab-pane {
            animation: fadenum 0.6s ease;
        }
    }
</style>

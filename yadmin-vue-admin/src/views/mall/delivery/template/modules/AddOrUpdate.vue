<template>
    <a-modal
            :title="!formId ? '新增' : '修改'"
            :width="1100"
            :visible="visible"
            :confirmLoading="confirmLoading"
            :maskClosable="false"
            @ok="handleSubmit"
            @cancel="handleCancel"
            :bodyStyle="bodyStyle"
    >
        <a-spin :spinning="confirmLoading">
            <a-form :form="form">

                <a-form-item label="模版名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-input placeholder="请输入模版名称"
                             v-decorator="['name', {rules: [{required: true, min: 2, message: '请输入至少2个字符'}]}]"/>
                </a-form-item>

                <a-form-item label="计费方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
                    <a-radio-group
                            @change="onChangemMethod"
                            v-decorator="['method', {initialValue: 10, rules: [{required: true}]}]"
                    >
                        <a-radio :value="10">按件数</a-radio>
                        <a-radio :value="20">按重量</a-radio>
                    </a-radio-group>
                </a-form-item>


                <a-form-item label="配送区域及运费" :labelCol="labelCol" :wrapperCol="{ span: 15 }" required>
                    <a-table
                            v-show="ruleList.length"
                            class="table-rules"
                            :columns="columns"
                            :dataSource="ruleList"
                            :pagination="false"
                            bordered
                    >
                        <!-- 可配送区域 -->
                        <template slot="regionText" slot-scope="text, item, index">
                            <p class="content">
                <span v-for="(province, pidx) in text" :key="pidx">
                  <span>{{ province.name }}</span>
                  <template v-if="province.citys.length">
                    <span>(</span>
                    <span
                            class="city-name"
                            v-for="(city, cidx) in province.citys"
                            :key="cidx"
                    >{{ city.name }}{{ province.citys.length > cidx + 1 ? '、': '' }}</span>
                    <span>)</span>
                  </template>
                  <span>{{ ' ' }}</span>
                </span>
                            </p>
                            <p class="operation">
                                <a href="javascript:void(0);" class="edit" @click="handleEdit(index, item)">编辑</a>
                                <a href="javascript:void(0);" class="delete" @click="handleDelete(index)">删除</a>
                            </p>
                        </template>
                        <!-- 首件 (个) -->
                        <template slot="first" slot-scope="text, item">
                            <a-input-number
                                    v-model="item.first"
                                    :min="method == 10 ? 1 : 0.01"
                                    :precision="method == 10 ? 0 : 2"
                            />
                        </template>
                        <!-- 首运费(元) -->
                        <template slot="firstFee" slot-scope="text, item">
                            <a-input-number v-model="item.firstFee" :min="0" :precision="2"/>
                        </template>
                        <!-- 续件 (个) -->
                        <template slot="additional" slot-scope="text, item">
                            <a-input-number v-model="item.additional" :min="0" :precision="method == 10 ? 0 : 2"/>
                        </template>
                        <!-- 续费(元) -->
                        <template slot="additionalFee" slot-scope="text, item">
                            <a-input-number v-model="item.additionalFee" :min="0" :precision="2"/>
                        </template>
                    </a-table>
                    <a-button icon="environment" @click="handleAdd">点击添加配送区域</a-button>
                </a-form-item>
                <a-form-item label="排序" :labelCol="labelCol" :wrapperCol="wrapperCol" extra="数字越小越靠前">
                    <a-input-number
                            :min="0"
                            v-decorator="['sort', {initialValue: 0, rules:[{required: true, message: '请输入排序值'}]}]"
                    />
                </a-form-item>

            </a-form>
        </a-spin>

        <AreasModal ref="AreasModal" @handleSubmit="handleAreaSubmit"/>
    </a-modal>
</template>

<script>
    import AreasModal from './AreasModal'
    import Region from './Region'
    import {saveAndUpdate, info} from '@/services/mall/delivery/template'
    import {isEmpty} from '@/utils/util'

    const defaultItem = {
        key: 0,
        first: 1,
        first_fee: 0,
        additional: 0,
        additional_fee: 0,
        region: [],
        region_text: []
    }

    export default {
        components: {
            AreasModal
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
                // 当前表单元素
                form: this.$form.createForm(this),
                // 标签布局属性
                labelCol: {
                    span: 3
                },
                // 输入框布局属性
                wrapperCol: {
                    span: 21
                },

                // table字段
                columns: [
                    {
                        title: '可配送区域',
                        dataIndex: 'regionText',
                        width: '400px',
                        scopedSlots: {customRender: 'regionText'}
                    },
                    {
                        title: '首件 (个)',
                        dataIndex: 'first',
                        scopedSlots: {customRender: 'first'}
                    },
                    {
                        title: '运费(元)',
                        dataIndex: 'firstFee',
                        scopedSlots: {customRender: 'firstFee'}
                    },
                    {
                        title: '续件 (个)',
                        dataIndex: 'additional',
                        scopedSlots: {customRender: 'additional'}
                    },
                    {
                        title: '续费(元)',
                        dataIndex: 'additionalFee',
                        scopedSlots: {customRender: 'additionalFee'}
                    }
                ],
                // table内容
                ruleList: [],
                // 正在加载
                isLoading: false,
                isBtnLoading: false,
                // 计费方式
                method: 10,
                // 城市总数
                citysCount: null
            }
        },
        created() {
            // 城市总数
            Region.getCitysCount().then(count => {
                console.log('我是Test获得城市总数')
                this.citysCount = count
            })
        },
        methods: {
            /**
             *数据初始化
             */
            init(id) {
                const {form} = this
                this.confirmLoading = false
                this.visible = true
                this.formId = id || 0
                if (this.formId) {
                    info(this.formId).then(result => {
                        const record = result.data.data
                        // 设置表单默认值
                        !isEmpty(form.getFieldsValue()) && form.setFieldsValue({
                            'name': record.name,
                            'method': record.method,
                            'sort': record.sort
                        })

                        // 记录默认值
                        this.ruleList = record.rules.map((item, index) => {
                            //这里返回的时json字符串，要转成js对象
                            item.region = JSON.parse(item.region)
                            item.regionText = JSON.parse(item.regionText)
                            //删除时间这个，不然添加会报错误
                            delete item.createTime;
                            return {...item, key: index}
                        })
                        this.method = record.method
                        this.record = record
                        this.isLoading = false
                    })
                }

            },
            /**
             * 确认按钮
             */
            handleSubmit(e) {
                e.preventDefault()
                // 表单验证
                const {form: {validateFields}, ruleList} = this
                validateFields((errors, values) => {
                    if (errors) {
                        return false
                    }
                    if (ruleList.length === 0) {
                        this.$message.error('您还没有添加配送区域及运费', 0.8)
                        return false
                    }
                    // 提交到后端api
                    values.rules = ruleList
                    // 记录默认值
                    values.rules = values.rules.map((item, index) => {
                        //保存要把对象转化成json字符串，防止多次转化没加一个判断
                        if (typeof (item.region) !== 'string') {
                            item.region = JSON.stringify(item.region)
                        }
                        if (typeof (item.regionText) !== 'string') {
                            item.regionText = JSON.stringify(item.regionText)
                        }
                        return {...item, key: index}
                    })
                    values.deliveryId = this.formId
                    this.onFormSubmit(values)
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


            // 更改计费方式
            onChangemMethod(e) {
                this.method = e.target.value
                const text = {
                    10: {first: '首件 (个)', additional: '续件 (个)'},
                    20: {first: '首重 (Kg)', additional: '续重 (Kg)'}
                }
                this.columns[1].title = text[this.method].first
                this.columns[3].title = text[this.method].additional
            },

            // 新增记录
            handleAdd() {
                const index = this.ruleList.length
                const newItem = {...defaultItem, key: index}
                // 排除的城市id集(已存在的城市id集)
                const excludedCityIds = this.getExcludedCityIds()

                if (excludedCityIds.length === this.citysCount) {
                    this.$message.error('已选择了所有的区域', 0.8)
                    return false
                }
                // 显示选择地区对话框
                this.handleAreasModal('add', index, newItem, excludedCityIds)
            },

            // 编辑记录
            handleEdit(index, item) {
                // 排除的城市id集(已存在的城市id集)
                const excludedCityIds = this.getExcludedCityIds()
                // 显示选择地区对话框
                this.handleAreasModal('edit', index, item, excludedCityIds)
            },

            // 显示选择地区对话框
            handleAreasModal(scene, index, item, excludedCityIds) {
                this.$refs.AreasModal.handle({scene, index, item}, item.region, excludedCityIds)
            },

            // 排除的城市id集(已存在的城市id集)
            getExcludedCityIds() {
                const excludedCityIds = []
                this.ruleList.forEach(item => {
                    item.region.forEach(cityId => {
                        excludedCityIds.push(cityId)
                    })
                })
                return excludedCityIds
            },

            // 选择地区后的回调
            handleAreaSubmit(result) {
                const {custom: {scene, item}} = result
                item.region = result.selectedCityIds
                item.regionText = result.selectedText
                if (scene === 'add') {
                    this.ruleList.push(item)
                }
            },

            // 删除记录
            handleDelete(index) {
                const app = this
                const modal = this.$confirm({
                    title: '您确定要删除该记录吗?',
                    onOk() {
                        app.ruleList.splice(index, 1)
                        modal.destroy()
                    }
                })
            },

            /**
             * 关闭对话框事件
             */

            handleCancel() {
                this.visible = false
                this.form.resetFields()
                this.citysCount = null;
                this.ruleList = [];
            },
        }
    }

</script>

<style lang="less">
    .table-rules {
        .operation {
            text-align: right;

            a {
                font-size: 13px;
                margin-left: 6px;
            }
        }

        .content {
            color: #505050;
            white-space: normal;

            .city-name {
                font-size: 12.5px;
                color: #7b7b7b;
            }
        }
    }
</style>

<template>
    <a-card>
        <!--查询 start-->
        <div class="table-operator">
            <!-- 搜索板块 -->
            <a-row class="row-item-search">
                <a-form class="search-form" :form="searchFrom" layout="inline">
                    <a-form-item label="商品名称">
                        <a-input v-decorator="['goodsName']" placeholder="请输入商品名称"/>
                    </a-form-item>
                    <a-form-item label="商品编码">
                        <a-input v-decorator="['goodsNo']" placeholder="请输入商品编码"/>
                    </a-form-item>
                    <a-form-item label="商品分类">
                        <a-input v-decorator="['leix']" placeholder="请输入商品编码"/>
                    </a-form-item>

                    <a-form-item>
                        <a-button icon="search" @click="init('search')" type="primary"
                                  :loading="searchButtonLoading">查询
                        </a-button>
                        <a-button icon="undo"
                                  @click="resetSearch"
                                  :loading="resetButtonLoading"
                                  style="margin-left: 8px">重置
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-row>
        </div>
        <!--查询 end-->
        <!--表格 start-->
        <div>
            <!--头部菜单 start-->
            <a-space class="operator">
                <a-radio-group defaultValue="all">
                    <a-radio-button value="all">全部</a-radio-button>
                    <a-radio-button value="on_sale">出售中</a-radio-button>
                    <a-radio-button value="off_sale">已下架</a-radio-button>
                    <a-radio-button value="sold_out">已售罄</a-radio-button>
                </a-radio-group>
                <!--                <a-button icon="arrow-up"-->
                <!--                          style=" color: #fff; background-color: #67c23a; border-color: #67c23a;">上架-->
                <!--                </a-button>-->

                <!--                <a-button icon="arrow-down"-->
                <!--                          style=" color: #fff; background-color: #e6a23c; border-color: #e6a23c;">-->
                <!--                    下架-->
                <!--                </a-button>-->
                <a-button icon="form" type="primary" @click="addOrUpdateHandle()">新增</a-button>
                <a-button icon="delete" type="danger" @click="handleBatchDelete">删除</a-button>

            </a-space>
            <!--头部菜单 end-->
            <a-table
                    row-key="userId"
                    :columns="columns"
                    :dataSource="dataSource"
                    :pagination="pagination"
                    @change="onChange"
                    :loading="initLoading"
                    :data-source="dataSource"
                    :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
            >
             <span slot="status" slot-scope="text, record">
                  <a-tag v-if="record.status === 0">禁用</a-tag>
                  <a-tag v-else color="#87d068">正常</a-tag>
             </span>>

                <span slot="action" slot-scope="text, record">
                   <a-button style="background-color: #108ee9;border-color:#108ee9" icon="edit" type="primary"
                             size="small"
                             @click="addOrUpdateHandle(record.userId)">编辑</a-button>
                   <a-button size="small" type="danger" icon="delete" style="margin-left: 8px"
                             @click="handleDelete(record)">删除</a-button>
             </span>
            </a-table>
        </div>

        <add-or-update
                @handleSubmit="resetSearch"
                ref="addOrUpdate">
        </add-or-update>

    </a-card>
</template>

<script>

    import {page, remove} from '@/services/mall/product/goods'
    import AddOrUpdate from "./modules/AddOrUpdate";

    export default {
        components: {AddOrUpdate},
        name: "Goods",
        data() {
            return {
                ids: [],
                visible: true,
                searchButtonLoading: false,
                resetButtonLoading: false,
                initLoading: true,
                searchFrom: this.$form.createForm(this),
                pagination: {
                    total: 0,
                    pageSize: 0,
                    showTotal: total => `共 ${total} 条数据`,
                },
                columns: [
                    {
                        title: '商品编码',
                        dataIndex: 'goodsNo',
                    },
                    {
                        title: '商品名称',
                        dataIndex: 'goodsName',
                    },
                    {
                        title: '商品图片',
                        dataIndex: 'goodsImages',
                    },
                    {
                        title: '商品价格',
                        dataIndex: 'goodsPriceMin',
                    },
                    {
                        title: '总销量',
                        dataIndex: 'salesActual',
                    },
                    {
                        title: '库存总量',
                        dataIndex: 'stockTotal',
                    },
                    {
                        title: '状态',
                        scopedSlots: {customRender: 'status'}
                    },
                    {
                        title: '排序',
                        dataIndex: 'sort',
                    },
                    {
                        title: '添加时间',
                        dataIndex: 'createTime',
                    },
                    {
                        title: '操作',
                        scopedSlots: {customRender: 'action'},
                        width: '230px',
                        align: 'center'
                    }
                ],
                dataSource: [],
                selectedRowKeys: [],
                currentPage: 1
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            /**
             *初始化数据
             */
            init(type = "") {
                this.buttonLoading(type, true)
               // let username = this.searchFrom.getFieldValue('username')
                let searchParam = [
                    //{column: 'username', type: 'like', value: username ? username : ""}
                ]
                page({
                    currentPage: this.currentPage,
                    fields: searchParam,
                    limit: 5
                }).then(response => {
                    const {data} = response.data
                    this.dataSource = data.records
                    this.pagination.pageSize = data.size
                    this.pagination.total = data.total
                    this.initLoading = false
                    this.buttonLoading(type, false)
                })
            },

            /**
             *新增 - 修改
             */
            addOrUpdateHandle(id) {
                this.$nextTick(() => {
                    this.$refs.addOrUpdate.init(id)
                })
            },

            /**
             * 重置搜索表单
             */
            resetSearch() {
                this.searchFrom.resetFields();
                this.init("reset")
            },
            /**
             * 按钮的loading状态
             * @param type
             * @param status
             */
            buttonLoading(type, status) {
                if (type === "search") {
                    this.searchButtonLoading = status
                }
                if (type === "reset") {
                    this.resetButtonLoading = status
                }
                this.initLoading = status
            },
            /**
             * 分页处理
             * @param selectedRowKeys
             */
            onChange(selectedRowKeys) {
                this.currentPage = selectedRowKeys.current
                this.init()
            },
            /**
             * select 动作
             * @param row
             */
            onSelectChange(row) {
                this.selectedRowKeys = row;
                this.ids = row
            }
        }
    }
</script>

<style scoped lang="less">
</style>

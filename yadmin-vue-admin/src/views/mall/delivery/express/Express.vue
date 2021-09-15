<template>
    <a-card>
        <!--查询 start-->
        <div class="table-operator">
            <!-- 搜索板块 -->
            <a-row class="row-item-search">
                <a-form class="search-form" :form="searchFrom" layout="inline">
                    <a-form-item label="物流公司名称" refs="searchFromRefs">
                        <a-input placeholder="请输入物流公司名称" v-decorator="['expressName']"/>
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
                <a-button icon="form" type="primary" @click="addOrUpdateHandle()">新增</a-button>
                <a-button icon="delete" type="danger" @click="handleBatchDelete">批量删除</a-button>
            </a-space>
            <!--头部菜单 end-->

            <a-table
                    row-key="expressId"
                    :columns="columns"
                    :dataSource="dataSource"
                    :pagination="pagination"
                    @change="onChange"
                    :loading="initLoading"
                    :data-source="dataSource"
                    :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
            >

           <span slot="isDefault" slot-scope="record">
              <a-tag v-if="record.isDefault === false">否</a-tag>
              <a-tag v-else color="#87d068">
                是
              </a-tag>
            </span>

                <span slot="status" slot-scope="record">
              <a-tag v-if="record.status === false">隐藏</a-tag>
              <a-tag v-else color="#87d068">
                显示
              </a-tag>
            </span>

                <span slot="action" slot-scope="text, record">
              <a-button style="background-color: #108ee9;border-color:#108ee9" icon="edit" type="primary"
                        size="small" @click="addOrUpdateHandle(record.expressId)">编辑
              </a-button>
              <a-button size="small" icon="delete" type="danger" style="margin-left: 8px" @click="handleDelete(record)">删除
              </a-button>
            </span>

            </a-table>

        </div>
        <!--表格end-->

        <add-or-update
                @handleSubmit="resetSearch"
                ref="addOrUpdate">
        </add-or-update>

    </a-card>
</template>

<script>

    import AddOrUpdate from "./modules/AddOrUpdate";
    import {page, remove} from '@/services/mall/delivery/express'

    export default {
        components: {AddOrUpdate},
        name: "Express",
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
                        title: '物流公司名称',
                        dataIndex: 'expressName',
                    },
                    {
                        title: '物流公司编码',
                        dataIndex: 'code',
                    },
                    {
                        title: '排序',
                        dataIndex: 'sort'
                    },
                    {
                        title: '添加时间',
                        dataIndex: 'createTime'
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
            init(type = "") {
                this.buttonLoading(type, true)
                let expressName = this.searchFrom.getFieldValue('expressName')
                let searchParam = [
                    {column: 'express_name', type: 'like', value: expressName ? expressName : ""}
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
             * 删除记录
             */
            handleDelete(item) {
                const app = this
                const modal = this.$confirm({
                    title: '您确定要删除该记录吗?',
                    content: '删除后不可恢复',
                    onOk() {
                        remove([item['expressId']])
                            .then((result) => {
                                app.$message.success(result.data.message, 1.5)
                                app.resetSearch()
                            }).catch(() => {
                            modal.destroy()
                        })
                    }
                })
            },
            /**
             *批量删除
             */
            handleBatchDelete() {
                const app = this
                //判断一下
                let delIds = app.ids
                if (delIds.length === 0) {
                    app.$message.error('请选择需要删除的数据', 1.5)
                    return
                }
                const modal = this.$confirm({
                    title: '您确定要删除选择的记录吗?',
                    content: '删除后不可恢复',
                    onOk() {
                        remove(delIds)
                            .then((result) => {
                                app.$message.success(result.data.message, 1.5)
                                app.resetSearch()
                                app.ids = []
                            }).catch(() => {
                            modal.destroy()
                        })
                    }
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
            },
            /**
             *新增 - 修改
             */
            addOrUpdateHandle(id) {
                this.$nextTick(() => {
                    this.$refs.addOrUpdate.init(id)
                })
            },
        }
    }
</script>

<style scoped>

</style>

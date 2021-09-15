<template>
    <a-card>
        <!--查询 start-->
        <div class="table-operator">
            <!-- 搜索板块 -->
            <a-row class="row-item-search">
                <a-form class="search-form" :form="searchFrom" layout="inline">
                    <a-form-item label="文件名称" refs="searchFromRefs">
                        <a-input placeholder="请输入文件名称" v-decorator="['fileName']"/>
                    </a-form-item>


                    <a-form-item label="文件分组">
                        <a-select v-decorator="['type',{initialValue: ''}]">
                            <a-select-option value="">全部</a-select-option>
                            <a-select-option value="OPT">正常</a-select-option>
                            <a-select-option value="EX">异常</a-select-option>
                        </a-select>
                    </a-form-item>


                    <a-form-item>
                        <a-button icon="search" @click="init('search')" type="primary"
                                  :loading="searchButtonLoading">查询</a-button>
                        <a-button icon="undo"
                                  @click="resetSearch"
                                  :loading="resetButtonLoading"
                                  style="margin-left: 8px">重置</a-button>
                    </a-form-item>

                </a-form>
            </a-row>
        </div>
        <!--查询 end-->

        <!--表格 start-->
        <div>
            <!--头部菜单 start-->
            <div style="margin-bottom: 18px;">
                <a-space class="operator">

                    <a-upload
                            name="iFile"
                            accept="image/*"
                            :beforeUpload="beforeUpload"
                            :customRequest="onUpload"
                            :multiple="true"
                            :showUploadList="false"
                    >
                        <a-button
                                :loading="uploadButtonLoading"
                                icon="cloud-upload"
                                type="primary">上传
                        </a-button>
                    </a-upload>

                    <a-button icon="delete" type="danger" @click="handleBatchDelete">批量删除</a-button>
                </a-space>
            </div>
            <!--头部菜单 end-->

            <a-table
                    row-key="fileId"
                    :columns="columns"
                    :dataSource="dataSource"
                    :pagination="pagination"
                    @change="onChange"
                    :loading="initLoading"
                    :data-source="dataSource"
                    :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
            >

                <!-- 文件预览 -->
                <span slot="image" slot-scope="text, record">
                    <div class="preview-box">
                      <a :href="record.filePath" target="_blank">
                        <img :src="text"/>
                      </a>
                    </div>
                 </span>

                <span slot="status" slot-scope="text, record">
                      <a-tag v-if="record.status === 0">禁用</a-tag>
                      <a-tag v-else color="#87d068">
                        启用
                      </a-tag>
                 </span>>
                <span slot="action" slot-scope="text, record">
                      <a-button style="background-color: #108ee9;border-color:#108ee9" icon="edit" type="primary"
                                size="small" @click="addOrUpdateHandle(record.fileId)">编辑
                      </a-button>
                      <a-button size="small" type="danger" icon="delete" style="margin-left: 8px"
                                @click="handleDelete(record)">
                        删除
                      </a-button>
                </span>
            </a-table>

        </div>
        <!--表格end-->
        <update
                @handleSubmit="resetSearch"
                ref="update">
        </update>


    </a-card>
</template>

<script>
    import * as FileApi from '@/services/system/files/file'
    import Update from "./modules/Update";

    export default {
        components: {Update},
        name: "File",
        data() {
            return {
                ids: [],
                visible: true,
                searchButtonLoading: false,
                resetButtonLoading: false,
                uploadButtonLoading: false,
                initLoading: true,
                searchFrom: this.$form.createForm(this),
                pagination: {
                    total: 0,
                    pageSize: 0,
                    showTotal: total => `共 ${total} 条数据`,
                },
                columns: [
                    // {
                    //     title: '文件ID',
                    //     dataIndex: 'fileId',
                    // },
                    {
                        title: '文件预览',
                        dataIndex: 'filePath',
                        scopedSlots: {customRender: 'image'}
                    },
                    {
                        title: '文件名称',
                        dataIndex: 'fileName',
                    },
                    {
                        title: '文件类型',
                        dataIndex: 'fileType',
                    },
                    {
                        title: '文件大小',
                        dataIndex: 'fileSize',
                    },
                    {
                        title: '文件后缀',
                        dataIndex: 'fileExt',
                    },
                    {
                        title: '上传时间',
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
                currentPage: 1,
                //文件上传
                // 上传中的文件
                uploading: []
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
                let fileName = this.searchFrom.getFieldValue('fileName')
                let searchParam = [
                    {column: 'file_name', type: 'like', value: fileName ? fileName : ""}
                ]
                FileApi.page({
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
                    this.$refs.update.init(id)
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
                        FileApi.remove([item['fileId']])
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
                        FileApi.remove(delIds)
                            .then((result) => {
                                app.$message.success(result.data.message, 1.5)
                                app.resetSearch()
                                app.ids=[]
                            }).catch(() => {
                            modal.destroy()
                        })
                    }
                })
            },

            // 事件: 上传文件之前
            beforeUpload(file, fileList) {
                const isLt1M = file.size / 1024 / 1024 < 1
                if (!isLt1M) {
                    this.$message.error('文件大小不能超出1MB')
                    return false
                }
                return true
            },
            /**
             * 事件: 自定义上传事件
             */
            onUpload(info) {
                this.uploadButtonLoading = true
                this.initLoading = true
                // 记录上传状态
                this.uploading.push(true)
                const beforeUploadCount = this.uploading.length
                // 构建上传参数
                const formData = new FormData()
                formData.append('file', info.file)
                // 开始上传
                FileApi.upload(formData)
                    .then(() => {
                        setTimeout(() => {
                            if (beforeUploadCount === this.uploading.length) {
                                this.uploading = []
                                this.uploadButtonLoading = false
                                this.initLoading = false
                                this.init()
                            }
                        }, 10)

                    })
                    .catch(() => {
                        this.uploadButtonLoading = false
                        this.initLoading = false
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

<style lang="less" scoped>
</style>

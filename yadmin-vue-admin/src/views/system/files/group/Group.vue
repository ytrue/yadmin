<template>
    <a-card>
        <!--查询 start-->
        <div style="margin-bottom: 18px;">
            <a-space class="operator">
                <a-button icon="form" type="primary" @click="addOrUpdateHandle()">新增</a-button>
                <!--                <a-button icon="delete" type="danger" @click="handleBatchDelete">批量删除</a-button>-->
            </a-space>
        </div>

        <div>
            <!-- :row-selection="{selectedRowKeys: selectedRowKeys, onSelectAll: onSelectAll, onSelect: onSelect}"-->
            <a-table
                    :columns="columns"
                    :data-source="tabData"
                    :customRow="tableClick"
                    :pagination="false"
                    expandRowByClick
            >
                <div slot="action" slot-scope="text, record">
                    <a-button
                            @click.stop.prevent="addOrUpdateHandle(record.groupId)"
                            size="small"
                            style="background-color: #108ee9;border-color:#108ee9"
                            icon="edit"
                            type="primary">编辑
                    </a-button>
                    <a-button
                            @click.stop.prevent="handleDelete(record)"
                            size="small"
                            type="danger"
                            icon="delete"
                            style="margin-left: 8px"
                    >删除
                    </a-button>
                </div>
            </a-table>
        </div>

        <!--表格end-->
        <add-or-update
                @handleSubmit="init"
                ref="addOrUpdate">
        </add-or-update>
    </a-card>
</template>

<script>
    import {treeDataTranslate} from '@/utils/util'
    import * as GroupApi from '@/services/system/files/group'
    import AddOrUpdate from "./modules/AddOrUpdate";

    export default {
        name: "Group",
        components: {AddOrUpdate},
        data() {
            return {
                //表格字段
                columns: [
                    // {
                    //     title: 'id',
                    //     dataIndex: 'groupId',
                    //     key: 'groupId'
                    // },
                    {
                        title: '名称',
                        dataIndex: 'name',
                    },
                    {
                        title: '排序',
                        dataIndex: 'sort',
                    },
                    {
                        title: '创建时间',
                        dataIndex: 'createTime',
                    },
                    {
                        title: '操作',
                        scopedSlots: {customRender: 'action'},
                        width: '230px',
                        align: 'center'
                    }
                ],
                //表格数据
                tabData: [],
                selectedRowKeys: [],
                expandRowByClick: false,
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                GroupApi.list().then(response => {
                    const menuData = response.data.data
                    menuData.forEach(item => {
                        item.key = item.groupId
                    })
                    this.tabData = treeDataTranslate(menuData, "groupId", "parentId")
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
             * 删除记录
             */
            handleDelete(item) {
                const app = this
                const modal = this.$confirm({
                    title: '您确定要删除该记录吗?',
                    content: '删除后不可恢复',
                    onOk() {
                        GroupApi.remove([item['groupId']])
                            .then((result) => {
                                app.$message.success(result.data.message, 1.5)
                                app.init("reset")
                            }).catch(() => {
                            modal.destroy()
                        })
                    }
                })
            },
            onSelectAll(selected) {
                if (selected) {
                    const tabData = this.tabData;
                    const arr = [];
                    setVal(tabData, arr);
                    this.selectedRowKeys = arr;
                } else {
                    this.selectedRowKeys = [];
                }

                function setVal(list, arr) {
                    list.forEach(v => {
                        arr.push(v.key);
                        if (v.children) {
                            setVal(v.children, arr);
                        }
                    });
                }
            },

            onSelect(record, selected) {
                const set = new Set(this.selectedRowKeys);
                const tabData = this.tabData;
                const key = record.key;
                if (selected) {
                    set.add(key);
                    record.children && setChildCheck(record.children);
                    setParentCheck(key);
                } else {
                    set.delete(key);
                    record.children && setChildUncheck(record.children);
                    setParentUncheck(key);
                }

                this.selectedRowKeys = Array.from(set);

                // 设置父级选择
                function setParentCheck(key) {
                    let parent = getParent(key);
                    if (parent) {
                        set.add(parent.key);
                        setParentCheck(parent.key);
                    }
                }

                // 设置父级取消，如果父级的子集有选择，则不取消
                function setParentUncheck(key) {
                    let childHasCheck = false,
                        parent = getParent(key);
                    if (parent) {
                        let childlist = parent.children;
                        childlist.forEach(function (v) {
                            if (set.has(v.key)) {
                                childHasCheck = true;
                            }
                        });
                        if (!childHasCheck) {
                            set.delete(parent.key);
                            setParentUncheck(parent.key);
                        }
                    }
                }

                // 获取当前对象的父级
                function getParent(key) {
                    for (let i = 0; i < tabData.length; i++) {
                        if (tabData[i].key === key) {
                            return null;
                        }
                    }
                    return _getParent(tabData);

                    function _getParent(list) {
                        let childlist,
                            isExist = false;
                        for (let i = 0; i < list.length; i++) {
                            if ((childlist = list[i].children)) {
                                childlist.forEach(function (v) {
                                    if (v.key === key) {
                                        isExist = true;
                                    }
                                });
                                if (isExist) {
                                    return list[i];
                                }
                                if (_getParent(childlist)) {
                                    return _getParent(childlist);
                                }
                            }
                        }
                    }
                }

                // 设置child全选
                function setChildCheck(list) {
                    list.forEach(function (v) {
                        set.add(v.key);
                        v.children && setChildCheck(v.children);
                    });
                }

                // 设置child取消
                function setChildUncheck(list) {
                    list.forEach(function (v) {
                        set.delete(v.key);
                        v.children && setChildUncheck(v.children);
                    });
                }
            },
            /** 点击a-table中的行后，展开或关闭其子行 */
            tableClick(record, index) {
                return {
                    style: {
                        cursor: 'pointer',
                    },
                    on: {
                        click: () => {
                            this.expandRowByClick = !this.expandRowByClick;
                        }
                    }
                }
            },
        }
    }
</script>

<style scoped>

</style>

<template>
  <a-card>
    <!--查询 start-->
    <div class="table-operator">
      <!-- 搜索板块 -->
      <a-row class="row-item-search">
        <a-form class="search-form" :form="searchFrom" layout="inline">
          <a-form-item label="用户名" refs="searchFromRefs">
            <a-input placeholder="请输入用户名称" v-decorator="['username']"/>
          </a-form-item>

          <a-form-item label="状态">
            <a-select v-decorator="['type',{initialValue: ''}]">
              <a-select-option value="">全部</a-select-option>
              <a-select-option value="OPT">正常</a-select-option>
              <a-select-option value="EX">异常</a-select-option>
            </a-select>
          </a-form-item>


          <a-form-item label="操作时间">
            <a-range-picker
                @change="onChangeDate"
                v-decorator="['createTime']"
                :show-time="{ hideDisabledOptions: true, defaultValue: [moment('00:00:00', 'HH:mm:ss'), moment('11:59:59', 'HH:mm:ss')]}"
                format="YYYY-MM-DD HH:mm:ss"/>

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
    <!--    :scroll="{ x: 2500 }"-->
    <a-table
        row-key="logId"
        :columns="columns"
        :pagination="pagination"
        @change="onChange"
        :loading="initLoading"
        :data-source="dataSource"
    >
      <!--这个额外显示-->
      <div slot="expandedRowRender" slot-scope="text, record">
        <table id="otherDetails">
          <tr>
            <td>浏览器</td>
            <td>{{ text.ua }}</td>
          </tr>
          <tr>
            <td>请求参数</td>
            <td>{{ text.params }}</td>
          </tr>
          <tr>
            <td>方法名</td>
            <td>{{ text.actionMethod }}</td>
          </tr>
          <tr>
            <td>类路径</td>
            <td>{{ text.classPath }}</td>
          </tr>
          <tr v-if="text.type !== 'OPT'" style="color: red">
            <td style="vertical-align: text-top;">异常描述</td>
            <td>{{ text.exDesc }}</td>
          </tr>
          <tr v-if="text.type !== 'OPT'" style="color: red">
            <td style="vertical-align: text-top;">异常详情</td>
            <td>{{ text.exDetail }}</td>
          </tr>
        </table>
      </div>

      <span slot="type" slot-scope="text, record">
              <a-tag v-if="record.type === 'OPT'" color="green">正常</a-tag>
              <a-tag v-else color="red">
                异常
              </a-tag>
             </span>>

      <span slot="consumingTime" slot-scope="text, record">
              <a-tag color="blue" style="min-width: 51px;text-align: center">
                  {{ record.consumingTime }}ms
              </a-tag>
             </span>>
    </a-table>

  </a-card>
</template>

<script>
import moment from 'moment'

import {page} from '@/services/system/auth/log'


export default {
  name: "Log",
  data() {
    return {
      searchButtonLoading: false,
      resetButtonLoading: false,
      //搜索表单
      searchFrom: this.$form.createForm(this),
      //初始化加载
      initLoading: true,
      //分页设置
      pagination: {
        total: 0,
        pageSize: 0,
        showTotal: total => `共 ${total} 条数据`,
      },
      advanced: true,
      selectedRows: [],
      //时间搜索
      createTime: [],
      //数据源
      dataSource: [],
      //表格
      columns: [
        {
          title: '操作人',
          dataIndex: 'username',
          width: 110
        },
        {
          title: 'IP',
          dataIndex: 'requestIp'
        },
        {
          title: '操作描述',
          dataIndex: 'description'
        },
        {
          title: '请求url',
          dataIndex: 'requestUri'
        },
        {
          title: '请求类型',
          dataIndex: 'httpMethod'
        },

        {
          title: '状态',
          dataIndex: 'type',
          scopedSlots: {customRender: 'type'}
        },
        {
          title: '消耗时间',
          dataIndex: 'consumingTime',
          scopedSlots: {customRender: 'consumingTime'}
        },
        {
          title: '操作时间',
          dataIndex: 'startTime',
          width: '200px'
        },

      ]
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    moment,
    /**
     *初始化数据
     */
    init(type = "") {
      this.buttonLoading(type, true)
      let username = this.searchFrom.getFieldValue('username')
      let type1 = this.searchFrom.getFieldValue('type')
      let searchParam = [
        {column: 'username', type: 'like', value: username ? username : ""},
        {column: 'type', type: 'eq', value: type1 ? type1 : ""},
        {
          column: 'start_time',
          type: 'betweenDate',
          value: this.createTime.length === 0 ? "" : this.createTime.toString()
        }
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
     * 重置搜索表单
     */
    resetSearch() {
      this.searchFrom.resetFields();
      this.createTime = []
      this.init("reset")
    },
    //时间改变
    onChangeDate(date, dateString) {
      this.createTime = dateString
    },
    /**
     * 分页处理
     * @param selectedRowKeys
     */
    onChange(selectedRowKeys) {
      this.currentPage = selectedRowKeys.current
      this.init()
    },
  }
}
</script>

<style scoped>
</style>

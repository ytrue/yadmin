<template>
  <div>
    <a-form-item label="商品规格" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <div v-if="true" class="form-item-help" style="line-height: 36px">
        <small>最多添加3个商品规格组，生成的SKU数量不能超出50个</small>
      </div>
      <!-- 规格组 -->
      <div class="spec-group" v-for="(item, index) in multiSpecData.specList" :key="index">
        <div class="spec-group-item clearfix">
          <a-input
              class="group-item-input"
              v-model="item.spec_name"
              placeholder="请输入规格名称"
              @change="onChangeSpecGroupIpt"
          />
          <a
              class="group-item-delete"
              href="javascript:;"
              @click="handleDeleteSpecGroup(index)"
          >删除规格组</a>
        </div>
        <div class="spec-value clearfix">
          <div class="spec-value-item" v-for="(itm, idx) in item.valueList" :key="idx">
            <a-input
                class="value-item-input"
                v-model="itm.spec_value"
                placeholder="请输入规格值"
                @change="onChangeSpecValueIpt"
            />
            <a-icon
                class="icon-close"
                theme="filled"
                type="close-circle"
                @click="handleDeleteSpecValue(index, idx)"
            />
          </div>
          <div class="spec-value-add">
            <a
                class="group-item-delete"
                href="javascript:;"
                @click="handleAddSpecValue(index)"
            >新增规格值</a>
          </div>
        </div>
      </div>
      <!-- 添加规格按钮 -->
      <a-button
          v-if="multiSpecData.specList.length < 3"
          class="spec-group-add-btn"
          icon="plus"
          @click="handleAddSpecGroup"
      >添加规格组</a-button>
    </a-form-item>
    <a-form-item
        v-show="multiSpecData.skuList.length"
        label="SKU列表"
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
    >
      <!-- 批量设置 -->
      <div v-if="multiSpecData.skuList.length > 1" class="sku-batch">
        <span class="title">批量设置:</span>
        <a-input-number
            v-model="multiSpecData.skuBatchForm.goods_price"
            placeholder="商品价格"
            :min="0.01"
            :precision="2"
        />
        <a-input-number
            v-model="multiSpecData.skuBatchForm.line_price"
            placeholder="划线价格"
            :min="0"
            :precision="2"
        />
        <a-input-number
            v-model="multiSpecData.skuBatchForm.stock_num"
            placeholder="库存数量"
            :min="0"
            :precision="0"
        />
        <a-input-number
            v-model="multiSpecData.skuBatchForm.goods_weight"
            placeholder="商品重量"
            :min="0"
            :precision="2"
        />
        <a-input v-model="multiSpecData.skuBatchForm.goods_sku_no" placeholder="sku编码" />
        <a-button @click="handleSkuBatch">立即设置</a-button>
      </div>
      <!-- sku列表table -->
      <a-table
          class="sku-list"
          :columns="multiSpecData.skuColumns"
          :dataSource="multiSpecData.skuList"
          :scroll="{ x: true }"
          :pagination="false"
          bordered
      >
        <!-- 预览图 -->
        <template slot="image" slot-scope="text, item">
          <SelectImage
              v-model="item.image_id"
              :defaultList="(item.image_id > 0 && item.image) ? [item.image] : []"
              :width="50"
          />
        </template>
        <!-- 商品价格 -->
        <template slot="goods_price" slot-scope="text, item">
          <a-input-number v-model="item.goods_price" size="small" :min="0.01" :precision="2" />
        </template>
        <!-- 划线价格 -->
        <template slot="line_price" slot-scope="text, item">
          <a-input-number v-model="item.line_price" size="small" :min="0" :precision="2" />
        </template>
        <!-- 库存数量 -->
        <template slot="stock_num" slot-scope="text, item">
          <a-input-number v-model="item.stock_num" size="small" :min="0" :precision="0" />
        </template>
        <!-- 商品重量 -->
        <template slot="goods_weight" slot-scope="text, item">
          <a-input-number v-model="item.goods_weight" size="small" :min="0" :precision="2" />
        </template>
        <!-- sku编码 -->
        <template slot="goods_sku_no" slot-scope="text, item">
          <a-input v-model="item.goods_sku_no" size="small" />
        </template>
      </a-table>
    </a-form-item>
  </div>
</template>

<script>
import PropTypes from 'ant-design-vue/es/_util/vue-types'
import MultiSpecModel from "./MultiSpecModel"
import SelectImage from '@/components/image/SelectImage'

export default {
  components: {
    SelectImage
  },
  props: {
    // 默认的规格列表
    defaultSpecList: PropTypes.array.def([]),
    // 默认的SKU列表
    defaultSkuList: PropTypes.array.def([])
  },
  data () {
    return {
      // 标签布局属性
      labelCol: { span: 3 },
      // 输入框布局属性
      wrapperCol: { span: 21 },
      // 商品多规格模型
      MultiSpecModel: new MultiSpecModel(),
      // MultiSpecModel: Object,
      multiSpecData: {
        // 规格列表
        specList: [],
        // SKU列表
        skuList: []
      }
    }
  },
  watch: {
    defaultSpecList (val) {
      if (val.length && this.MultiSpecModel.isEmpty()) {
        this.getData()
      }
    }
  },
  // 初始化数据
  created () {
    // 获取规格及SKU信息
    this.getData()
  },
  methods: {

    // 获取规格及SKU信息(展示)
    getData () {
      const { defaultSpecList, defaultSkuList } = this
      this.multiSpecData = this.MultiSpecModel.getData(defaultSpecList, defaultSkuList)
    },

    // 获取规格及SKU信息(表单提交)
    getFromSpecData () {
      return this.MultiSpecModel.getFromSpecData()
    },

    // 添加规格组
    handleAddSpecGroup () {
      if (this.checkSkuMaxNum()) {
        this.MultiSpecModel.handleAddSpecGroup()
      }
    },

    // 删除规格组
    handleDeleteSpecGroup (groupIndex) {
      const app = this
      const modal = this.$confirm({
        title: '您确定要删除该规格组吗?',
        content: '删除后不可恢复',
        onOk () {
          // 删除元素
          app.MultiSpecModel.handleDeleteSpecGroup(groupIndex)
          // 关闭对话框
          modal.destroy()
        }
      })
    },

    // 新增规格值
    handleAddSpecValue (groupIndex) {
      if (this.checkSkuMaxNum()) {
        this.MultiSpecModel.handleAddSpecValue(groupIndex)
      }
    },

    // 删除规格值
    handleDeleteSpecValue (groupIndex, valueIndex) {
      const app = this
      const modal = this.$confirm({
        title: '您确定要删除该规格值吗?',
        content: '删除后不可恢复',
        onOk () {
          // 删除元素
          app.MultiSpecModel.handleDeleteSpecValue(groupIndex, valueIndex)
          // 关闭对话框
          modal.destroy()
        }
      })
    },

    // 规格组输入框change事件
    onChangeSpecGroupIpt () {
      // 更新skuList
      this.MultiSpecModel.onUpdate(true)
    },

    // 规格值输入框change事件
    onChangeSpecValueIpt (event, itm) {
      // 更新skuList
      this.MultiSpecModel.onUpdate(true)
    },

    // 验证最大sku数量
    checkSkuMaxNum () {
      const skuList = this.multiSpecData.skuList
      if (skuList.length >= 50) {
        this.$message.error(`生成的sku列表数量不能大于50个，当前数量：${skuList.length}个`, 2.5)
        return false
      }
      return true
    },

    // 批量设置sku事件
    handleSkuBatch () {
      this.MultiSpecModel.handleSkuBatch()
    },

    // 验证多规格表单
    verifyForm () {
      if (!this.MultiSpecModel.verifyForm()) {
        this.$message.error(this.MultiSpecModel.getError(), 2)
        return false
      }
      return true
    }

  }
}

</script>
<style lang="less" scoped>
// 商品多规格
.spec-group {
  width: 895px;
  margin-bottom: 15px;
  line-height: normal;

  .ant-input {
    height: 28px;
    padding: 4px 11px;
    font-size: 13px;
    line-height: 1.5;

    &::placeholder {
      font-size: 13px;
    }
  }

  .form-item-help {
    line-height: 36px;
  }

  .spec-group-item {
    background: #f4f5f9;
    padding: 7px 12px;
    line-height: 28px;

    .group-item-input {
      float: left;
      width: 180px;
    }

    .group-item-delete {
      font-size: 12px;
      float: right;
    }
  }

  .spec-value {
    background: #fbfbfb;
    padding: 8px 35px;

    .spec-value-add,
    .spec-value-item {
      float: left;
      margin-bottom: 6px;
      margin-top: 6px;
    }

    .spec-value-add {
      height: 28px;
      line-height: 28px;

      a {
        font-size: 12px;
      }
    }

    .spec-value-item {
      position: relative;
      margin-right: 20px;

      .value-item-input {
        width: 186px;
      }

      &:hover {
        .icon-close {
          display: block;
        }
      }

      .icon-close {
        display: none;
        position: absolute;
        top: -8px;
        right: -8px;
        cursor: pointer;
        font-size: 16px;
        color: #7d7d7d;
      }
    }
  }
}

// 添加规格按钮
.spec-group-add-btn {
  font-size: 13px;
}

// 批量设置sku
.sku-batch {
  line-height: 40px;
  margin-bottom: 12px;
  .title {
    line-height: 28px;
    margin-right: 15px;
    font-size: 14px;
  }
  /deep/.ant-input,
  /deep/.ant-input-number {
    width: 120px;
    margin-right: 15px;
  }
  /deep/.ant-input {
    width: 140px;
  }
}

// sku列表
.sku-list {
  width: 895px;
  /deep/.ant-table-thead > tr > th,
  /deep/.ant-table-tbody > tr > td {
    white-space: nowrap;
  }
  /deep/.ant-table-tbody > tr > td {
    padding: 12px 18px;
  }

  /deep/.ant-input-sm,
  /deep/.ant-input-number-sm {
    height: 28px;
  }
  /deep/.ant-input-number-sm input {
    height: 26px;
  }
}
</style>

<template>
  <a-modal
      :title="!formId ? '新增' : '修改'"
      :width="1200"
      :visible="visible"
      :confirmLoading="confirmLoading"
      :maskClosable="false"

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
        <div class="tab-pane" v-show="tabKey === 0">
          <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input
                placeholder="请输入商品名称"
                v-decorator="['goods_name', {rules: [{required: true, min: 2, message: '请输入至少2个字符'}]}]"
            />
          </a-form-item>

          <a-form-item label="商品分类" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-tree-select
                :getPopupContainer="triggerNode => {return triggerNode.parentNode || document.body;}"
                v-model="selectCategoryId"
                :treeDefaultExpandAll="true"
                style="width: 100%"
                :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                :tree-data="categoryTreeData"
                placeholder="请选择商品分类"
                :replaceFields="categoryTreeFields"
                :showSearch="true"
            >
            </a-tree-select>
          </a-form-item>

          <a-form-item
              label="商品封面图"
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              extra="建议尺寸：750*750像素"
          >
            <SelectImage
                :initData="goodsImage"
                v-decorator="['goodsImage']"
            />
          </a-form-item>

          <a-form-item
              label="商品轮播图"
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              extra="建议尺寸：750*750像素, 最多上传10张, 可拖拽图片调整顺序"
          >
            <SelectImage
                :initData="goodsImages"
                v-decorator="['goodsImages']"
            />
          </a-form-item>

          <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input placeholder="请输入商品编码" v-decorator="['goods_no']"/>
          </a-form-item>

          <a-form-item label="配送模板" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-select
                :getPopupContainer="triggerNode => {return triggerNode.parentNode || document.body;}"
                show-search
                placeholder="请选择配送模板"
                option-filter-prop="children"
                v-decorator="['deliveryId', {rules: [{required: true, message: '请选择配送模板'}]}]">
              <a-select-option
                  v-for="(item, index) in templateList"
                  :key="index"
                  :value="item.deliveryId">{{ item.name }}
              </a-select-option>
            </a-select>
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
        <div class="tab-pane" v-show="tabKey === 1">
          <a-form-item label="规格类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-radio-group
                v-decorator="['spec_type', { initialValue: 10, rules: [{ required: true }] }]"
                @change="onForceUpdate()"
            >
              <a-radio :value="10">单规格</a-radio>
              <a-radio :value="20">多规格</a-radio>
            </a-radio-group>
          </a-form-item>
          <!-- 多规格的表单内容 -->
          <div v-show="form.getFieldValue('spec_type') === 20">
            <MultiSpec ref="MultiSpec"/>
          </div>
          <!-- 单规格的表单内容 -->
          <div v-show="form.getFieldValue('spec_type') === 10">
            <a-form-item
                label="商品价格"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="商品的实际购买金额，最低0.01"
            >
              <a-input-number
                  :min="0.01"
                  :precision="2"
                  v-decorator="['goods_price', { initialValue: 1, rules: [{ required: true, message: '请输入商品价格' }] }]"
              />
              <span class="ml-10">元</span>
            </a-form-item>
            <a-form-item
                label="划线价"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="划线价仅用于商品页展示"
            >
              <a-input-number :min="0" :precision="2" v-decorator="['line_price']"/>
              <span class="ml-10">元</span>
            </a-form-item>
            <a-form-item
                label="当前库存数量"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="商品的实际库存数量，为0时用户无法下单"
            >
              <a-input-number
                  :min="0"
                  :precision="0"
                  v-decorator="['stock_num', { initialValue: 100, rules:[{ required: true, message: '请输入库存数量' }] }]"
              />
              <span class="ml-10">件</span>
            </a-form-item>
            <a-form-item
                label="商品重量"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="商品的实际重量，用于计算运费"
            >
              <a-input-number
                  :min="0"
                  v-decorator="['goods_weight', { initialValue: 0, rules:[{ required: true, message: '请输入库存数量' }] }]"
              />
              <span class="ml-10">千克 (Kg)</span>
            </a-form-item>
          </div>
        </div>
        <!-- 规格/库存 end -->

      </div>
    </a-form>
  </a-modal>
</template>

<script>
import SelectImage from '@/components/image/SelectImage'
import * as templateApi from '@/services/mall/delivery/template'
import * as categoryApi from '@/services/mall/product/category'
import {treeDataTranslate} from '@/utils/util'
import MultiSpec from '@/components/sku/MultiSpec'

export default {
  name: "AddOrUpdate",
  components: {
    SelectImage,
    MultiSpec
  },
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
        span: 3
      },
      // 输入框布局属性
      wrapperCol: {
        span: 20
      },

      //自定义商品分类的tree结构
      categoryTreeFields: {
        children: 'children',
        title: 'name',
        key: 'categoryId',
        value: 'name'
      },
      //商品分类的tree数据
      categoryTreeData: [],
      //选择商品分类的id
      selectCategoryId: undefined,
      //商品封面图
      goodsImage: [],
      //商品图片集合
      goodsImages: [],
      //配送模板
      templateList: [],
    }
  },
  methods: {

    // 手动强制更新页面
    onForceUpdate (bool = false) {
      this.$forceUpdate()
      // bool为true时再执行一次 $forceUpdate, 特殊情况下需执行两次，原因如下：
      // 第一次执行 $forceUpdate时, 新元素绑定v-decorator无法获取到form.getFieldValue
      bool && setTimeout(() => {
        this.$forceUpdate()
      }, 10)
    },

    /**
     *数据初始化，等待多个ajax异步操作执行完了后执行的方法，async 配合 await
     */
    async init(id) {
      //   this.confirmLoading = true
      this.visible = true
      this.confirmLoading = false


      //商品分类
      await categoryApi.list().then((response) => {
        const {data} = response
        let categoryListData = data.data
        //第一级分类不可选择
        categoryListData.map((item, index) => {
          if (item.parentId === 0) {
            item.disabled = true;
          }
          return {...item, key: index}
        })
        this.categoryTreeData = treeDataTranslate(categoryListData, "categoryId", "parentId")
      })

      //获取配送模板
      await templateApi.list().then((response) => {
        const {data} = response
        this.templateList = data.data
      })
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

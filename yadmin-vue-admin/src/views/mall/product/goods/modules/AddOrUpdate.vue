<template>
  <a-modal
      :title="!formId ? '新增' : '修改'"
      :width="1200"
      :visible="visible"
      :confirmLoading="confirmLoading"
      :maskClosable="false"
      @ok="handleSubmit"
      @cancel="handleCancel"
      :bodyStyle="bodyStyle"
  >
    <a-spin :spinning="confirmLoading">
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
                  v-decorator="['goodsName', {rules: [{required: true, min: 2, message: '请输入至少2个字符'}]}]"
              />
            </a-form-item>

            <!--v-model="selectCategoryId"-->
            <a-form-item label="商品分类" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-tree-select
                  :getPopupContainer="triggerNode => {return triggerNode.parentNode || document.body;}"
                  v-decorator="['categoryId', {rules: [{required: true,message: '请选择商品分类'}]}]"
                  :treeDefaultExpandAll="true"
                  style="width: 100%"
                  :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                  :tree-data="categoryTreeData"
                  placeholder="请选择商品分类"
                  :replaceFields="categoryTreeFields"
                  :showSearch="true"
              >
              </a-tree-select>
              <div class="form-item-help">
                <router-link target="_blank" :to="{ path: '/mall/product/category' }">去新增</router-link>
                <a href="javascript:;" @click="onReloadCategoryList">刷新</a>
              </div>
            </a-form-item>

            <a-form-item
                label="商品封面图"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="建议尺寸：750*750像素"
            >
              <SelectImage
                  :initData="goodsImage"
                  v-decorator="['goodsImage',{rules: [{required: true, message: '请至少上传商品封面图'}]}]"
              />
            </a-form-item>

            <a-form-item
                label="商品轮播图"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="建议尺寸：750*750像素, 最多上传10张, 可拖拽图片调整顺序"
            >
              <SelectImage
                  multiple
                  :maxNum="10"
                  :initData="goodsImages"
                  v-decorator="['goodsImages',{rules: [{required: true, message: '请至少上传1张商品轮播图'}]}]"
              />
            </a-form-item>

            <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input placeholder="请输入商品编码" v-decorator="['goodsNo']"/>
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
              <div class="form-item-help">
                <router-link target="_blank" :to="{ path: '/mall/delivery/template' }">新增模板</router-link>
                <a href="javascript:;" @click="onReloadDeliveryList">刷新</a>
              </div>
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
                  v-decorator="['specType', { initialValue: 10, rules: [{ required: true }] }]"
                  @change="onForceUpdate()"
              >
                <a-radio :value="10">单规格</a-radio>
                <a-radio :value="20">多规格</a-radio>
              </a-radio-group>
            </a-form-item>
            <!-- 多规格的表单内容 -->
            <div v-show="form.getFieldValue('specType') === 20">
              <MultiSpec ref="MultiSpec"/>
            </div>
            <!-- 单规格的表单内容 -->
            <div v-show="form.getFieldValue('specType') === 10">
              <a-form-item
                  label="商品价格"
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  extra="商品的实际购买金额，最低0.01"
              >
                <a-input-number
                    :min="0.01"
                    :precision="2"
                    v-decorator="['goodsPrice', { initialValue: 1, rules: [{ required: true, message: '请输入商品价格' }] }]"
                />
                <span class="ml-10">元</span>
              </a-form-item>
              <a-form-item
                  label="划线价"
                  :labelCol="labelCol"
                  :wrapperCol="wrapperCol"
                  extra="划线价仅用于商品页展示"
              >
                <a-input-number :min="0" :precision="2" v-decorator="['linePrice']"/>
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
                    v-decorator="['stockNum', { initialValue: 100, rules:[{ required: true, message: '请输入库存数量' }] }]"
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
                    v-decorator="['goodsWeight', { initialValue: 0, rules:[{ required: true, message: '请输入库存数量' }] }]"
                />
                <span class="ml-10">千克 (Kg)</span>
              </a-form-item>
            </div>
          </div>
          <!-- 规格/库存 end -->

          <!-- 商品详情 start-->
          <div class="tab-pane" v-show="tabKey === 2">
            <a-form-item label="商品详情" :labelCol="labelCol" :wrapperCol="{span: 16}">
              <a-textarea type="textarea" v-decorator="['content']"/>
            </a-form-item>
          </div>
          <!-- 商品详情 end-->

          <!-- 更多设置 -->
          <div class="tab-pane" v-show="tabKey === 3">
            <a-form-item
                label="商品卖点"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="一句话简述，例如：此款商品美观大方 性价比较高 不容错过"
            >
              <a-input placeholder="请输入商品卖点" v-decorator="['sellingPoint']"/>
            </a-form-item>

            <a-form-item label="服务与承诺" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-select
                  v-if="serviceList"
                  mode="multiple"
                  v-decorator="['serviceIds', { initialValue: defaultServiceIds }]"
                  placeholder="请选择服务与承诺"
              >
                <a-select-option
                    v-for="(item, index) in serviceList"
                    :key="index"
                    :value="item.serviceId"
                >{{ item.name }}
                </a-select-option>
              </a-select>
              <div class="form-item-help">
                <router-link target="_blank" :to="{ path: '/mall/product/promise' }">去新增</router-link>
                <a href="javascript:;" @click="onReloadServiceList">刷新</a>
              </div>
            </a-form-item>

            <a-form-item
                label="初始销量"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
                extra="用户端展示的销量 = 初始销量 + 实际销量"
            >
              <a-input-number v-decorator="['salesInitial', {initialValue: 0}]"/>
            </a-form-item>
          </div>
        </div>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
import SelectImage from '@/components/image/SelectImage'
import * as templateApi from '@/services/mall/delivery/template'
import * as categoryApi from '@/services/mall/product/category'
import * as promiseApi from '@/services/mall/product/promise'
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
      //商品承诺
      serviceList: [],
      //默认的商品承诺
      defaultServiceIds: [],
    }
  },
  methods: {

    // 手动强制更新页面
    onForceUpdate(bool = false) {
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



      this.visible = true
      // 获取分类列表
      await this.getCategoryList()
      // 获取运费模板列表
      await this.getDeliveryList()
      // 获取服务与承诺
      await this.getServiceList().then(() => {
        this.setDefaultServiceIds()
        this.confirmLoading = false
      })
    },


    /**
     * 确认按钮
     */
    handleSubmit(e) {
      // if (this.confirmLoading === true) return
      e.preventDefault()
      const {form: {validateFields}} = this
      // 表单验证
      validateFields((errors, values) => {
        // 提交到后端api
        if (errors === null) {


          // 验证多规格
          if (values.specType === 20) {
            const MultiSpec = this.$refs.MultiSpec
            if (!MultiSpec.verifyForm()) {
              this.tabKey = 1
              return false
            }
            // 记录多规格数据
            values.specData = MultiSpec.getFromSpecData()
          }
          console.log(values)

        }
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

    // 获取分类列表
    getCategoryList() {
      return categoryApi.list().then((response) => {
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
        console.log(1)
      })
    },
    // 获取运费模板列表
    getDeliveryList() {
      return templateApi.list().then((response) => {
        const {data} = response
        this.templateList = data.data
        console.log(2)
      })
    },
    // 获取服务与承诺
    getServiceList() {
      return promiseApi.list().then((response) => {
        const {data} = response
        this.serviceList = data.data
        console.log(3)
      })
    },

    // 默认的商品服务与承诺
    setDefaultServiceIds() {
      // 服务与承诺列表
      const serviceList = this.serviceList
      const defaultServiceItems = serviceList.filter(item => item.isDefault)
      this.defaultServiceIds = defaultServiceItems.map(item => item.serviceId)
    },


    // 刷新分类列表
    onReloadCategoryList() {
      this.confirmLoading = true
      this.getCategoryList().then(() => {
        this.confirmLoading = false
      })
    },

    // 刷新服务与承诺列表
    onReloadServiceList() {
      this.confirmLoading = true
      this.getServiceList().then(() => {
        this.confirmLoading = false
      })
    },

    // 刷新配送模板列表
    onReloadDeliveryList() {
      this.confirmLoading = true
      this.getDeliveryList().then(() => {
        this.confirmLoading = false
      })
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


.form-item-help {
  font-size: 12px;
  // line-height: 2;
  line-height: 1.5;
  padding-top: 4px;
  min-height: 22px;
  margin-top: -2px;
  transition: color 0.3s cubic-bezier(0.215, 0.61, 0.355, 1);

  a {
    margin: 0 3px;
  }

  p.extra,
  small {
    color: rgba(0, 0, 0, 0.45);
    font-size: 13px !important;
  }

  p.extra {
    margin-bottom: 5px;
  }
}
</style>

<template>
  <div class="common-layout">
    <div class="content">
      <div class="top">
        <div class="header">
          <img alt="logo" class="logo" src="~@/assets/img/logo.png"/>
          <span class="title">yadmin</span>
        </div>
        <div class="desc">欢迎来到 yadmin chat</div>
      </div>
      <div class="login">
        <a-form @submit="onSubmit" :form="form">
          <a-tabs size="large" :tabBarStyle="{textAlign: 'center'}" style="padding: 0 2px;">
            <a-tab-pane tab="账户密码登录" key="1">
              <a-alert type="error" :closable="true" v-show="error" :message="error" showIcon
                       style="margin-bottom: 24px;"/>
              <a-form-item>
                <a-input
                  autocomplete="autocomplete"
                  size="large"
                  placeholder="请输入账户名"
                  v-decorator="['name', {rules: [{ required: true, message: '请输入账户名', whitespace: true}]}]"
                >
                  <a-icon slot="prefix" type="user"/>
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-input
                  size="large"
                  placeholder="请输入密码"
                  autocomplete="autocomplete"
                  type="password"
                  v-decorator="['password', {rules: [{ required: true, message: '请输入密码', whitespace: true}]}]"
                >
                  <a-icon slot="prefix" type="lock"/>
                </a-input>
              </a-form-item>
            </a-tab-pane>
            <a-tab-pane tab="手机号登录" key="2">
              <a-form-item>
                <a-input size="large" placeholder="请输入手机号">
                  <a-icon slot="prefix" type="mobile"/>
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-row :gutter="8" style="margin: 0 -4px">
                  <a-col :span="16">
                    <a-input size="large" placeholder="请输入验证码">
                      <a-icon slot="prefix" type="mail"/>
                    </a-input>
                  </a-col>
                  <a-col :span="8" style="padding-left: 4px">
                    <a-button style="width: 100%" class="captcha-button" size="large">获取验证码</a-button>
                  </a-col>
                </a-row>
              </a-form-item>
            </a-tab-pane>
          </a-tabs>
          <div>
            <a-checkbox :checked="true">自动登录</a-checkbox>
            <a style="float: right">忘记密码</a>
          </div>
          <a-form-item>
            <a-button :loading="logging" style="width: 100%;margin-top: 24px" size="large" htmlType="submit"
                      type="primary">登录
            </a-button>
          </a-form-item>
          <div>
            <router-link style="float: right" to="/system/auth">注册账户</router-link>
          </div>
        </a-form>
      </div>
    </div>
  </div>
</template>

<script>
import 'ant-design-vue/dist/antd.less'
import { login1 } from '@/api/login'
import { setAuthorization } from '@/utils/request'

export default {
  name: 'Login',
  data () {
    return {
      logging: false,
      error: '',
      form: this.$form.createForm(this)
    }
  },
  methods: {
    onSubmit (e) {
      e.preventDefault()
      this.form.validateFields((err) => {
        if (!err) {
          this.logging = true
          const name = this.form.getFieldValue('name')
          const password = this.form.getFieldValue('password')
          login1(name, password).then(this.afterLogin).finally(() => {
            this.logging = false
          })
        }
      })
    },
    afterLogin (res) {
      this.logging = false
      const loginRes = res.data
      if (loginRes.code === 200) {
        setAuthorization({
          token: loginRes.data.access_token,
          expireAt: new Date(new Date().getTime() + (loginRes.data.expires_in * 100))
        })
        this.$router.push('/')
        this.$message.success(loginRes.message, 3)
      } else {
        this.$message.error(loginRes.message, 3)
      }
    }
  }
}
</script>

<style scoped>

.common-layout {
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow: auto;
  background-color: #f0f2f5;
  background-image: url('https://gw.alipayobjects.com/zos/rmsportal/TVYTbAXWheQpRcWDaDMu.svg');
  background-repeat: no-repeat;
  background-position-x: center;
  background-position-y: 110px;
  background-size: 100%;
}

.common-layout .content {
  padding: 32px 0;
  flex: 1;
}

@media (min-width: 768px) {
  .common-layout .content {
    padding: 112px 0 24px;
  }
}

.top {
  text-align: center;
}

.top .header {
  height: 44px;
  line-height: 44px;
}

.top .header a {
  text-decoration: none;
}

.top .header .logo {
  height: 44px;
  vertical-align: top;
  margin-right: 16px;
}

.top .header .title {
  font-size: 33px;
  color: rgba(0, 0, 0, 0.85);
  font-family: 'Myriad Pro', 'Helvetica Neue', Arial, Helvetica, sans-serif;
  font-weight: 600;
  position: relative;
  top: 2px;
}

.top .desc {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.45);
  margin-top: 12px;
  margin-bottom: 40px;
}

.login {
  width: 368px;
  margin: 0 auto;
}

@media screen and (max-width: 576px) {
  .login {
    width: 95%;
  }
}

@media screen and (max-width: 320px) {
  .login .captcha-button {
    font-size: 14px;
  }
}

.login .icon {
  font-size: 24px;
  color: rgba(0, 0, 0, 0.45);
  margin-left: 16px;
  vertical-align: middle;
  cursor: pointer;
  transition: color 0.3s;
}

.login .icon:hover {
  color: #3eaf7c;
}
</style>

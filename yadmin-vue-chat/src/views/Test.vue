<template>
  <div>
    <lemon-imui
      class="screen-centered"
      @pull-messages="handlePullMessages"
      :user='user'
      ref='IMUI'
    >
    </lemon-imui>
  </div>
</template>

<script>
import * as contactsApi from '@/api/contacts'

export default {
  name: 'Test',
  data () {
    return {
      // 初始化登录用户的信息
      user: {
        // id
        id: 1,
        // 用户名
        displayName: 'yadmin',
        // 头像
        avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif'
      },
      // 联系人列表
      contactsListData: []
    }
  },
  mounted () {
    const { IMUI } = this.$refs
    // 初始化联系人
    this.initContacts(IMUI)
  },
  methods: {
    /**
     * 初始化联系人
     */
    initContacts (IMUI) {
      contactsApi.getMyContact().then((response) => {
        const { data } = response.data
        this.contactsListData = data
        IMUI.initContacts(this.contactsListData)
      })
    },
    /**
     * 消息初始化
     * @param contact
     * @param next
     * @param instance
     */
    handlePullMessages (contact, next, instance) {
    }
  }
}
</script>

<style scoped>

* {
  margin: 0;
  padding: 0;
}

/*设置聊天框居中*/
.screen-centered {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
}
</style>

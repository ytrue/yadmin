<template>
  <div>
    <lemon-imui
      :width="width"
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
import * as recordApi from '@/api/record'
import emojiData from '@/database/emoji'

const getTime = () => {
  return new Date().getTime()
}
const generateRandId = () => {
  return Math.random()
    .toString(36)
    .substr(-8)
}
const generateRandWord = () => {
  return Math.random()
    .toString(36)
    .substr(2)
}
const generateMessage = (toContactId = '', fromUser) => {
  if (!fromUser) {
    fromUser = {
      id: 'system',
      displayName: '系统测试',
      avatar: 'http://upload.qqbodys.com/allimg/1710/1035512943-0.jpg'
    }
  }
  return {
    id: generateRandId(),
    status: 'succeed',
    type: 'text',
    sendTime: getTime(),
    content: generateRandWord(),
    toContactId,
    fromUser
  }
}

export default {
  name: 'Test',
  data () {
    return {
      // 聊天窗口的宽度
      width: '900px',
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
        // 初始化表情，这个要改是从数据库获取，目前线虫前端获取
        IMUI.initEmoji(emojiData)
      })
    },
    /**
     * 消息初始化
     * @param contact
     * @param next
     * @param instance
     */
    handlePullMessages (contact, next, instance) {
      // 这里去获得数据
      let isEnd = false
      setTimeout(() => {
        recordApi.page().then((response) => {
          const { data } = response.data
          // 返回当前聊天窗口的所有消息的数量+从服务器请求数据的长度，怎么去获取分页的长度呢？
          if (instance.getCurrentMessages().length + data.length > 11) {
            isEnd = true
          }
          next(data, isEnd)
        })
      }, 200)
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

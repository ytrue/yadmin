import { request, METHOD } from '@/utils/request'

const apiPath = '/chat/contact'

/**
 *获得当前用户侧栏聊天消息列表，限制是前150条
 * @returns {Promise<*>}
 */
export async function getMySidebarMessage () {
  return request(`${apiPath}/message`, METHOD.GET)
}

/**
 * 获得当前用户的联系人
 * @returns {Promise<*>}
 */
export async function getMyContact () {
  return request(`${apiPath}/list`, METHOD.GET)
}

/**
 * 获取我的信息
 * @returns {Promise<*>}
 */
export async function myInfo () {
  return request(`${apiPath}/my`, METHOD.GET)
}

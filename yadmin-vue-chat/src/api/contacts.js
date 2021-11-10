import { METHOD, request } from '@/utils/request'

const apiPath = '/chat/contact'

/**
 * 获得当前用户的联系人
 * @returns {Promise<*>}
 */
export function getMyContact () {
  return request(`${apiPath}/list`, METHOD.GET)
}

/**
 * 获取我的信息
 * @returns {Promise<*>}
 */
export function myInfo () {
  return request(`${apiPath}/my`, METHOD.GET)
}

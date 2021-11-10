import { request, METHOD } from '@/utils/request'

const apiPath = '/chat/record'

/**
 * 聊天信息
 * @returns {Promise<*>}
 */
export function page () {
  return request(`${apiPath}/page`, METHOD.GET)
}

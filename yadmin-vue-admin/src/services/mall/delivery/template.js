import {request, METHOD} from '@/utils/request'

const apiPath = '/mall/delivery'

/**
 * 获得列表数据
 * @returns {Promise<*>}
 */
export async function page(data) {
    return request(`${apiPath}/page`, METHOD.POST, data)
}


/**
 * 配送模板全部列表
 * @returns {*}
 */
export function list() {
    return request(`${apiPath}/list`, METHOD.GET)
}


/**
 * 新增或者編輯
 * @param params
 * @returns {*}
 */
export function saveAndUpdate(params) {
    let method = params.deliveryId ? METHOD.PUT : METHOD.POST
    return request(apiPath, method, params)
}

/**
 * 获得信息
 * @param id
 * @returns {*}
 */
export function info(id) {
    return request(`${apiPath}/${id}/info`, METHOD.GET)
}


/**
 * 删除
 * @param data
 * @returns {*}
 */
export function remove(data) {
    return request(apiPath, METHOD.DELETE, data)
}

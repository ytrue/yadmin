import {request, METHOD} from '@/utils/request'

const apiPath = '/mall/goodsPromise'


/**
 * 获得列表数据
 * @returns {Promise<*>}
 */
export async function page(data) {
    return request(`${apiPath}/page`, METHOD.POST, data)
}

/**
 * 获得列表数据
 * @returns {Promise<*>}
 */
export async function list(data = {}) {
    return request(`${apiPath}/list`, METHOD.GET, data)
}


/**
 * 新增或者編輯
 * @param params
 * @returns {*}
 */
export function saveAndUpdate(params) {
    let method = params.serviceId ? METHOD.PUT : METHOD.POST
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
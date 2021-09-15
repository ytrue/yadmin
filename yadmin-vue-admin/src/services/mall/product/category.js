import {request, METHOD} from '@/utils/request'

const apiPath = '/mall/goodsCategory'

/**
 * 获得列表数据
 * @returns {Promise<*>}
 */
export async function list(data = {}) {
    return request(`${apiPath}/list`, METHOD.POST, data)
}


/**
 * 新增或者編輯
 * @param params
 * @returns {*}
 */
export function saveAndUpdate(params) {
    let method = params.categoryId ? METHOD.PUT : METHOD.POST
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



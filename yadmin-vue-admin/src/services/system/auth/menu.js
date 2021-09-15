import {request, METHOD} from '@/utils/request'

const apiPath = '/sys/menu'

/**
 * 菜单全部列表
 * @returns {*}
 */
export function table() {
    return request(`${apiPath}/table`, METHOD.GET)
}


/**
 * 不获得4级
 * @returns {*}
 */
export function list() {
    return request(`${apiPath}/list`, METHOD.GET)
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
 * 新增或者編輯
 * @param params
 * @returns {*}
 */
export function saveAndUpdate(params) {
    let method = params.menuId ? METHOD.PUT : METHOD.POST
    return request(apiPath, method, params)
}


/**
 * 删除
 * @param data
 * @returns {*}
 */
export function remove(data) {
    return request(apiPath, METHOD.DELETE, data)
}

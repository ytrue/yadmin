import {request, METHOD} from '@/utils/request'

const apiPath = '/sys/role'

/**
 * 角色全部列表
 * @returns {*}
 */
export function roleList() {
    return request(`${apiPath}/list`, METHOD.GET)
}


/**
 * 获得列表数据
 * @returns {Promise<*>}
 */
export async function page(data) {
    return request(`${apiPath}/page`, METHOD.POST, data)
}

/**
 * 新增或者編輯
 * @param params
 * @returns {*}
 */
export function saveAndUpdate(params) {
    let method = params.roleId ? METHOD.PUT : METHOD.POST
    return request(apiPath, method, params)
}

/**
 * 获得信息
 * @param id
 * @returns {*}
 */
export function getUserById(id) {
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

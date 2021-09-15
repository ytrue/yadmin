import {request, METHOD} from '@/utils/request'

const apiPath = '/sys/attachment'

/**
 * 获得列表数据
 * @returns {Promise<*>}
 */
export async function page(data) {
    return request(`${apiPath}/page`, METHOD.POST, data)
}


/**
 * 文件上传
 * @param file
 * @return {Promise<*>}
 */
export async function upload(file) {

    let config = {headers: {'Content-Type': 'multipart/form-data'}}
    return request(`${apiPath}/upload`, METHOD.POST, file, config)
}

/**
 * 移动图片分组
 * @param params
 */
export function moveGroup(params) {
    return request(`${apiPath}/move`, METHOD.POST, params)
}

/**
 * 删除图片
 * @param data
 * @returns {*}
 */
export function remove(data) {
    return request(apiPath, METHOD.DELETE, data)
}


/**
 * 編輯
 * @param params
 * @returns {*}
 */
export function update(params) {
    return request(apiPath, METHOD.PUT, params)
}


/**
 * 获得信息
 * @param id
 * @returns {*}
 */
export function info(id) {
    return request(`${apiPath}/${id}/info`, METHOD.GET)
}


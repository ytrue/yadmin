import {request, METHOD} from '@/utils/request'

const apiPath = '/files/setting'

/**
 * 获得信息
 * @param id
 * @returns {*}
 */
export function info(id) {
    return request(apiPath, METHOD.GET)
}


/**
 * 修改
 * @param params
 * @return {*}
 */
export function update(params) {
    return request(apiPath, METHOD.PUT, params)
}

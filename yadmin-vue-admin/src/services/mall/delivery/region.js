import {request, METHOD} from '@/utils/request'

const apiPath = '/mall/region'

/**
 * 获得列表数据
 * @returns {Promise<*>}
 */
export async function tree() {
    return request(`${apiPath}/tree`, METHOD.GET)
}

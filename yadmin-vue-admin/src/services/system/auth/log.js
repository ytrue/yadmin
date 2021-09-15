import {request, METHOD} from '@/utils/request'

const apiPath = '/sys/log';


/**
 * 获得列表数据
 * @param data
 * @return {Promise<*>}
 */
export async function page(data) {
    return request(`${apiPath}/page`, METHOD.POST, data)
}

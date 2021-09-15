import * as Api from '@/services/mall/delivery/region'

/**
 * 商品分类 model类
 * RegionModel
 */
export default {

    // 从服务端获取全部地区数据(树状)
    getTreeDataFromApi() {
        return new Promise((resolve, reject) => {
            Api.tree().then(result => {
                resolve(result.data.data)
            })
        })
    },

    // 获取所有地区(树状)
    getTreeData() {
        return new Promise((resolve, reject) => {
            this.getTreeDataFromApi().then(list => {
                resolve(list)
            })
        })
    },

    // 获取所有地区的总数
    getCitysCount() {
        return new Promise((resolve, reject) => {
            // 获取所有地区(树状)
            this.getTreeData().then(data => {
                const cityIds = []
                // 遍历省份
                for (const pidx in data) {
                    const province = data[pidx]
                    // 遍历城市
                    for (const cidx in province.city) {
                        const cityItem = province.city[cidx]
                        cityIds.push(cityItem.id)
                    }
                }
                resolve(cityIds.length)
            })
        })
    }

}

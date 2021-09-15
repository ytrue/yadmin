import enquireJs from 'enquire.js'

export function isDef(v) {
    return v !== undefined && v !== null
}

/**
 * Remove an item from an array.
 */
export function remove(arr, item) {
    if (arr.length) {
        const index = arr.indexOf(item)
        if (index > -1) {
            return arr.splice(index, 1)
        }
    }
}

export function isRegExp(v) {
    return _toString.call(v) === '[object RegExp]'
}

export function enquireScreen(call) {
    const handler = {
        match: function () {
            call && call(true)
        },
        unmatch: function () {
            call && call(false)
        }
    }
    enquireJs.register('only screen and (max-width: 767.99px)', handler)
}


/**
 * 树形数据转换
 * @param {*} data
 * @param {*} id
 * @param {*} pid
 */
export function treeDataTranslate(data, id = 'id', pid = 'parentId') {
    let res = []
    let temp = {}
    for (let i = 0; i < data.length; i++) {
        temp[data[i][id]] = data[i]
    }
    for (let k = 0; k < data.length; k++) {
        if (temp[data[k][pid]] && data[k][id] !== data[k][pid]) {
            if (!temp[data[k][pid]]['children']) {
                temp[data[k][pid]]['children'] = []
            }
            if (!temp[data[k][pid]]['_level']) {
                temp[data[k][pid]]['_level'] = 1
            }
            data[k]['_level'] = temp[data[k][pid]]._level + 1
            temp[data[k][pid]]['children'].push(data[k])
        } else {
            res.push(data[k])
        }
    }
    return res
}


// 防抖
// 首次运行时把定时器赋值给一个变量， 第二次执行时，
// 如果间隔没超过定时器设定的时间则会清除掉定时器，
// 重新设定定时器， 依次反复， 当我们停止下来时，
// 没有执行清除定时器， 超过一定时间后触发回调函数。
export function debounce (fun, delay) {
    return function (args) {
        // 获取函数的作用域和变量
        const that = this
        const _args = args
        // 每次事件被触发，都会清除当前的timeer，然后重写设置超时调用
        clearTimeout(fun.id)
        fun.id = setTimeout(function () {
            fun.call(that, _args)
        }, delay)
    }
}

/**
 * 判断是否为空对象
 * @param {*} object 源对象
 */
export function isEmptyObject (object) {
    return Object.keys(object).length === 0
}


/**
 * 判断是否为空
 * @param {*} object 源对象
 */
export function isEmpty (value) {
    if (isArray(value)) {
        return value.length === 0
    }
    if (isObject(value)) {
        return isEmptyObject(value)
    }
    return !value
}


/**
 * 判断是否为对象
 * @param {*} object
 */
export function isObject (object) {
    return Object.prototype.toString.call(object) === '[object Object]'
}


/**
 * 判断是否为对象
 * @param {*} array
 */
export function isArray (array) {
    return Object.prototype.toString.call(array) === '[object Array]'
}

/**
 * 判断是否在数组中
 * @param {*} search
 * @param {*} array
 */
export function inArray (search, array) {
    return array.includes(search)
}


const _toString = Object.prototype.toString

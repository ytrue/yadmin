module.exports = {
  css: {
    loaderOptions: {
      less: {
        lessOptions: {
          // If you are using less-loader@5 please spread the lessOptions to options directly
          modifyVars: {
            'primary-color': '#3eaf7c', // 全局主色
            'link-color': '#3eaf7c ' // 链接色
          },
          javascriptEnabled: true
        }
      }
    }
  }
}

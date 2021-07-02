package com.ytrue.yadmin.oss.parameter.cloud;


import com.ytrue.yadmin.oss.parameter.properties.BaseProperties;
import org.springframework.beans.factory.InitializingBean;


/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 模板方法设计模式
 */
public abstract class AbstractUpload implements InitializingBean {

    /**
     * 上传
     *
     * @param data
     * @param fileName
     * @return
     */
    public String upload(byte[] data, String fileName) {
        throw new UnsupportedOperationException();
    }


    /**
     * 手动传参数
     *
     * @param properties
     * @param data
     * @param fileName
     * @return
     */
    public String upload(BaseProperties properties, byte[] data, String fileName) {
        throw new UnsupportedOperationException();
    }


}

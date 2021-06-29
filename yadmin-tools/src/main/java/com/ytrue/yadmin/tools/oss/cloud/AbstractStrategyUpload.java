package com.ytrue.yadmin.tools.oss.cloud;


import com.ytrue.yadmin.tools.oss.dto.UploadSetting;
import org.springframework.beans.factory.InitializingBean;


/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 模板方法设计模式
 */
public abstract class AbstractStrategyUpload implements InitializingBean {

    /**
     * 上传
     *
     * @param uploadSetting
     * @param data
     * @param fileName
     * @return
     */
    public String upload(UploadSetting uploadSetting, byte[] data, String fileName) {
        throw new UnsupportedOperationException();
    }

}

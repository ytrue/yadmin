package com.ytrue.yadmin.modules.generator.service;

import com.ytrue.yadmin.modules.generator.model.GenTableInfo;

/**
 * @author ytrue
 * @date 2022/5/27 17:25
 * @description GeneratorService
 */
public interface GeneratorService {


    /**
     * 生成代码
     *
     * @param genTableInfo
     */
    void generatorCode(GenTableInfo genTableInfo);
}

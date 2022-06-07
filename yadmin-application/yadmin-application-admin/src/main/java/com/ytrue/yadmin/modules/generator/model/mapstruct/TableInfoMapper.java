package com.ytrue.yadmin.modules.generator.model.mapstruct;

import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.vo.TableInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author ytrue
 * @date 2022/5/28 14:30
 * @description TableInfoMapper
 */
@Mapper(componentModel = "spring")
public interface TableInfoMapper {

    /**
     * Entity转VO
     *
     * @param tableInfo
     * @return
     */
    TableInfoVO toVo(GenTableInfo tableInfo);
}

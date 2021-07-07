package com.ytrue.yadmin.modules.system.service.mapstruct;

import com.ytrue.yadmin.base.BaseMapper;
import com.ytrue.yadmin.log.entity.OptLogDTO;
import com.ytrue.yadmin.modules.system.model.SysLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author ytrue
 * @date 2021/7/6 16:46
 * @description bean转化
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogManager extends BaseMapper<OptLogDTO, SysLog> {

}

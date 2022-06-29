package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.enums.ResponseCode;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.AssertUtils;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenBaseClass;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.dto.request.ImportTableRequest;
import com.ytrue.yadmin.modules.generator.model.mapstruct.TableInfoMapper;
import com.ytrue.yadmin.modules.generator.model.vo.TableInfoVO;
import com.ytrue.yadmin.modules.generator.service.GenTableInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author ytrue
 * @date 2022/6/13 16:19
 * @description GenTableInfoController
 */
@RestController
@RequestMapping("gen/tableInfo")
@AllArgsConstructor
@Api(tags = "表信息管理")
public class GenTableInfoController {
    private final GenTableInfoService genTableInfoService;
    private final TableInfoMapper tableInfoMapper;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public ApiResultResponse<IPage<TableInfoVO>> page(@RequestBody QueryEntity<GenTableInfo> queryEntity) {
        IPage<TableInfoVO> page = genTableInfoService
                .page(queryEntity.getPage(), queryEntity.getQueryModel().orderByDesc(GenTableInfo::getId))
                .convert(tableInfoMapper::toVo);
        return ApiResultResponse.success(page);

    }

    @PutMapping
    @ApiOperation("修改")
    public ApiResultResponse<Object> update(@Valid @RequestBody GenTableInfo genTableInfo) {
        genTableInfoService.updateById(genTableInfo);
        return ApiResultResponse.success();
    }

    @GetMapping("detail/{id}")
    @ApiOperation("详情")
    public ApiResultResponse<GenTableInfo> detail(@PathVariable("id") Long id) {
        GenTableInfo data = genTableInfoService.getById(id);
        AssertUtils.notNull(data, ResponseCode.DATA_NOT_FOUND);
        return ApiResultResponse.success(data);
    }


    @GetMapping("list/{id}")
    @ApiOperation("获取数据源中所有表")
    public ApiResultResponse<List<GenTableInfo>> getDataSourceTableList(@PathVariable("id") Long id) {
        List<GenTableInfo> dataSourceTables = genTableInfoService.getDataSourceTables(id);
        return ApiResultResponse.success(dataSourceTables);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        genTableInfoService.removeBatchTableAndFieldByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }

    @PostMapping("import")
    @ApiOperation("导入表")
    public ApiResultResponse<Object> importTable(@RequestBody ImportTableRequest importTableRequest) {
        genTableInfoService.importTable(importTableRequest);
        return ApiResultResponse.success();
    }


}

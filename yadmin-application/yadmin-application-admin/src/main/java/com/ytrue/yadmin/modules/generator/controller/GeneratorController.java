package com.ytrue.yadmin.modules.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.core.utils.ApiResultResponse;
import com.ytrue.yadmin.core.utils.query.QueryEntity;
import com.ytrue.yadmin.modules.generator.model.GenTableInfo;
import com.ytrue.yadmin.modules.generator.model.mapstruct.TableInfoMapper;
import com.ytrue.yadmin.modules.generator.model.vo.TableInfoVO;
import com.ytrue.yadmin.modules.generator.service.GenTableInfoService;
import com.ytrue.yadmin.modules.generator.service.GeneratorService;
import com.ytrue.yadmin.modules.generator.model.dto.TableInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author ytrue
 * @date 2022/5/27 11:31
 * @description GeneratorController
 */
@RestController
@RequestMapping("gen")
@AllArgsConstructor
@Api(tags = "代码生成")
public class GeneratorController {

    private final GenTableInfoService genTableInfoService;
    private final GeneratorService generatorService;
    private final TableInfoMapper tableInfoMapper;

    @PostMapping("generator/page")
    @ApiOperation("分页查询")
    public IPage<TableInfoVO> page(@RequestBody QueryEntity<GenTableInfo> queryEntity) {
        return genTableInfoService
                .page(queryEntity.getPage(), queryEntity.getQueryModel().orderByDesc(GenTableInfo::getId))
                .convert(tableInfoMapper::toVo);

    }


    @DeleteMapping("generator")
    @ApiOperation("删除")
    public ApiResultResponse<Object> delete(@RequestBody Long[] ids) {
        genTableInfoService.removeBatchByIds(Arrays.asList(ids));
        return ApiResultResponse.success();
    }

    @GetMapping("datasource/table/list/{id}")
    @ApiOperation("获取数据源中所有表")
    public ApiResultResponse<List<TableInfoDTO>> getDataSourceTableList(@PathVariable("id") Long id) {
        List<TableInfoDTO> dataSourceTables = genTableInfoService.getDataSourceTables(id);
        return ApiResultResponse.success(dataSourceTables);
    }

}

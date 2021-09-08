package com.ytrue.yadmin.modules.mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ytrue.yadmin.log.annotation.SysLog;
import com.ytrue.yadmin.model.mall.setting.Delivery;
import com.ytrue.yadmin.modules.mall.service.DeliveryService;
import com.ytrue.yadmin.search.SearchModel;
import com.ytrue.yadmin.vo.DeliveryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ytrue
 * @date 2021/9/6 17:22
 * @description 配送模板
 */
@Api(tags = "配送模板")
@RestController
@AllArgsConstructor
@RequestMapping("mall/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public IPage<Delivery> page(@RequestBody SearchModel<Delivery> searchModel) {
        return deliveryService
                .page(
                        searchModel.getPage(),
                        searchModel.getQueryModel()
                                .lambda()
                                .orderByDesc(Delivery::getSort)
                                .orderByDesc(Delivery::getDeliveryId));
    }


    @SysLog
    @PostMapping
    @ApiOperation("保存配送模板")
    public void save(@Valid @RequestBody DeliveryVO deliveryVO) {
        deliveryService.saveDelivery(deliveryVO);
    }


    @SysLog
    @PutMapping
    @ApiOperation("修改配送模板")
    public void update(@Valid @RequestBody DeliveryVO deliveryVO) {
        deliveryService.updateDelivery(deliveryVO);
    }

    @SysLog
    @DeleteMapping
    @ApiOperation("删除配送模板")
    public void delete(@RequestBody List<Long> deliveryIds) {
        deliveryService.removeByIds(deliveryIds);
    }
}

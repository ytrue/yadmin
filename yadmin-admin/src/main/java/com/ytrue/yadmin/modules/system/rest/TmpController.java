package com.ytrue.yadmin.modules.system.rest;

import com.ytrue.yadmin.common.annotation.WrapResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ytrue
 * @date 2021/4/21 14:05
 */
@WrapResp
@RestController

public class TmpController {


    @GetMapping("store/info")
    public Map<String, String> info() {
        HashMap<String, String> stringHashMap = new HashMap<>();

        return null;
    }


}

package com.zzz.zb.sankuaileaf;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.service.SegmentService;
import com.sankuai.inf.leaf.service.SnowflakeService;
import com.zzz.zb.common.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjiaw
 * @date 2021-06-03 9:33
 * 分段m
 */
@RestController
@RequestMapping("leaf")
public class LeafController {

    @Autowired
    private SegmentService segmentService;

    @Autowired
    private SnowflakeService snowflakeService;

    @GetMapping("/segment")
    public JsonResult segment(){
        Result segment = segmentService.getId("segment");
        return JsonResult.success(segment);
    }

    @GetMapping("/snowflake")
    public JsonResult snowflake(){
        Result snowflake = snowflakeService.getId("snowflake");
        return JsonResult.success(snowflake);
    }
}

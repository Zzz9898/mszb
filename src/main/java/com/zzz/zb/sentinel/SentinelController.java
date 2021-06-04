package com.zzz.zb.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zzz.zb.common.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjiaw
 * @date 2021-06-04 10:50
 * spring cloud alibaba 源码地址，需要mvn clean install导入：https://github.com/alibaba/spring-cloud-alibaba
 * sentinel 中文文档地址：https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel
 * sentinel 控制台下载地址: https://github.com/alibaba/Sentinel/releases
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    @GetMapping("/get")
    public JsonResult get(){
        return JsonResult.success("访问成功！");
    }

    @GetMapping("/get2")
    @SentinelResource(value = "sentinelGet2", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "handleException")
    public JsonResult get2(){
        return JsonResult.success("访问成功！");
    }
}

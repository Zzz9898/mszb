package com.zzz.zb.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zzz.zb.common.JsonResult;

/**
 * @author zhangjiaw
 * @date 2021-06-04 14:00
 * 自定义限流异常返回类
 */
public class SentinelBlockHandler {

    public static JsonResult handleException(BlockException ex) {
        return JsonResult.error(-1, "请稍后再试！");
    }

}

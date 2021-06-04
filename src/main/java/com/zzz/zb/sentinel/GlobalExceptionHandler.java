package com.zzz.zb.sentinel;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.zzz.zb.common.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangjiaw
 * @date 2021-06-04 14:10
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理sentinel自定义全局异常捕获，返回json数据
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = FlowException.class)
    public JsonResult flowExceptionHandler(HttpServletRequest req, FlowException e){
        return JsonResult.error(-1, "请稍后再试!");
    }
}

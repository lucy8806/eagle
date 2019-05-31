package org.eagle.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.eagle.core.consts.CommonConst;
import org.eagle.core.model.ResponseVo;
import org.eagle.core.utils.ResultUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常统一处理
 * @Author lusai
 * @Date 2019/5/31 9:47
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVo handleException(Exception e){
        log.error("系统内部异常，异常信息：", e);
        return ResultUtil.error(CommonConst.DEFAULT_ERROR_CODE, "系统内部异常");
    }

    @ExceptionHandler(value = EagleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseVo handleEagleException(Exception e){
        log.error("系统错误：{}", e.getMessage());
        return ResultUtil.error(CommonConst.DEFAULT_ERROR_CODE, e.getMessage());
    }
}

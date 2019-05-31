package org.eagle.core.exception;

/**
 * @ClassName: EagleException
 * @Description: EAGLE 系统内部异常
 * @Author lusai
 * @Date 2019/5/31 9:40
 * @Version 1.0
 */
public class EagleException extends Exception {

    private static final long serialVersionUID = -5651095234504325556L;

    public EagleException(String message){
        super(message);
    }
}

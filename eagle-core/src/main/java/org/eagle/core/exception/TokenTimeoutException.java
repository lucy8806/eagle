package org.eagle.core.exception;

/**
 * @ClassName: TokenTimeoutException
 * @Description: Token过期异常
 * @Author lusai
 * @Date 2019/5/31 9:45
 * @Version 1.0
 */
public class TokenTimeoutException extends Exception {

    private static final long serialVersionUID = 4901054978921927501L;

    public TokenTimeoutException(String message){
        super(message);
    }
}

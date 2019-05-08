package org.eagle.core.enums;

/**
 * @ClassName: ResponseStatusEnum
 * @Description: 响应状态枚举
 * @Author lucy
 * @Date 2019/5/815:34
 * @Version 1.0
 */
public enum  ResponseStatusEnum {
    SUCCESS(200, "操作成功！"),
    ERROR(500, "温馨提示：服务器未知错误！请联系作者！"),
    UNAUTHORIZED(500, "尚未登录！"),
    FORBIDDEN(500, "您没有操作权限！"),
    NOT_FOUND(500, "资源不存在！"),
    LOGIN_ERROR(500, "账号或密码错误！"),
    USER_EXIST(500, "已存在的用户！"),
    INVALID_AUTHCODE(500, "手机验证码无效！"),
    INVALID_TOKEN(500, "无效的TOKEN，您没有操作权限！"),
    INVALID_ACCESS(500, "无效的请求，该请求已过期！"),
    DELETE_ERROR(500, "删除失败！");
    private Integer code;
    private String message;

    ResponseStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseStatusEnum get(String message) {
        for (ResponseStatusEnum responseStatusEnum : ResponseStatusEnum.values()) {
            if (responseStatusEnum.getMessage() == message) {
                return responseStatusEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

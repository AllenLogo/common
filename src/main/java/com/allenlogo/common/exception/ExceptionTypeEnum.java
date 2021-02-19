package com.allenlogo.common.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author allen
 * Date 2021/2/17
 * Time 17:45
 * Description
 */
public enum ExceptionTypeEnum implements BaseExceptionEnum {

    /**
     *
     */
    CODE_OK(0, "响应成功"),
    CODE_SYSTEM_ERROR(900500, "系统繁忙，请重试"),
    CODE_SERVER_ERROR(900501, "业务异常"),
    CODE_ERROR_PARAM(900512, "请求参数错误");

    private int code;

    private String message;


    ExceptionTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return code;
    }


    @Override
    public String getMessage() {
        return message;
    }

}

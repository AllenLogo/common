package com.allenlogo.common.exception;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author allen
 * Date 2021/2/18
 * Time 0:01
 * Description
 */
@Data
public class BusinessException extends RuntimeException {

    
    private final int code;

    private final String message;

    public BusinessException() {
        this.code = ExceptionTypeEnum.CODE_SERVER_ERROR.getCode();
        this.message = ExceptionTypeEnum.CODE_SERVER_ERROR.getMessage();
    }


    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}

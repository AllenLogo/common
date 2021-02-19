package com.allenlogo.common.base;

import com.allenlogo.common.exception.BaseExceptionEnum;
import com.allenlogo.common.exception.ExceptionTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 *
 * @author allen
 * Date 2021/2/17
 * Time 17:42
 * Description
 */
@ApiModel("MessageResponse")
public class MessageResponse<T> {


    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("状态信息- 推荐")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;


    private MessageResponse(){

    }

    public static MessageResponse<String> resultOk(){
        return of(ExceptionTypeEnum.CODE_OK);
    }


    public static MessageResponse<String> of(BaseExceptionEnum baseExceptionEnum){
        return of(baseExceptionEnum, "");
    }


    public static <S> MessageResponse<S> of(BaseExceptionEnum baseExceptionEnum, S data){
        return of(baseExceptionEnum.getCode(), baseExceptionEnum.getMessage(), data);
    }


    public static <S> MessageResponse<S> of(int code, String message, S data){
        MessageResponse<S> messageResponse = new MessageResponse<>();
        messageResponse.code = code;
        messageResponse.message = message;
        messageResponse.data = data;
        return messageResponse;
    }

}

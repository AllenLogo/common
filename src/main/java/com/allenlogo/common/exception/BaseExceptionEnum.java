package com.allenlogo.common.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author allen
 * Date 2021/2/17
 * Time 17:44
 * Description
 */
public interface BaseExceptionEnum {


    /**
     * 获取异常code
     * @return
     */
    int getCode();

    /**
     * 获取异常详情
     * @return
     */
    String getMessage();

}

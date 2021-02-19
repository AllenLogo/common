package com.allenlogo.common.base;

import com.allenlogo.common.exception.BusinessException;
import com.allenlogo.common.exception.ExceptionTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author allen
 * Date 2021/2/17
 * Time 23:57
 * Description
 */
@Slf4j
public class BaseController {

    private static final String OPEN_JOINER = "(";

    private static final String CLOSE_JOINER = ")";

    private static final String SEPARATOR_SPACE_JOINER = " ";

    private static final String SEPARATOR_EQUAL_JOINER = " = ";

    private static final String EMPTY_STRING = "";


    @ExceptionHandler
    @ResponseBody
    public MessageResponse<String> handleException(Exception ex) {
        log.error("handleException exception [{}]", ex.getMessage());
        if (ex instanceof BusinessException) {
            BusinessException exception = (BusinessException)ex;
            return MessageResponse.of(exception.getCode(), exception.getMessage(), "");
        } else if (ex instanceof IllegalArgumentException) {
            return MessageResponse.of(ExceptionTypeEnum.CODE_ERROR_PARAM.getCode(), ex.getMessage(), "");
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException)ex;
            return this.handleBindingResult(methodArgumentNotValidException.getBindingResult());
        } else if (ex instanceof BindException) {
            BindException bindException = (BindException)ex;
            return this.handleBindingResult(bindException);
        } else {
            return MessageResponse.of(ExceptionTypeEnum.CODE_SYSTEM_ERROR, "");
        }
    }


    private MessageResponse<String> handleBindingResult(@NotNull BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();

        for(Iterator<FieldError> var3 = bindingResult.getFieldErrors().iterator(); var3.hasNext(); sb.append(CLOSE_JOINER).append(SEPARATOR_SPACE_JOINER)) {
            FieldError error = var3.next();
            String defaultMessage = error.getDefaultMessage();
            String field = error.getField();
            sb.append(defaultMessage).append(OPEN_JOINER).append(field);
            Object rejectedValue = error.getRejectedValue();
            if (rejectedValue != null && !EMPTY_STRING.equals(rejectedValue.toString())) {
                sb.append(SEPARATOR_EQUAL_JOINER).append(rejectedValue);
            }
        }
        return MessageResponse.of(ExceptionTypeEnum.CODE_ERROR_PARAM.getCode(), sb.toString(), "");
    }

}

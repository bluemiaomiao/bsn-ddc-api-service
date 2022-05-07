package io.github.bluemiaomiao.bsnddcapiservice.core.exception;


import com.alibaba.fastjson.JSONObject;
import io.github.bluemiaomiao.bsnddcapiservice.core.constant.ErrorMessage;
import io.github.bluemiaomiao.bsnddcapiservice.core.dto.wuhanchain.RespJsonRpcBean;

/**
 * ddc exception
 */
public class DDCException extends RuntimeException{
    private int code;
    private String msg;

    public DDCException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.code = errorMessage.getCode();
        this.msg = errorMessage.getMessage();
    }

    public DDCException(ErrorMessage errorMessage, RespJsonRpcBean.ErrorBean appendError) {
        super(errorMessage.getMessage());
        this.code = errorMessage.getCode();
        this.msg = JSONObject.toJSONString(appendError);
    }

    public DDCException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

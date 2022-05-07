package io.github.bluemiaomiao.bsnddcapiservice.handlers.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class GlobalResponseEntity<T> implements Serializable {
    private String code;
    private String message;
    private T data;


    public GlobalResponseEntity() {
        super();
    }

    public GlobalResponseEntity(T data) {
        this.code = "1000";
        this.message = "完成";
        this.data = data;
    }

    public GlobalResponseEntity<T> withCode(String Code) {
        this.code = code;
        return this;
    }

    public GlobalResponseEntity<T> withMessage(String message) {
        this.message = message;
        return this;
    }

    public GlobalResponseEntity<T> withData(T data) {
        this.data = data;
        return this;
    }
}

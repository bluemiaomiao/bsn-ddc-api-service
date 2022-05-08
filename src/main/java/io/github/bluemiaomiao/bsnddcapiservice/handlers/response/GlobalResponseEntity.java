package io.github.bluemiaomiao.bsnddcapiservice.handlers.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class GlobalResponseEntity<T> implements Serializable {
    @Schema(name = "code", title = "业务状态码")
    private String code;

    @Schema(name = "message", title = "业务消息")
    private String message;

    @Schema(name = "data")
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

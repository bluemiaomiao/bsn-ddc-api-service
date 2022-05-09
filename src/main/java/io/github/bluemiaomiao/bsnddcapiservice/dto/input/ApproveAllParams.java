package io.github.bluemiaomiao.bsnddcapiservice.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ApproveAllParams {
    @NotBlank(message = "调用者不能为空")
    @Schema(name = "sender", title = "调用者")
    private String sender;

    @NotBlank(message = "授权者账户不能为空")
    @Schema(name = "operator", title = "授权者账户")
    private String operator;

    @NotEmpty(message = "授权标识不能为空")
    @Schema(name = "approved", title = "授权标识")
    private Boolean approved;
}

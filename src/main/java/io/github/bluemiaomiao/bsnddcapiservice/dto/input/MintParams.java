package io.github.bluemiaomiao.bsnddcapiservice.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MintParams {
    @NotEmpty(message = "调用者不能为空")
    @Schema(title = "调用者", name = "title")
    private String sender;

    @Schema(title = "接收者", name = "to")
    @NotEmpty(message = "接收者账户不能为空")
    private String to;

    @Schema(title = "DDC资源标识符", name = "ddc-uri")
    @NotEmpty(message = "DDC资源标识符不能为空")
    private String ddcURI;
}

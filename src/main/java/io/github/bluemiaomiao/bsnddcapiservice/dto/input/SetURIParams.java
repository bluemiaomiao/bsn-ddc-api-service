package io.github.bluemiaomiao.bsnddcapiservice.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Data
public class SetURIParams {
    @NotBlank(message = "调用者不能为空")
    @Schema(name = "sender", title = "调用者")
    private String sender;

    @NotEmpty(message = "DDC唯一资源标识不能为空")
    @Schema(name = "ddc-id", title = "DDC唯一资源")
    private BigInteger ddcID;

    @NotEmpty(message = "DDC资源描述符不能为空")
    @Schema(name = "ddc-uri", title = "DDC资源描述符")
    private String ddcURI;
}

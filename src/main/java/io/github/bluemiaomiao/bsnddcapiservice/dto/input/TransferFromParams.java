package io.github.bluemiaomiao.bsnddcapiservice.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Data
public class TransferFromParams {
    @NotBlank(message = "调用者不能为空")
    @Schema(name = "sender", title = "调用者")
    private String sender;

    @NotBlank(message = "拥有者账户不能为空")
    @Schema(name = "from", title = "拥有者账户")
    private String from;

    @NotBlank(message = "接收者账户不能为空")
    @Schema(name = "to", title = "接收者账户")
    private String to;

    @NotEmpty(message = "DDC唯一标识不能为空")
    @Schema(name = "ddc-id", title = "DDC唯一标识")
    private BigInteger ddcID;
}

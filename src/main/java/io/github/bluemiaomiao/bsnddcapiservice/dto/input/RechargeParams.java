package io.github.bluemiaomiao.bsnddcapiservice.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Data
public class RechargeParams {
    @Schema(name = "sender", title = "调用者")
    @NotEmpty(message = "调用者不能为空")
    private String sender;

    @Schema(name = "to", title = "用户账户地址")
    @NotEmpty(message = "用户账户地址不能为空")
    private String to;

    @Schema(name = "amount", title = "金额")
    @NotEmpty(message = "金额不能为空")
    private BigInteger amount;
}

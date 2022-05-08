package io.github.bluemiaomiao.bsnddcapiservice.dto.input;

import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountState;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateAccountStateParams {
    @Schema(name = "sender", title = "调用者")
    @NotEmpty(message = "调用者不能为空")
    private String sender;

    // ref: ~.core.dto.ddc.AccountState
    @Schema(name = "state", title = "状态", allowableValues = {"0", "1"})
    @NotEmpty(message = "状态不能为空")
    private Integer state;

    @Schema(name = "change-platform-state", title = "是否修改平台方状态标识")
    private Boolean changePlatformState;

    @Hidden
    public AccountState getStateForEnum() {
        if (this.state == 0) {
            return AccountState.Frozen;
        } else if (this.state == 1) {
            return AccountState.Active;
        } else {
            // TODO: 需要确认默认值
            return null;
        }
    }

}

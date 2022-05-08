package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountInfo;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.UpdateAccountStateParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.account.AccountServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Tag(name = "权限管理")
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Operation(summary = "查询账户")
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponseEntity<AccountInfo>> getAccountByID(
            @Parameter(description = "账户地址") @PathVariable("id") @NotEmpty(message = "账户地址不能为空") String id)
            throws AccountServiceInvokeFailedException {
        return this.accountService.getAccountByID(id);
    }

    @Operation(summary = "更新账户状态")
    @PutMapping("/{id}/state")
    public ResponseEntity<GlobalResponseEntity<String>> updateAccountState(
            @Parameter(description = "账户地址") @NotEmpty(message = "账户地址不能为空") @PathVariable("id") String id,
            @Valid @RequestBody UpdateAccountStateParams params
    ) throws AccountServiceInvokeFailedException {
        return this.accountService.updateAccountState(id, params);
    }
}

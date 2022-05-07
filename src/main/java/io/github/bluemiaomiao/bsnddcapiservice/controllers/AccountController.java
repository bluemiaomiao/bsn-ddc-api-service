package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.github.bluemiaomiao.bsnddcapiservice.core.dto.ddc.AccountInfo;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.account.AccountServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "权限管理")
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    private final Logger logger = LoggerFactory.getLogger("Account Controller");

    @Operation(summary = "查询账户")
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponseEntity<AccountInfo>> getAccountByID(
            @Parameter(description = "账户地址")
            @PathVariable("id")
            String id)
            throws AccountServiceInvokeFailedException {
        return this.accountService.getAccountByID(id);
    }
}

package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.github.bluemiaomiao.bsnddcapiservice.dto.input.RechargeParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.expense.ExpenseServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Tag(name = "费用管理")
@RestController
@RequestMapping(value = "/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Operation(summary = "充值")
    @PostMapping("/recharge")
    public ResponseEntity<GlobalResponseEntity<String>> recharge(
            @Valid @RequestBody RechargeParams params
    ) throws ExpenseServiceInvokeFailedException {
        return this.expenseService.recharge(params);
    }

    @Operation(summary = "账户余额查询")
    @GetMapping("/balance/{accAddr}")
    public ResponseEntity<GlobalResponseEntity<BigInteger>> getBalance(
            @Parameter(description = "账户地址") @NotEmpty(message = "账户地址不能为空") @PathVariable("accAddr") String id
    ) throws ExpenseServiceInvokeFailedException {
        return this.expenseService.getBalance(id);
    }

    @Operation(summary = "计费规则查询")
    @GetMapping("/cost/{ddcAddr}")
    public ResponseEntity<GlobalResponseEntity<BigInteger>> getCost(
            @Parameter(description = "业务合约地址") @NotEmpty(message = "业务合约地址不能为空") @PathVariable("ddcAddr") String ddcAddr,
            @Parameter(description = "方法ID") @NotEmpty(message = "方法ID不能为空") @RequestParam("sig") String sig
    ) throws ExpenseServiceInvokeFailedException {
        return this.expenseService.getCost(ddcAddr, sig);
    }
}

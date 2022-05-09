package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.github.bluemiaomiao.bsnddcapiservice.exceptions.transaction.TransactionServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Tag(name = "交易查询")
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "查询交易信息")
    @GetMapping("/info")
    public ResponseEntity<GlobalResponseEntity<String>> getTransactionInfo(
            @Parameter(description = "交易Hash") @RequestParam("tx-hash") @NotBlank(message = "交易Hash不能为空") String txHash
    ) throws TransactionServiceInvokeFailedException {
        return this.transactionService.getTransactionInfo(txHash);
    }

    @Operation(summary = "查询交易回执")
    @GetMapping("/receipt")
    public ResponseEntity<GlobalResponseEntity<String>> getTransactionReceipt(
            @Parameter(description = "交易Hash") @RequestParam("tx-hash") @NotBlank(message = "交易Hash不能为空") String txHash
    ) throws TransactionServiceInvokeFailedException {
        return this.transactionService.getTransactionReceipt(txHash);
    }

    @Operation(summary = "查询交易状态")
    @GetMapping("/status")
    public ResponseEntity<GlobalResponseEntity<Boolean>> getTransactionStatus(
            @Parameter(description = "交易Hash") @RequestParam("tx-hash") @NotBlank(message = "交易Hash不能为空") String txHash
    ) throws TransactionServiceInvokeFailedException {
        return this.transactionService.getTransactionStatus(txHash);
    }
}

package io.github.bluemiaomiao.bsnddcapiservice.service;

import io.github.bluemiaomiao.bsnddcapiservice.exceptions.transaction.TransactionServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 交易查询
 */
@Service
public interface TransactionService {
    ResponseEntity<GlobalResponseEntity<String>> getTransactionInfo(String txHash) throws TransactionServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> getTransactionReceipt(String txHash) throws TransactionServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<Boolean>> getTransactionStatus(String txHash) throws TransactionServiceInvokeFailedException;
}

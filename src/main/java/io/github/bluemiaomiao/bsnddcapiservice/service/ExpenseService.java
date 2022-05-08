package io.github.bluemiaomiao.bsnddcapiservice.service;

import io.github.bluemiaomiao.bsnddcapiservice.dto.input.RechargeParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.expense.ExpenseServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * 费用管理
 */
@Service
public interface ExpenseService {
    ResponseEntity<GlobalResponseEntity<String>> recharge(RechargeParams params) throws ExpenseServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<BigInteger>> getBalance(String id) throws ExpenseServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<BigInteger>> getCost(String ddcAddr, String sig) throws ExpenseServiceInvokeFailedException;
}

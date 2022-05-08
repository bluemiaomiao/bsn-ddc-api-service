package io.github.bluemiaomiao.bsnddcapiservice.service.impl;

import io.github.bluemiaomiao.bsnddcapiservice.core.DDCCoreTemplate;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.RechargeParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.expense.ExpenseServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private DDCCoreTemplate ddcCoreTemplate;

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> recharge(RechargeParams params) throws ExpenseServiceInvokeFailedException {
        String result = null;

        try {
            result = this.ddcCoreTemplate.buildWithDefaultEvent().chargeService.recharge(
                    params.getSender(),
                    params.getTo(),
                    params.getAmount()
            );
        } catch (Exception e) {
            throw new ExpenseServiceInvokeFailedException("充值失败");
        }
        return new ResponseEntity<>(new GlobalResponseEntity<>(result), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<BigInteger>> getBalance(String id) throws ExpenseServiceInvokeFailedException {
        BigInteger result = null;
        try {
            this.ddcCoreTemplate.buildWithDefaultEvent().chargeService.balanceOf(id);
        } catch (Exception e) {
            throw new ExpenseServiceInvokeFailedException("查询账户余额失败");
        }
        return new ResponseEntity<>(new GlobalResponseEntity<>(result), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<BigInteger>> getCost(String ddcAddr, String sig) throws ExpenseServiceInvokeFailedException {
        BigInteger result = null;
        try {
            result = this.ddcCoreTemplate.buildWithDefaultEvent().chargeService.queryFee(ddcAddr, sig);
        } catch (Exception e) {
            throw new ExpenseServiceInvokeFailedException("DDC计费规则查询失败");
        }
        return new ResponseEntity<>(new GlobalResponseEntity<>(result), HttpStatus.OK);
    }
}

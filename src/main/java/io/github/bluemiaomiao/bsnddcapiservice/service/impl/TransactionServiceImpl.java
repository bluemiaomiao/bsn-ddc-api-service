package io.github.bluemiaomiao.bsnddcapiservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.github.bluemiaomiao.bsnddcapiservice.core.DDCCoreTemplate;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.transaction.TransactionServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private DDCCoreTemplate ddcCoreTemplate;

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> getTransactionInfo(String txHash) throws TransactionServiceInvokeFailedException {
        String result = null;
        // TODO: 该方法在 SDK 文档中找不到
        try {
            result = JSONObject.toJSONString(this.ddcCoreTemplate.buildWithNoneEvent().baseService.getTransactionByHash(txHash));
        } catch (Exception e) {
            throw new TransactionServiceInvokeFailedException("查询交易信息调用失败");
        }
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> getTransactionReceipt(String txHash) throws TransactionServiceInvokeFailedException {
        String result = null;
        // TODO: 该方法在 SDK 文档中找不到
        try {
            result = JSONObject.toJSONString(this.ddcCoreTemplate.buildWithNoneEvent().baseService.getTransactionReceipt(txHash));
        } catch (Exception e) {
            throw new TransactionServiceInvokeFailedException("查询交易回执调用失败");
        }
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<Boolean>> getTransactionStatus(String txHash) throws TransactionServiceInvokeFailedException {
        Boolean result = null;
        try {
            result = this.ddcCoreTemplate.buildWithNoneEvent().baseService.getTransStatusByHash(txHash);
        } catch (Exception e) {
            throw new TransactionServiceInvokeFailedException("查询交易状态调用失败");
        }
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }


}

package io.github.bluemiaomiao.bsnddcapiservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.github.bluemiaomiao.bsnddcapiservice.core.DDCCoreTemplate;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.block.BlockServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class BlockServiceImpl implements BlockService {

    @Autowired
    private DDCCoreTemplate ddcCoreTemplate;

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> getBlockInfoByNumber(BigInteger blockNumber) throws BlockServiceInvokeFailedException {
        String result = null;

        try {
            result = JSONObject.toJSONString(this.ddcCoreTemplate.buildWithNoneEvent().baseService.getBlockByNumber(blockNumber));
        } catch (Exception e) {
            throw new BlockServiceInvokeFailedException("获取区块信息调用失败");
        }

        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }
}

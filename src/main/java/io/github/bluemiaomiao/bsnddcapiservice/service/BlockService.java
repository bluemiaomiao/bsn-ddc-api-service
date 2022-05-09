package io.github.bluemiaomiao.bsnddcapiservice.service;

import io.github.bluemiaomiao.bsnddcapiservice.exceptions.block.BlockServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * 区块查询
 */
@Service
public interface BlockService {
    ResponseEntity<GlobalResponseEntity<String>> getBlockInfoByNumber(BigInteger blockNumber) throws BlockServiceInvokeFailedException;
}

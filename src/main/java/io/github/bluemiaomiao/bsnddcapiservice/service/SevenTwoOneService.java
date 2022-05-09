package io.github.bluemiaomiao.bsnddcapiservice.service;

import io.github.bluemiaomiao.bsnddcapiservice.dto.input.ApproveAllParams;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.ApproveParams;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.MintParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.seventwoone.SevenTwoOneServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * 721服务
 */
@Service
public interface SevenTwoOneService {
    ResponseEntity<GlobalResponseEntity<String>> mint(MintParams mintParams) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> approve(ApproveParams approveParams) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> getApprove(BigInteger ddcID) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> setApprovalForAll(ApproveAllParams approveAllParams) throws SevenTwoOneServiceInvokeFailedException;
}

package io.github.bluemiaomiao.bsnddcapiservice.service.impl;

import io.github.bluemiaomiao.bsnddcapiservice.core.DDCCoreTemplate;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.ApproveAllParams;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.ApproveParams;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.MintParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.seventwoone.SevenTwoOneServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.SevenTwoOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class SevenTwoOneServiceImpl implements SevenTwoOneService {

    @Autowired
    private DDCCoreTemplate ddcCoreTemplate;

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> mint(MintParams mintParams) throws SevenTwoOneServiceInvokeFailedException {
        String result = null;

        try {
            result = this.ddcCoreTemplate.buildWithDefaultEvent().ddc721Service.mint(
                    mintParams.getSender(),
                    mintParams.getTo(),
                    mintParams.getDdcURI()
            );
        } catch (Exception e) {
            throw new SevenTwoOneServiceInvokeFailedException("生成调用失败");
        }

        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> approve(ApproveParams approveParams) throws SevenTwoOneServiceInvokeFailedException {
        String result = null;

        try {
            result = this.ddcCoreTemplate.buildWithDefaultEvent().ddc721Service.approve(
                    approveParams.getSender(),
                    approveParams.getTo(),
                    approveParams.getDdcID()
            );
        } catch (Exception e) {
            throw new SevenTwoOneServiceInvokeFailedException("DDC授权调用失败");
        }

        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> getApprove(BigInteger ddcID) throws SevenTwoOneServiceInvokeFailedException {
        String result = null;

        try {
            result = this.ddcCoreTemplate.buildWithDefaultEvent().ddc721Service.getApproved(ddcID);
        } catch (Exception e) {
            throw new SevenTwoOneServiceInvokeFailedException("DDC授权查询调用失败");
        }

        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<GlobalResponseEntity<String>> setApprovalForAll(ApproveAllParams approveAllParams) throws SevenTwoOneServiceInvokeFailedException {
        String result = null;

        try {
            result = this.ddcCoreTemplate.buildWithDefaultEvent().ddc721Service.setApprovalForAll(
                    approveAllParams.getSender(),
                    approveAllParams.getOperator(),
                    approveAllParams.getApproved()
            );
        } catch (Exception e) {
            throw new SevenTwoOneServiceInvokeFailedException("账户授权查询调用失败");
        }

        return new ResponseEntity<>(
                new GlobalResponseEntity<>(result),
                HttpStatus.OK
        );
    }
}

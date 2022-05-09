package io.github.bluemiaomiao.bsnddcapiservice.service;

import io.github.bluemiaomiao.bsnddcapiservice.dto.input.*;
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

    ResponseEntity<GlobalResponseEntity<Boolean>> isApprovedForAll(String owner, String operator) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> safeTransferFrom(SafeTransferFromParams safeTransferFromParams) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> transferFrom(TransferFromParams transferFromParams) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> burn(BurnParams burnParams) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<BigInteger>> balanceOf(String owner) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> ownerOf(BigInteger ddcID) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> name() throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> symbols() throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> ddcURI(BigInteger ddcID) throws SevenTwoOneServiceInvokeFailedException;

    ResponseEntity<GlobalResponseEntity<String>> setURI(SetURIParams setURIParams) throws SevenTwoOneServiceInvokeFailedException;
}

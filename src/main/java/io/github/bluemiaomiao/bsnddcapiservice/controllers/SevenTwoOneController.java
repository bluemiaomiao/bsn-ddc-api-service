package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.github.bluemiaomiao.bsnddcapiservice.dto.input.ApproveAllParams;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.ApproveParams;
import io.github.bluemiaomiao.bsnddcapiservice.dto.input.MintParams;
import io.github.bluemiaomiao.bsnddcapiservice.exceptions.seventwoone.SevenTwoOneServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.SevenTwoOneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Tag(name = "721")
@RestController
@RequestMapping(value = "/721")
public class SevenTwoOneController {

    @Autowired
    private SevenTwoOneService sevenTwoOneService;

    @Operation(summary = "生成")
    @PostMapping("/mint")
    public ResponseEntity<GlobalResponseEntity<String>> mint(
            @RequestBody @Valid MintParams mintParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.mint(mintParams);
    }

    @Operation(summary = "安全生成")
    @PostMapping("/safe-mint")
    public ResponseEntity<GlobalResponseEntity<String>> safeMint(
            @RequestBody @Valid MintParams mintParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.mint(mintParams);
    }

    @Operation(summary = "DDC授权")
    @PostMapping("/approve")
    public ResponseEntity<GlobalResponseEntity<String>> approve(
            @RequestBody @Valid ApproveParams approveParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.approve(approveParams);
    }

    @Operation(summary = "DDC授权查询")
    @GetMapping("/approve")
    public ResponseEntity<GlobalResponseEntity<String>> getApprove(
            @Parameter(description = "DDC唯一标识") @NotEmpty(message = "DDC唯一标识不能为空") @RequestParam("ddc-id") BigInteger ddcID
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.getApprove(ddcID);
    }

    @Operation(summary = "账户授权")
    @PostMapping("/approve/all")
    public ResponseEntity<GlobalResponseEntity<String>> setApproveForAll(
            @RequestBody @Valid ApproveAllParams approveALlParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.setApprovalForAll(approveALlParams);
    }

}

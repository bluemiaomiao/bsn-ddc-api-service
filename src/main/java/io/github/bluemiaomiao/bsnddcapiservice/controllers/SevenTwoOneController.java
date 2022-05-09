package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.github.bluemiaomiao.bsnddcapiservice.dto.input.*;
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
import javax.validation.constraints.NotBlank;
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
            @RequestBody @Valid ApproveAllParams approveAllParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.setApprovalForAll(approveAllParams);
    }

    @Operation(summary = "账户授权查询")
    @GetMapping("/approve/all")
    public ResponseEntity<GlobalResponseEntity<Boolean>> isApprovedForAll(
            @Parameter(description = "owner") @NotEmpty(message = "拥有者账户不能为空") @RequestParam("owner") String owner,
            @Parameter(description = "operator") @NotEmpty(message = "授权者账户不能为空") @RequestParam("operator") String operator
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.isApprovedForAll(owner, operator);
    }

    @Operation(summary = "安全转移")
    @PostMapping("/transfer/safe")
    public ResponseEntity<GlobalResponseEntity<String>> safeTransferFrom(
            @RequestBody @Valid SafeTransferFromParams safeTransferFromParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.safeTransferFrom(safeTransferFromParams);
    }

    @Operation(summary = "转移")
    @PostMapping("/transfer")
    public ResponseEntity<GlobalResponseEntity<String>> transferFrom(
            @RequestBody @Valid TransferFromParams transferFromParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.transferFrom(transferFromParams);
    }

    @Operation(summary = "销毁")
    @PostMapping("/burn")
    public ResponseEntity<GlobalResponseEntity<String>> burn(
            @RequestBody @Valid BurnParams burnParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.burn(burnParams);
    }

    @Operation(summary = "查询数量")
    @GetMapping("/balance")
    public ResponseEntity<GlobalResponseEntity<BigInteger>> balanceOf(
            @Parameter(description = "拥有者账户") @NotBlank(message = "拥有者账户不能为空") @RequestParam("owner") String owner
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.balanceOf(owner);
    }

    @Operation(summary = "查询所有者")
    @GetMapping("/owner")
    public ResponseEntity<GlobalResponseEntity<String>> ownerOf(
            @Parameter(description = "DDC唯一标识") @NotEmpty(message = "DDC唯一标识不能为空") @RequestParam("ddc-id") BigInteger ddcID
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.ownerOf(ddcID);
    }

    @Operation(summary = "获取运营商名称")
    @GetMapping("/name")
    public ResponseEntity<GlobalResponseEntity<String>> name() throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.name();
    }


    @Operation(summary = "获取运营商符号")
    @GetMapping("/symbols")
    public ResponseEntity<GlobalResponseEntity<String>> symbols() throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.symbols();
    }

    @Operation(summary = "获取运营商DDCURI")
    @GetMapping("/ddcuri")
    public ResponseEntity<GlobalResponseEntity<String>> ddcURI(
            @Parameter(description = "DDC唯一标识") @NotEmpty(message = "DDC唯一标识不能为空") @RequestParam("ddc-id") BigInteger ddcID
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.ddcURI(ddcID);
    }

    @Operation(summary = "URI设置")
    @PostMapping("/uri")
    public ResponseEntity<GlobalResponseEntity<String>> setURI(
            @RequestBody @Valid SetURIParams setURIParams
    ) throws SevenTwoOneServiceInvokeFailedException {
        return this.sevenTwoOneService.setURI(setURIParams);
    }
}

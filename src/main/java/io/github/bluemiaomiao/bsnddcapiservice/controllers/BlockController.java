package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.github.bluemiaomiao.bsnddcapiservice.exceptions.block.BlockServiceInvokeFailedException;
import io.github.bluemiaomiao.bsnddcapiservice.handlers.response.GlobalResponseEntity;
import io.github.bluemiaomiao.bsnddcapiservice.service.BlockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Tag(name = "区块查询")
@RestController
@RequestMapping(value = "/block")
public class BlockController {

    @Autowired
    private BlockService blockService;

    @Operation(summary = "获取区块信息")
    @GetMapping("/info")
    public ResponseEntity<GlobalResponseEntity<String>> getBlockInfoByNumber(
            @Parameter(description = "区块高度") @RequestParam("block-number") @NotEmpty(message = "区块高度不能为空")BigInteger blockNumber
            ) throws BlockServiceInvokeFailedException {
        return this.blockService.getBlockInfoByNumber(blockNumber);
    }
}

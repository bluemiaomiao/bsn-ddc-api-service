package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "健康检查")
@RestController
@RequestMapping(value = "/health")
public class HelloController {

    @Operation(summary = "业务可用性检测")
    @GetMapping(value = "/ping")
    public String ping() {
        return "pong";
    }
}

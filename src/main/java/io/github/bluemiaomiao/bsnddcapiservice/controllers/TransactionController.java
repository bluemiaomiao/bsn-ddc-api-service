package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "交易查询")
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
}

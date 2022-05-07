package io.github.bluemiaomiao.bsnddcapiservice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "区块查询")
@RestController
@RequestMapping(value = "/block")
public class BlockController {
}

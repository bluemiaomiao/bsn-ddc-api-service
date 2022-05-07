package io.github.bluemiaomiao.bsnddcapiservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openapi() {
        return new OpenAPI()
                .info(new Info()
                        .description("BSN DDC 微服务接入文档，将 BSN DDC SDK 转化为可伸缩的微服务。")
                        .title("BSN DDC API SERVICE")
                        .version("v1.0.0")
                        .summary("将 BSN DDC SDK 转化为可伸缩的微服务")
                        .license(new License()
                                .name("Apache License 2")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
                        .contact(new Contact()
                                .name("bluemiaomiao")
                                .url("https://github.com/bluemiaomiao")
                                .email("xv2017@outlook.com")));
    }
}

package com.phoenix.signal.controller.platform.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("信控系统文档")
                        .description("信号机信息处理")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation()
                        .description("项目API文档")
                        .url("/"));
    }

}

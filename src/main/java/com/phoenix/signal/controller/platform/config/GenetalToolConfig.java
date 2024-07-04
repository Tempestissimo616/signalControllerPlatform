package com.phoenix.signal.controller.platform.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenetalToolConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

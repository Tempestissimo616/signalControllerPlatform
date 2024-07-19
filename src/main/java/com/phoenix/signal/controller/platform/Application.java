package com.phoenix.signal.controller.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.phoenix.signal.controller.platform.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer(){
//		MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
//		// 可以通过环境变量获取你的 mapper 路径，这样 mapper 扫描可以通过配置文件配置了
//		scannerConfigurer.setBasePackage("com.phoenix.signal.controller.platform.mapper");
//		return scannerConfigurer;
//	}

}

package com.wusc.entranceimp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient
public class EntranceImpApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntranceImpApplication.class, args);
	}
}

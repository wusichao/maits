package com.wusc.entranceclick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@ServletComponentScan
@EnableDiscoveryClient
public class EntranceClickApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntranceClickApplication.class, args);
	}
}

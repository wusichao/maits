package com.wusc.entranceimp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class EntranceImpApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntranceImpApplication.class, args);
	}
}

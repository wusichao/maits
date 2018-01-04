package com.wusc.loganalyzes;

import com.wusc.loganalyzes.launch.Launch;
import com.wusc.loganalyzes.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LogAnalyzesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAnalyzesApplication.class, args);
		Launch launch = (Launch) SpringUtil.getBean("launch");
		launch.run(args);
	}
}

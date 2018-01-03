package com.wusc.loganalyzes;

import com.wusc.loganalyzes.launch.Launch;
import com.wusc.loganalyzes.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogAnalyzesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAnalyzesApplication.class, args);
		Launch launch = (Launch) SpringUtil.getBean("launch");
		launch.run(args);
	}
}

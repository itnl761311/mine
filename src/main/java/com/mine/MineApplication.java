package com.mine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MineApplication {
	public static void main(String[] args) {
		ConvertCommon convertCommon = new ConvertCommon();
		convertCommon.convertJsonToMap("");
		SpringApplication.run(MineApplication.class, args);

	}

}

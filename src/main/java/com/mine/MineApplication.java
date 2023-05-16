package com.mine;

import com.mine.util.Convenience;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MineApplication {
	public static void main(String[] args) {
		java.sql.Date sqlDate = new java.sql.Date(Convenience.getCurrentDate().getTime());
		System.out.println(sqlDate);
		SpringApplication.run(MineApplication.class, args);

	}

}

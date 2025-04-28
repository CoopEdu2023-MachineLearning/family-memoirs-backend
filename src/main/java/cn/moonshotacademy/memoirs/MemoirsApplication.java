package cn.moonshotacademy.memoirs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MemoirsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoirsApplication.class, args);
	}

}

package cn.zhu.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootConfigApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootConfigApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("程序老仁 插件上路");
	}
}

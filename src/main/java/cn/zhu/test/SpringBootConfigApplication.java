package cn.zhu.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className SpringBootConfigApplication
 * @description TODO SpringBootApplication是 springBoot的统一注解，包括配置的注解@Configuration,自动装载的注解@EnableAutoConfiguration,扫描包的注解@ComponentScan
 * @author 程序老仁
 * @date 2020/3/25
 */   

@SpringBootApplication
public class SpringBootConfigApplication implements CommandLineRunner {

	public static void main(String[] args) {
		// 启动springBoot项目
		SpringApplication.run(SpringBootConfigApplication.class, args);
	}

	/**
	 * 实现了CommandLineRunner接口，复写了run方法，是程序启动时添加一些特殊的定制功能
	 */
	@Override
	public void run(String... strings) throws Exception {
		System.out.println("程序老仁 添加的特殊启动功能");
	}
}

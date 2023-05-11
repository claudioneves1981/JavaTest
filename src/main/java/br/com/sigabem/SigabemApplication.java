package br.com.sigabem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SigabemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigabemApplication.class, args);
	}

}

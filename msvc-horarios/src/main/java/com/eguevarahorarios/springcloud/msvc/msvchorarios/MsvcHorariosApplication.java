package com.eguevarahorarios.springcloud.msvc.msvchorarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcHorariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcHorariosApplication.class, args);
	}

}

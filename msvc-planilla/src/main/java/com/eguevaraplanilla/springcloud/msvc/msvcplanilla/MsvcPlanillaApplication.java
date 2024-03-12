package com.eguevaraplanilla.springcloud.msvc.msvcplanilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcPlanillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcPlanillaApplication.class, args);
	}

}

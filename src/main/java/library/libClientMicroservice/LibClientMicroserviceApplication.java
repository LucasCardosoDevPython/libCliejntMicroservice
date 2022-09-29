package library.libClientMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LibClientMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibClientMicroserviceApplication.class, args);
	}

}

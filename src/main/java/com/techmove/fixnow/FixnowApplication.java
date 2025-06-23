package com.techmove.fixnow;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
   servers = {
     @Server(url = "http://localhost:8383", description = "LOCAL"),
     @Server(url = "https://fixnow-backend-production.up.railway.app", description = "RAILWAY")
   }
)
@SpringBootApplication
public class FixnowApplication {

	public static void main(String[] args) {
		SpringApplication.run(FixnowApplication.class, args);
	}

}

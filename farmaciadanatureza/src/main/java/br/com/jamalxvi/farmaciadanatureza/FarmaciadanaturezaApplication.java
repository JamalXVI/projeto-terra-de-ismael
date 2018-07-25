package br.com.jamalxvi.farmaciadanatureza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan({"br.com.jamalxvi.farmaciadanatureza", "br.com.jamalxvi.farmaciadanatureza.security"})
public class FarmaciadanaturezaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmaciadanaturezaApplication.class, args);
	}
}

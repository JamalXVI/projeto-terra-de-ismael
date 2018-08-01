package br.com.jamalxvi.farmaciadanatureza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(
    basePackageClasses = {FarmaciadanaturezaApplication.class, Jsr310JpaConverters.class}
)
@ComponentScan({"br.com.jamalxvi.farmaciadanatureza", "br.com.jamalxvi.farmaciadanatureza.security"})
public class FarmaciadanaturezaApplication {

  public static void main(String[] args) {
    SpringApplication.run(FarmaciadanaturezaApplication.class, args);
  }
}

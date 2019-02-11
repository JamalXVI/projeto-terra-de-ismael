package br.com.jamalxvi.farmaciadanatureza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


/**
 * Esta é a classe principal do projeto por onde a aplicação se inicia.
 * Lembrar que esta classe deve ficar no pacote acima dos controllers
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
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

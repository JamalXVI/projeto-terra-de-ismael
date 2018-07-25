package br.com.jamalxvi.farmaciadanatureza;

import br.com.jamalxvi.farmaciadanatureza.service.impl.AutorizadadeServiceImplTest;
import br.com.jamalxvi.farmaciadanatureza.service.impl.UsuarioServiceImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Suite.class)
@SpringBootTest(classes = {FarmaciadanaturezaApplication.class})
public class FarmaciadanaturezaApplicationTests {

	@Test
	public void contextLoads() {
	}

}

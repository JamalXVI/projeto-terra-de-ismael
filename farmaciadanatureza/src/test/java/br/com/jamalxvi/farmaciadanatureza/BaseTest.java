package br.com.jamalxvi.farmaciadanatureza;

import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Classe base para todos os testes.
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public class BaseTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public void esperarErroGenerico(String mensagem) {
        thrown.expect(MensagemExcecao.class);
        thrown.expectMessage(mensagem);
    }
}

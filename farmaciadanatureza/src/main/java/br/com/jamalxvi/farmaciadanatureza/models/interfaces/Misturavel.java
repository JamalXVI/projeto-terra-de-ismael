package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.math.BigDecimal;

/**
 * Esta interface define os modelos que são passível de mistura. Para isso é preciso definir o
 * objeto que irá se realizar a mistura.
 * @version 0.1
 * @since 0.1
 * @author Jamal XVI <henriquearantest@gmail.com>
 */
public interface Misturavel<T> {
    T getDosagem();
    BigDecimal getProporcao();
}

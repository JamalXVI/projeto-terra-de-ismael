package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoDiluicao;

/**
 * Esta interfaces define os tipos de medicamentos que são diluídos. Existem três tipos de
 * diluição:<br/>
 * -CH (Homeopatia Somente);<br/>
 * -DM (Tintura Somente);<br/>
 * -TM (Tintura Somente);<br/>
 * Definada o tipo de diluição é importante definir a proporção de diluição. Ex:<br/>
 * Curcuma CH 6. CH é o tipo de diluição e 6 é a diluição.<br/>
 * <strong>É importante ressaltar que a interface define somente a quantidade de diluição.</strong>
 * @version 0.1
 * @since 0.1
 * @author Jamal XVI <henriquearantest@gmail.com>
 */
public interface Diluivel {
  Integer getDiluicao();
}

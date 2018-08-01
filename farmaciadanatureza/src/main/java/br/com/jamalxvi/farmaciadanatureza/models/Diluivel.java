package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoDiluicao;

/**
 * Esta interface define os tipos de medicamentos que são diluídos. Existem dois tipos de
 * diluição:<br/>
 * -CH;<br/>
 * -DM;<br/>
 * Definada o tipo de diluição é importante definir a proporção de diluição. Ex:<br/>
 * Curcuma CH 6. CH é o tipo de diluição e 6 é a diluição.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface Diluivel {
  EnumTipoDiluicao getTipoDiluicao();
  Integer getDiluicao();
}

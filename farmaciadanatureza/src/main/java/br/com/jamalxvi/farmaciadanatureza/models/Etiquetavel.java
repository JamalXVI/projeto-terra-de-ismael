package br.com.jamalxvi.farmaciadanatureza.models;

import java.time.LocalDate;

/**
 * Esta interface define as classes de medicamentos que podem gerar uma etiqueta. Os medicamentos
 *  que forem imprimir uma etiqueta devem apersentar:<br/>
 *  -Posologia
 *  -Vencimento da Receita
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface Etiquetavel {
  public String getPosologia();
  public LocalDate getValidadeReceita();
}

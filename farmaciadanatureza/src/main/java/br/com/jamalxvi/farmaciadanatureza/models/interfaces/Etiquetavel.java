package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.time.LocalDate;

/**
 * Esta interfaces define as classes de medicamentos que podem gerar uma etiqueta. Os medicamentos
 *  que forem imprimir uma etiqueta devem apersentar:<br/>
 *  -Posologia;<br/>
 *  -Vencimento da Receita;<br/>
 * @version 0.1
 * @since 0.1
 * @author Jamal XVI <henriquearantest@gmail.com>
 */
public interface Etiquetavel {
  public String getPosologia();
  public LocalDate getValidadeReceita();
}

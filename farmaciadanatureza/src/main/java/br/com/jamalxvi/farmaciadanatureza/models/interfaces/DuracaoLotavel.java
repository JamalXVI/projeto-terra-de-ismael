package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

/**
 * Classe que especifíca a duração do lote (em dias). A utilização dessa interfaces obriga
 * a implementação da interfaces Lotável na classe de estoque, pois é utilizado esta interfaces
 * para o calculo do vencimento.
 * @version 0.1
 * @since 0.1
 * @author Jamal XVI <henriquearantest@gmail.com>
 */
public interface DuracaoLotavel {
  public Long getDuracaoLote();
}

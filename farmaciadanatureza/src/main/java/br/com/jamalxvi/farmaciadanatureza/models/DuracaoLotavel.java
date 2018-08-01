package br.com.jamalxvi.farmaciadanatureza.models;

/**
 * Classe que especifíca a duração do lote (em dias). A utilização dessa interface obriga
 * a implementação da interface Lotável na classe de estoque, pois é utilizado esta interface
 * para o calculo do vencimento.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface DuracaoLotavel {
  public Long getDuracaoLote();
}

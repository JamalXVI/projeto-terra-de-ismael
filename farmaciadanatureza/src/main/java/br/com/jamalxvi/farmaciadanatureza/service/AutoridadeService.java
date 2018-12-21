package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;

import java.util.List;

/**
 * Interface do Serviço de Autoridade
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface AutoridadeService {

  /**
   * Encontra a autoridade pelo id
   *
   * @param id o id da autoridade
   * @return a autoridade, se encontrada, ou nulo
   */
  List<Autoridade> findById(Long id);

  /**
   * Econtra a Autorizacao pelo nome, vide {@link EnumAutorizacaoUsuario}
   *
   * @param autorizacao o nome da autorizacao
   * @return a autorizacao, se encontrada, ou nulo
   */
  List<Autoridade> findByAutorizacao(String autorizacao);

  /**
   * Encontra a Autoridade pelo Enum de autorização {@link EnumAutorizacaoUsuario}
   *
   * @param autorizacao o valor do Enum de autorização em questão
   * @return a lista de autoridades
   */
  List<Autoridade> findByAutorizacao(EnumAutorizacaoUsuario autorizacao);

  /**
   * Cria e salva uma autoridade com base no Enum de autorização {@link EnumAutorizacaoUsuario}
   *
   * @param autorizacaoUsuario o valor do enum em questão
   * @return a Autoridade no estado attached do jpa.
   */
  Autoridade save(EnumAutorizacaoUsuario autorizacaoUsuario);


  /**
   * Cria e salva uma autoridade, a partir de uma String, com base no Enum de autorização
   * {@link EnumAutorizacaoUsuario}
   *
   * @param autorizacao a autorização a ser encontrada
   * @return a Autoridade no estado attached do jpa.
   */
  Autoridade save(String autorizacao);

}

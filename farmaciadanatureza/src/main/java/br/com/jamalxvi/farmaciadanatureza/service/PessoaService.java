package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;

import java.util.List;

/**
 * Interface do Serviço de Usuário
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface PessoaService {
  /**
   * Encontra a pessoa pelo id
   *
   * @param id da pessoa em questão
   * @return a Pessoa, se encontrada, ou nulo.
   */
  Pessoa findById(Long id);

  /**
   * Encontra a pessoa pelo Cpf
   *
   * @param cpf
   * @return a Pessoa, se encontrada, ou nulo
   */
  Pessoa findByCpf(String cpf);

  /**
   * Retorna todas as pessoas
   *
   * @return a Lista com todas as pessoas
   */
  List<Pessoa> findAll();

  /**
   * Salva a pessoa no banco
   *
   * @param p a pessoa a ser salva
   * @return a Pessoa attached com o JPA
   */
  Pessoa save(Pessoa p);

  /**
   * Remove a pessoa do banco de dados
   *
   * @param p a pessoa em questão
   */
  void remove(Pessoa p);
}

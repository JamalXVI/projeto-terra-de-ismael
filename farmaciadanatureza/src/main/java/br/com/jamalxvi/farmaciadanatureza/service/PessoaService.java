package br.com.jamalxvi.farmaciadanatureza.service;

import java.util.List;

import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;

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
  Pessoa encontrarPeloId(Long id);

  /**
   * Encontra a pessoa pelo Cpf
   *
   * @param cpf
   * @return a Pessoa, se encontrada, ou nulo
   */
  Pessoa encontrarPeloCpf(String cpf);

  /**
   * Retorna todas as pessoas
   *
   * @return a Lista com todas as pessoas
   */
  List<Pessoa> listarTodos();


  /**
   * Retorna todas as pessoas ativas convertidas em Dto
   *
   * @return a Lista com todas as pessoas em Dto
   */
  List<PessoaDto> listarTodosDto();

  /**
   * Salva a pessoa no banco
   *
   * @param p a pessoa a ser salva
   * @return a Pessoa attached com o JPA
   */
  Pessoa salvar(Pessoa p);

  /**
   * Remove a pessoa do banco de dados
   *
   * @param p a pessoa em questão
   */
  void remover(Pessoa p);

  /**
   * Retorna uma lista de pessoas de acordo com a pesquisa
   * @param pesquisa o que foi digitado, em string
   * @param limite o limite de resultados
   * @return a lista de pessoas conforme os resultados
   */
  List<PessoaDto> pesquisar(String pesquisa, Integer limite);
}

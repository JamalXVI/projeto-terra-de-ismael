package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.dto.RequisicaoDoUsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.UsuarioDto;

import java.util.List;

/**
 * Interface do Serviço de Usuário
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface UsuarioService {
  void resetCredentials();

  /**
   * Encontra o usuário pelo id
   * @param id o id do usuário em questão
   * @return o usuário, se encontrado, ou nulo
   */
  Usuario encontrarPeloId(Long id);

  /**
   * Retorna o usuário pelo nome de usuário
   * @param usuario o usuário em questão
   * @return o usuário em questão ou nulo
   */
  Usuario encontrarPeloNomeDeUsuario(String usuario);

    /**
     * Retorna o usuário pelo nome de usuário com os dados não sensíveis
     * @param usuario o usuário em questão
     * @return o usuário em questão ou nulo
     */
    UsuarioDto encontrarPeloNomeDeUsuarioDto(String usuario);

  /**
   * Retorna a lista de usuário (somente dados não sensíveis) e usuários ativos
   *
   * @return a lista de usuários
   */
  List<UsuarioDto> listarTodos();

  /**
   * Salva o usuário em questão
   *
   * @param user o usuário em questão
   * @return retorna o usuário salvo attached com o JPA
   */
  Usuario salvar(RequisicaoDoUsuarioDto user);
}

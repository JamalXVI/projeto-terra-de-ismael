package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.RequisicaoDoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.UsuarioDto;

import java.util.List;

/**
 * Interface do Serviço de Usuário
 * @author      Jamal XVI <henriquearantest@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public interface UsuarioService {
  void resetCredentials();

  Usuario findById(Long id);

  Usuario findByUsuario(String usuario);

  /**
   * Retorna a lista de usuário (somente dados não sensíveis)
   * @return a lista de usuários
   */
  List<UsuarioDto> findAll();

  Usuario save(RequisicaoDoUsuario user);
}

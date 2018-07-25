package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;

import java.util.List;

/**
 * Interface do Serviço de Autoridade
 * @author      Jamal XVI <henriquearantest@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public interface AutoridadeService {
  List<Autoridade> findById(Long id);

  List<Autoridade> findByAutorizacao(String autorizacao);
    List<Autoridade> findByAutorizacao(EnumAutorizacaoUsuario autorizacao);

  Autoridade save(EnumAutorizacaoUsuario autorizacaoUsuario);
  Autoridade save(String autorizacao);

}

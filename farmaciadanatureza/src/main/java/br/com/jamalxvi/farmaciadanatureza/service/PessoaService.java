package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.RequisicaoDoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;

import java.util.List;

/**
 * Interface do Serviço de Usuário
 * @author      Jamal XVI <henriquearantest@gmail.com>
 * @version     0.1
 * @since       0.1
 */
public interface PessoaService {
  Pessoa findById(Long id);

  Pessoa findByCpf(String cpf);

  List<Pessoa> findAll();

  Pessoa save(Pessoa p);

  void remove(Pessoa p);
}

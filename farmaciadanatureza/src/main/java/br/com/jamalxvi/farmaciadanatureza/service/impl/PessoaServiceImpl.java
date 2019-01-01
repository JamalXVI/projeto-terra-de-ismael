package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.RequisicaoDoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.repository.PessoaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.UsuarioRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import br.com.jamalxvi.farmaciadanatureza.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Implementação do Serviço de pessoa
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */

@Service
public class PessoaServiceImpl extends BaseService implements PessoaService {
  @Autowired
  private PessoaRepository pessoaRepository;

  @Override
  public Pessoa findById(Long id) {
    Pessoa pessoa = null;
    try {
      pessoa = pessoaRepository.findById(id).orElse(null);
    } catch (Exception e) {
      return null;
    }
    return pessoa;
  }

  @Override
  public Pessoa findByCpf(String cpf) {
    Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
    return pessoa.filter(p -> p.getAtivo()).orElse(null);
  }

  @Override
  public List<Pessoa> findAll() {
    List<Pessoa> pessoas = pessoaRepository.findAll();
    return pessoas;
  }

  @Override
  public Pessoa save(Pessoa p) {
    Set<ConstraintViolation<Pessoa>> violations = validator.validate(p);
    if (!violations.isEmpty()) {
      return null;
    }
    Pessoa existeCpf = pessoaRepository.findByCpf(p.getCpf()).orElse(null);
    if (existeCpf != null) {
      if (existeCpf.getUsuario() != null) {
        return null;
      }
      p = Pessoa.builder().usuario(p.getUsuario()).nome(p.getNome())
          .sobrenome(p.getSobrenome()).cpf(existeCpf.getCpf())
          .build();
      p.setId(existeCpf.getId());
    }
    Pessoa pessoa = pessoaRepository.save(p);

    return pessoa;
  }

  @Override
  public void remove(Pessoa p) {
    this.pessoaRepository.delete(p);
  }
}

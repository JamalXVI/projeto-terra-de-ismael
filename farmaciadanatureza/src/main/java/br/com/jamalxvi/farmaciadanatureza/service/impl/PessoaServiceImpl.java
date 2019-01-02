package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.PessoaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_LISTAR_PESSOA_ATRIBUTO_NULO;

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

    /**
     * Construtor sobrecarregado contendo todos os parâmetros necessários
     * @param pessoaRepository o repositório de pessoas
     */
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * Construtor vazio para injeções de dependências
     */
    public PessoaServiceImpl() {
    }

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
        return pessoa.filter(p -> p.getAtivo() != null && p.getAtivo()).orElse(null);
    }

    @Override
    public List<Pessoa> findAll() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        try {
            return pessoas.stream().filter(p -> p.getAtivo())
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new MensagemExcecao(ERRO_LISTAR_PESSOA_ATRIBUTO_NULO.getMensagem(),
                    EnumExcecaoDto.ATRIBUTO_EXISTE);
        }
    }

    @Override
    public List<PessoaDto> findAllDto() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().filter(p -> p.getAtivo()).map(p ->
                PessoaDto.builder().ativo(
                        p.getAtivo()).cpf(p.getCpf()).nome(p.getNome()).sobrenome(
                                p.getSobrenome()).build()).collect(Collectors.toList());
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

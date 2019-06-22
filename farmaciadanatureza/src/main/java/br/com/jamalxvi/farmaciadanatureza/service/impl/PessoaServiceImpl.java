package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.PessoaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_INSERIR_PESSOA;
import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_LISTAR_PESSOA;
import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_LISTAR_PESSOA_ATRIBUTO_NULO;

/**
 * Implementação do Serviço de pessoa
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PessoaServiceImpl extends BaseServiceImpl<Pessoa, PessoaRepository> implements PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    void config() {
        this.repository = pessoaRepository;
    }

    @Override
    public Pessoa encontrarPeloCpf(String cpf) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
        return pessoa.filter(p -> p.getAtivo() != null && p.getAtivo()).orElseThrow(() ->
                new MensagemExcecao(ERRO_LISTAR_PESSOA.getMensagem(),
                        EnumExcecaoDto.NAO_ENCONTRADO));
    }

    @Override
    public List<Pessoa> listarTodos() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        try {
            return pessoas.stream().filter(p -> p.getAtivo())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MensagemExcecao(ERRO_LISTAR_PESSOA_ATRIBUTO_NULO.getMensagem(),
                    EnumExcecaoDto.ATRIBUTO_EXISTE);
        }
    }

    @Override
    public List<PessoaDto> listarTodosDto() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return ConstruirPessoasDto(pessoas);
    }

    @Override
    public Pessoa salvar(Pessoa p) {
        Set<ConstraintViolation<Pessoa>> violations = validator.validate(p);
        if (!violations.isEmpty()) {
            throw new MensagemExcecao(ERRO_INSERIR_PESSOA.getMensagem(), EnumExcecaoDto.SALVAR);
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
    public void remover(Pessoa p) {
        this.pessoaRepository.delete(p);
    }

    @Override
    public List<PessoaDto> pesquisar(String pesquisa, Integer limite) {
        List<Pessoa> pessoas = pessoaRepository.findByPesquisa(pesquisa, limite);
        return ConstruirPessoasDto(pessoas);
    }

    private List<PessoaDto> ConstruirPessoasDto(List<Pessoa> pessoas) {
        return pessoas.stream().filter(p -> p.getAtivo()).map(p ->
                PessoaDto.builder().ativo(
                        p.getAtivo()).cpf(p.getCpf()).nome(p.getNome()).sobrenome(
                        p.getSobrenome()).codigo(p.getId()).build()).collect(Collectors.toList());
    }
}

package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.models.Medico;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl extends BaseServiceImpl<Medico, MedicoRepository> implements MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    MedicoServiceImpl()
    {
        super();
        this.repository = medicoRepository;
    }

    @Override
    void config() {
        this.repository = medicoRepository;
    }

    @Override
    public List<MedicoDto> listarTodosDto() {
        List<Medico> medicos = medicoRepository.findAll();
        List<MedicoDto> medicoDtos = construirMedicoDto(medicos);
        return medicoDtos;
    }

    @Override
    public List<MedicoDto> pesquisar(String pesquisa, Integer limite) {
        List<Medico> medicos = repository.findByPesquisa(pesquisa, limite);
        return construirMedicoDto(medicos);
    }

    private List<MedicoDto> construirMedicoDto(List<Medico> medicos) {
        return medicos.stream().filter(m -> m.getPessoa().getAtivo()).map(m -> MedicoDto.builder()
                .codigo(m.getId()).nome(m.getPessoa().getNome()).cpf(m.getPessoa().getCpf())
                .ativo(m.getPessoa().getAtivo()).sobrenome(m.getPessoa().getSobrenome())
                .crm(m.getCrm()).build()).collect(Collectors.toList());
    }
}

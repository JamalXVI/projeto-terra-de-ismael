package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.models.Medico;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class MedicoServiceImpl extends BaseService implements MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<MedicoDto> listarTodosDto() {
        List<Medico> medicos = medicoRepository.findAll();
        List<MedicoDto> medicoDtos = medicos.stream().filter(m -> m.getPessoa().getAtivo()).map(m -> MedicoDto.builder().nome
                (m.getPessoa().getNome()).cpf(m.getPessoa().getCpf()).ativo(m.getPessoa()
                .getAtivo()).sobrenome(m.getPessoa().getSobrenome()).crm(m.getCrm()).build())
                .collect(Collectors.toList());
        return medicoDtos;
    }
}

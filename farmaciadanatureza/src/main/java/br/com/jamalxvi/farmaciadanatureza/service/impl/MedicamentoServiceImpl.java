package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.repository.CapsulaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.FloralRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.HomeopatiaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.OutrosMedicamentosRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.PlantaDesidratadaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.PomadaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.TinturaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_BUSCAR_TIPO_MEDICAMENTO;
import static br.com.jamalxvi.farmaciadanatureza.util.MetodosGenericosUtils.transformarEnumEmDTO;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private CapsulaRepository capsulaRepository;
    @Autowired
    private FloralRepository floralRepository;
    @Autowired
    private HomeopatiaRepository homeopatiaRepository;
    @Autowired
    private OutrosMedicamentosRepository outrosMedicamentosRepository;
    @Autowired
    private PlantaDesidratadaRepository plantaDesidratadaRepository;
    @Autowired
    private PomadaRepository pomadaRepository;
    @Autowired
    private TinturaRepository tinturaRepository;

    @Override
    public List<ElementoDeListaDto> retornaListaDePossiveisMedicamentos() {
        return Arrays.asList(EnumTipoMedicamento.values()).stream()
                .map(m -> transformarEnumEmDTO(m)).collect(Collectors.toList());
    }

    @Override
    public List<ElementoDeListaDto> retornarItensDoMedicamento(Integer tipoMedicamento) {
        EnumTipoMedicamento medicamento = EnumTipoMedicamento.encontrarPeloId(tipoMedicamento).orElseThrow(() ->
                new MensagemExcecao(ERRO_BUSCAR_TIPO_MEDICAMENTO.getMensagem(),
                        EnumExcecaoDto.NAO_ENCONTRADO)
        );
        try {
            JpaRepository<? extends Medicamento, Long> repository = (JpaRepository<? extends
                    Medicamento, Long>) this.getClass().getDeclaredField(medicamento.getClazz()
                    .getSimpleName().toLowerCase() + "Repository").get(this);
            List<? extends Medicamento> medicamentos = repository.findAll();
            return  medicamentos.stream().map(m -> new ElementoDeListaDto( m.getNome(),  m.getId()
                    .toString()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MensagemExcecao(ERRO_BUSCAR_TIPO_MEDICAMENTO.getMensagem(),
                    EnumExcecaoDto.NAO_ENCONTRADO);
        }
    }
}

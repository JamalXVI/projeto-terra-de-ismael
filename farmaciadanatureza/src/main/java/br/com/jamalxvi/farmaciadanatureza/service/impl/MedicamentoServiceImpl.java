package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.util.MetodosGenericosUtils.transformarEnumEmDTO;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {
    @Override
    public List<ElementoDeListaDto> retornaListaDePossiveisMedicamentos() {
        return Arrays.asList(EnumTipoMedicamento.values()).stream()
                .map(m -> transformarEnumEmDTO(m)).collect(Collectors.toList());
    }
}

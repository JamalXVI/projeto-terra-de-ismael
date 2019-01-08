package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {
    @Override
    public List<ElementoDeListaDto> retornaListaDePossiveisMedicamentos() {
        return ;
    }
}

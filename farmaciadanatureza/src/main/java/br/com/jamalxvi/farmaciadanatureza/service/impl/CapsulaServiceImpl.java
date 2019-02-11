package br.com.jamalxvi.farmaciadanatureza.service.impl;

import java.math.BigDecimal;

import br.com.jamalxvi.farmaciadanatureza.util.ProcessaMedicamentoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jamalxvi.farmaciadanatureza.models.Capsula;
import br.com.jamalxvi.farmaciadanatureza.models.CapsulaEstoque;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;
import br.com.jamalxvi.farmaciadanatureza.repository.CapsulaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.CapsulaService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CapsulaServiceImpl extends BaseService implements CapsulaService {
  @Autowired
  private CapsulaRepository capsulaRepository;

  @Override
  public RetornoDosMedicamentosDto popularDto(Capsula capsula) {
    RetornoDosMedicamentosDto dto = new RetornoDosMedicamentosDto();
    dto.setUnidade(CapsulaEstoque.getUnidade().toString());
    ProcessaMedicamentoUtils.processar(dto, capsula);
    return dto;
  }
}

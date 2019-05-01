package br.com.jamalxvi.farmaciadanatureza.service.impl;

import static br.com.jamalxvi.farmaciadanatureza.util.MetodosGenericosUtils.transformarEnumEmDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicamentoRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.PrincipioAtivoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {
  @Autowired
  MedicamentoRepository medicamentoRepository;
  @Autowired
  PrincipioAtivoRepository principioAtivoRepository;

  @Override
  public List<ElementoDeListaDto> retornaListaDeTipoDeMedicamento() {
    return Arrays.asList(EnumTipoMedicamento.values()).stream().map(m -> transformarEnumEmDTO(m))
        .collect(Collectors.toList());
  }

  @Override
  public List<ElementoDeListaDto> retonarListaPrincipioAtivo(String pesquisa, Integer limite) {
    return principioAtivoRepository.findByPesquisa(pesquisa, limite).stream()
        .map(p -> new ElementoDeListaDto(p.getNomePrincipal(), p.getId().toString()))
        .collect(Collectors.toList());
  }

}

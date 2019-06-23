package br.com.jamalxvi.farmaciadanatureza.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicamentoRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.PrincipioAtivoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoServiceImpl extends BaseServiceImpl<Medicamento, MedicamentoRepository>
    implements MedicamentoService {
  @Autowired
  MedicamentoRepository medicamentoRepository;
  @Autowired
  PrincipioAtivoRepository principioAtivoRepository;

  @Override
  void config() {
    this.repository = medicamentoRepository;
  }

  @Override
  public List<ElementoDeListaDto> retornaListaDeTipoDeMedicamento() {
    return montaDtoDeLista(EnumTipoMedicamento.values());
  }

  @Override
  public List<ElementoDeListaDto> retonarListaPrincipioAtivo(String pesquisa, Integer limite) {
    return principioAtivoRepository.findByPesquisa(pesquisa, limite).stream()
        .map(p -> new ElementoDeListaDto(p.getNomePrincipal(), p.getId().toString()))
        .collect(Collectors.toList());
  }

  @Override
  public Medicamento cria(Integer tipo){

    EnumTipoMedicamento tipoMedicamento = EnumTipoMedicamento
            .encontra(tipo).orElseThrow(() -> new RuntimeException());
    Medicamento medicamento = Medicamento.builder().tipoMedicamento(tipoMedicamento).build();
    return salva(medicamento);
  }
}

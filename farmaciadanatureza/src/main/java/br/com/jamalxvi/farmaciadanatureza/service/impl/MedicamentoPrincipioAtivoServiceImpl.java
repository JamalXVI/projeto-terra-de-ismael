package br.com.jamalxvi.farmaciadanatureza.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.MedicamentoPrincipioAtivo;
import br.com.jamalxvi.farmaciadanatureza.models.MedicamentoPrincipioAtivoPk;
import br.com.jamalxvi.farmaciadanatureza.models.PrincipioAtivo;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicamentoPrincipioAtivoDto;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicamentoPrincipioAtivoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoPrincipioAtivoService;
import br.com.jamalxvi.farmaciadanatureza.service.PrincipioAtivoService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoPrincipioAtivoServiceImpl implements MedicamentoPrincipioAtivoService {

  @Autowired
  PrincipioAtivoService principioAtivoService;
  @Autowired
  MedicamentoPrincipioAtivoRepository medicamentoPrincipioAtivoRepository;


  @Override
  public MedicamentoPrincipioAtivo cria(MedicamentoPrincipioAtivoDto dto, Medicamento medicamento) {

    PrincipioAtivo principioAtivo = principioAtivoService.encontra(dto.getId());

    MedicamentoPrincipioAtivo medicamentoPrincipioAtivo = MedicamentoPrincipioAtivo.builder()
        .chave(MedicamentoPrincipioAtivoPk.builder().idPrincipioAtivo(dto.getId())
            .idMedicamento(medicamento.getId()).build())
        .principioAtivo(principioAtivo).medicamento(medicamento).proporcao(dto.getProporcao())
        .build();

    return salva(medicamentoPrincipioAtivo);
  }

  public MedicamentoPrincipioAtivo salva(MedicamentoPrincipioAtivo medicamentoPrincipioAtivo) {
    try {
      return medicamentoPrincipioAtivoRepository.save(medicamentoPrincipioAtivo);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

}

package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.MedicamentoPrincipioAtivo;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicamentoPrincipioAtivoDto;

public interface MedicamentoPrincipioAtivoService {

    MedicamentoPrincipioAtivo cria(MedicamentoPrincipioAtivoDto dto, Medicamento medicamento);
}

package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.Receita;
import br.com.jamalxvi.farmaciadanatureza.models.ReceitaMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ReceitaMedicamentoDto;

public interface ReceitaMedicamentoService {
    ReceitaMedicamento cria(Receita receita, ReceitaMedicamentoDto receitaMedicamentoDto, Medicamento medicamento);
}

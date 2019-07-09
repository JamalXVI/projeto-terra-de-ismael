package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.Receita;
import br.com.jamalxvi.farmaciadanatureza.models.ReceitaMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.ReceitaMedicamentoPk;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ReceitaMedicamentoDto;
import br.com.jamalxvi.farmaciadanatureza.repository.ReceitaMedicamentoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.ReceitaMedicamentoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaMedicamentoServiceImpl implements ReceitaMedicamentoService {
    @Autowired
    ReceitaMedicamentoRepository receitaMedicamentoRepository;

    @Override
    public ReceitaMedicamento cria(Receita receita, ReceitaMedicamentoDto receitaMedicamentoDto,
                                   Medicamento medicamento) {

        ReceitaMedicamento receitaMedicamento = ReceitaMedicamento.builder()
                .peso(receitaMedicamentoDto.getPeso()).medicamento(medicamento).receita(receita)
                .chave(ReceitaMedicamentoPk.builder().idReceita(receita.getId())
                        .idMedicamento(medicamento.getId()).build()).lote(receitaMedicamentoDto.getLote())
                .posologia(receitaMedicamentoDto.getPosologia())
                .quantidade(receitaMedicamentoDto.getQuantidade())
                .validadeReceita(receitaMedicamentoDto.getValidade()).build();

        return salva(receitaMedicamento);
    }

    private ReceitaMedicamento salva(ReceitaMedicamento receitaMedicamento) {
        try {
            return receitaMedicamentoRepository.save(receitaMedicamento);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.input.ReceberMedicamentosDto;

import java.util.List;

/**
 * Interface de Serviço para os medicamentos em geral (que é comum para todos)
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface MedicamentoService {

    /**
     * Retorna a lista de possíveis medicamentos
     *
     * @return A lista de medicamentos encapsuladas em DTOs
     */
    List<ElementoDeListaDto> retornaListaDePossiveisMedicamentos();


    /**
     * Retorna a lista de itens do medicamento
     *
     * @return A lista de medicamentos encapsuladas em DTOs
     */
    List<ElementoDeListaDto> retornarItensDoMedicamento(Integer tipoMedicamento);


    /**
     * Retorna as informações de preenchimento de um medicamento específico
     *
     * @return As informações de um medicamento
     */
    RetornoDosMedicamentosDto retornarInformacoesDoMedicamento(ReceberMedicamentosDto receberMedicamentosDto);
}

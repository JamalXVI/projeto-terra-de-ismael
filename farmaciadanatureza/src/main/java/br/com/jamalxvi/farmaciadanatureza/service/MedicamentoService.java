package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.FormularioReceitaDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;

import java.util.List;

/**
 * Interface de Serviço para os medicamentos em geral (que é comum para todos)
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface MedicamentoService extends BaseService<Medicamento> {

    /**
     * Retorna a lista de possíveis medicamentos
     *
     * @return A lista de medicamentos encapsuladas em DTOs
     */
    List<ElementoDeListaDto> retornaListaDeTipoDeMedicamento();

    List<ElementoDeListaDto> retonarListaPrincipioAtivo(String pesquisa, Integer limite);

    Medicamento cria(Integer tipo);

    Medicamento salva(Medicamento m);
}

package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Medico;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;

import java.util.List;

/**
 * Interface do Serviço de Médico
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface MedicoService extends BaseService<Medico> {

    /**
     * Retorna todos os médicos ativos convertidas em Dto
     *
     * @return a Lista com todas as pessoas em Dto
     */
     List<MedicoDto> listarTodosDto();

    /**
     * Retorna uma lista de pessoas de acordo com a pesquisa
     * @param pesquisa o que foi digitado, em string
     * @param limite o limite de resultados
     * @return a lista de pessoas conforme os resultados
     */
    List<MedicoDto> pesquisar(String pesquisa, Integer limite);
}

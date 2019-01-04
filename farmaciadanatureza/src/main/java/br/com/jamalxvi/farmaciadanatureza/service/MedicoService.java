package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;

import java.util.List;

/**
 * Interface do Serviço de Médico
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface MedicoService {

    /**
     * Retorna todos os médicos ativos convertidas em Dto
     *
     * @return a Lista com todas as pessoas em Dto
     */
     List<MedicoDto> listarTodosDto();
}

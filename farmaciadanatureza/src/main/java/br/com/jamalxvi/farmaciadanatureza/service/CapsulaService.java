package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Capsula;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;

/**
 * Interface de Serviço para a Cápsula
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface CapsulaService {
    public RetornoDosMedicamentosDto popularDto(Capsula capsula);
}

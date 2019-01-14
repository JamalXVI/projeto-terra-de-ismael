package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * Esta interface representa todos os medicados
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface Medicamento {
    public String getNome();

    public Long getId();
}

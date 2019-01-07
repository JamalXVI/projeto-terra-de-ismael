package br.com.jamalxvi.farmaciadanatureza.enums;

import br.com.jamalxvi.farmaciadanatureza.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Define os tipos de medicamentos
 * {@link br.com.jamalxvi.farmaciadanatureza.models.Estocavel}
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@AllArgsConstructor
public enum EnumTipoMedicamento {
    CAPSULA(1, Capsula.class),
    FLORAL(2, Floral.class),
    HOMEOPATIA(3, Homeopatia.class),
    OUTROS_MEDICAMENTOS(4, OutrosMedicamentos.class),
    PLANTA_DESIDRATADA(5, PlantaDesidratada.class),
    POMADA(6, Pomada.class),
    TINTURA(7, Tintura.class);

    @Getter
    private Integer id;
    @Getter
    private Class clazz;

}

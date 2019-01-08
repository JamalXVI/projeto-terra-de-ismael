package br.com.jamalxvi.farmaciadanatureza.enums;

import br.com.jamalxvi.farmaciadanatureza.models.*;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.ElementoParaIrNaLista;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Define os tipos de medicamentos
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@AllArgsConstructor
public enum EnumTipoMedicamento implements ElementoParaIrNaLista {
    CAPSULA(1, "capsula", Capsula.class),
    FLORAL(2, "floral", Floral.class),
    HOMEOPATIA(3, "homeopatia", Homeopatia.class),
    OUTROS_MEDICAMENTOS(4, "outrosMedicamentos", OutrosMedicamentos.class),
    PLANTA_DESIDRATADA(5, "plantaDesidratada", PlantaDesidratada.class),
    POMADA(6, "pomada", Pomada.class),
    TINTURA(7, "tintura", Tintura.class);

    @Getter
    private Integer id;

    @Getter
    private String desc;

    @Getter
    private Class clazz;

}

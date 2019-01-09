package br.com.jamalxvi.farmaciadanatureza.enums;

import br.com.jamalxvi.farmaciadanatureza.models.Capsula;
import br.com.jamalxvi.farmaciadanatureza.models.EntidadeBase;
import br.com.jamalxvi.farmaciadanatureza.models.Floral;
import br.com.jamalxvi.farmaciadanatureza.models.Homeopatia;
import br.com.jamalxvi.farmaciadanatureza.models.OutrosMedicamentos;
import br.com.jamalxvi.farmaciadanatureza.models.PlantaDesidratada;
import br.com.jamalxvi.farmaciadanatureza.models.Pomada;
import br.com.jamalxvi.farmaciadanatureza.models.Tintura;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.ElementoParaIrNaLista;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

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
    private Class<? extends Medicamento> clazz;

    /**
     * Retorna o enum do Tipo de medicamento pelo ID
     * @param id o id do tipo de medicamento
     * @return um optional com o Enum de medicamento
     */
    public static Optional<EnumTipoMedicamento> encontrarPeloId(Integer id) {
        return Arrays.asList(values()).stream().filter(m -> m.getId() == id).findFirst();
    }
}

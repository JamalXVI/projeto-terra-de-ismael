package br.com.jamalxvi.farmaciadanatureza.enums;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.ElementoParaIrNaLista;
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
    CAPSULA(1, "capsula"),
    FLORAL(2, "floral"),
    HOMEOPATIA(3, "homeopatia"),
    OUTROS_MEDICAMENTOS(4, "outrosMedicamentos"),
    PLANTA_DESIDRATADA(5, "plantaDesidratada"),
    POMADA(6, "pomada"),
    TINTURA(7, "tintura");

    @Getter
    private Integer id;

    @Getter
    private String desc;

    /**
     * Retorna o enum do Tipo de medicamento pelo ID
     * @param id o id do tipo de medicamento
     * @return um optional com o Enum de medicamento
     */
    public static Optional<EnumTipoMedicamento> encontrarPeloId(Integer id) {
        return Arrays.asList(values()).stream().filter(m -> m.getId() == id).findFirst();
    }
}

package br.com.jamalxvi.farmaciadanatureza.enums;

import br.com.jamalxvi.farmaciadanatureza.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento.*;

/**
 * Define as unidades a serem usadas nos medicamentos.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@AllArgsConstructor
@Getter
public enum EnumUnidadesMetricas {
    MILILITROS("mililitro", "ml", new EnumTipoMedicamento[]{TINTURA}),
    GRAMAS("grama", "g", new EnumTipoMedicamento[]{PLANTA_DESIDRATADA, CAPSULA}),
    UNIDADES("unidade", "un", new EnumTipoMedicamento[]{OUTROS_MEDICAMENTOS, POMADA}),
    SACOS("saco", "sc", new EnumTipoMedicamento[]{});

  private final String unidade;
  private final String abreviacao;
  private final EnumTipoMedicamento[] clazzs;

}

package br.com.jamalxvi.farmaciadanatureza.enums;

/**
 * Define as unidades a serem usadas nos medicamentos.
 * {@link br.com.jamalxvi.farmaciadanatureza.models.Estocavel}
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public enum EnumUnidadesMetricas {
    MILILITROS("mililitro", "mL"),
    GRAMAS("grama", "g"),
    UNIDADES("unidade", "un"),
    SACOS("saco", "sc");

  private final String unidade;
  private final String abreviacao;

  EnumUnidadesMetricas(String unidade, String abreviacao) {
    this.unidade = unidade;
    this.abreviacao = abreviacao;
  }
}

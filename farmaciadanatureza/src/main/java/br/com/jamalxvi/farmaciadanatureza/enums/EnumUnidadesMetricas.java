package br.com.jamalxvi.farmaciadanatureza.enums;

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

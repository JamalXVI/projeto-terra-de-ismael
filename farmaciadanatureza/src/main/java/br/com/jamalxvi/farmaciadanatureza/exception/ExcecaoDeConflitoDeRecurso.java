package br.com.jamalxvi.farmaciadanatureza.exception;

import lombok.Getter;
import lombok.Setter;

public class ExcecaoDeConflitoDeRecurso extends RuntimeException {

  private static final long serialVersionUID = 1791564636123821405L;
  @Getter @Setter
  private Long idDoRecurso;

  public ExcecaoDeConflitoDeRecurso(Long idDoRecurso, String message) {
    super(message);
    this.setIdDoRecurso(idDoRecurso);
  }
}

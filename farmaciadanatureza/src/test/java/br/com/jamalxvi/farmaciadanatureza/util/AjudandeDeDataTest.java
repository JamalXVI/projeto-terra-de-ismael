package br.com.jamalxvi.farmaciadanatureza.util;

import static junit.framework.TestCase.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class AjudandeDeDataTest {

  @Test
  public void localDateParaString() {
    String data = AjudandeDeData.localDateParaString(LocalDate.now());
    assertTrue("A data não pode estar vazia", !data.isEmpty());
    assertTrue("O tamanho da data deve ser 10", data.length() == 10);
  }
}

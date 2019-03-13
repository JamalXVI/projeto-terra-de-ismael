package br.com.jamalxvi.farmaciadanatureza.util;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class CodigoDigestTest {

  @Test
  public void digerir() {
    final String resultado = "33";
    final String digerido = CodigoDigest.digerir(resultado);
    assertTrue("A chave revertida deve ser a mesma da original",
        resultado.equals(CodigoDigest.reverter(digerido)));
  }
}

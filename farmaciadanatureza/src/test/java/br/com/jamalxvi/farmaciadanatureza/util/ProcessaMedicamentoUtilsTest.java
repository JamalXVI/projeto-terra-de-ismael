package br.com.jamalxvi.farmaciadanatureza.util;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.jamalxvi.farmaciadanatureza.models.Capsula;
import br.com.jamalxvi.farmaciadanatureza.models.CapsulaEstoque;
import br.com.jamalxvi.farmaciadanatureza.models.CapsulaUsoEstoque;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;

public class ProcessaMedicamentoUtilsTest {

  @Test
  public void processar() {
    CapsulaUsoEstoque estoque1 = CapsulaUsoEstoque.builder().quantidade(new BigDecimal(2)).build();
    CapsulaUsoEstoque estoque2 = CapsulaUsoEstoque.builder().quantidade(new BigDecimal(1)).build();
    estoque1.setId(1L);
    estoque2.setId(2L);
    List<CapsulaUsoEstoque> usoEstoques =
        Arrays.asList(new CapsulaUsoEstoque[] {estoque1, estoque2});
    CapsulaEstoque estoque = CapsulaEstoque.builder().usoEstoques(usoEstoques)
        .dataVencimentoLote(LocalDate.now().plusDays(1)).quantidade(new BigDecimal(10)).build();
    CapsulaEstoque estoqueVencido = CapsulaEstoque.builder().usoEstoques(usoEstoques)
        .dataVencimentoLote(LocalDate.now().minusDays(1)).quantidade(new BigDecimal(15)).build();
    estoque.setId(1L);
    estoqueVencido.setId(2L);
    List<CapsulaEstoque> estoques = Arrays.asList(new CapsulaEstoque[] {estoque, estoqueVencido});
    Capsula capsula = Capsula.builder().pesoMinimo(new BigDecimal(1)).pesoMaximo(new BigDecimal(2))
        .duracaoLote(6L).estoque(estoques).build();
    capsula.setNome("Capsúla X");
    capsula.setNomeCientifico("Capsularium X");
    RetornoDosMedicamentosDto dto = new RetornoDosMedicamentosDto();
    ProcessaMedicamentoUtils.processar(dto, capsula);
    assertTrue("O estoque deve ser 7", dto.getQuantidade().compareTo(new BigDecimal(7)) == 0);
    assertTrue("Deve possuir nome", dto.getNome().equals(capsula.getNome()));
    assertTrue("Deve possuir nome científico",
        dto.getNomeCientifico().equals(capsula.getNomeCientifico()));
  }
}

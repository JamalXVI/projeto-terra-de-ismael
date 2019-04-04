package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.models.Capsula;
import br.com.jamalxvi.farmaciadanatureza.models.CapsulaEstoque;
import br.com.jamalxvi.farmaciadanatureza.models.CapsulaUsoEstoque;
import br.com.jamalxvi.farmaciadanatureza.repository.CapsulaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.CapsulaService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CapsulaServiceTest extends BaseTest {
    @Mock
    private CapsulaService capsulaService;
    @Mock
    private CapsulaRepository capsulaRepository;
    @Before
    public void setUp(){
        this.capsulaService = new CapsulaServiceImpl(capsulaRepository);
    }
    @Test
    private void popularDto_AllValid_Ok(){
        criaCapsula();
    }

    private void criaCapsula() {
        CapsulaUsoEstoque estoque1 = CapsulaUsoEstoque.builder().quantidade(new BigDecimal(2)).build();
        CapsulaUsoEstoque estoque2 = CapsulaUsoEstoque.builder().quantidade(new BigDecimal(1)).build();
        List<CapsulaUsoEstoque> usoEstoques =
                Arrays.asList(new CapsulaUsoEstoque[] {estoque1, estoque2});
        CapsulaEstoque estoque = CapsulaEstoque.builder().usoEstoques(usoEstoques)
                .dataVencimentoLote(LocalDate.now().plusDays(1)).quantidade(new BigDecimal(10)).build();
        List<CapsulaEstoque> estoques = Arrays.asList(new CapsulaEstoque[] {estoque});
        Capsula capsula = Capsula.builder().pesoMinimo(new BigDecimal(1)).pesoMaximo(new BigDecimal(2))
                .duracaoLote(6L).estoque(estoques).build();
        capsula.setNome("Capsúla X");
        capsula.setNomeCientifico("Capsularium X");
    }
}

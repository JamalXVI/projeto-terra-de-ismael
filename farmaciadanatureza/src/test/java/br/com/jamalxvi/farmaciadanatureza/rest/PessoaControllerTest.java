package br.com.jamalxvi.farmaciadanatureza.rest;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.PessoaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import br.com.jamalxvi.farmaciadanatureza.service.impl.PessoaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_PESQUISA_INVALIDA;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PessoaControllerTest extends BaseTest {
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private PessoaService pessoaService;

    private PessoaController pessoaController;
    @Before
    public void setUp() throws Exception {
        pessoaRepository = mock(PessoaRepository.class);
        when(pessoaRepository.findAll()).thenReturn(new ArrayList<>());
        this.pessoaService = new PessoaServiceImpl(pessoaRepository);
        when(this.pessoaService.listarTodosDto()).thenReturn(new ArrayList<>());
        when(this.pessoaService.pesquisar(anyString(), anyInt())).thenReturn(new ArrayList<>());
        pessoaController = new PessoaController(this.pessoaService);
    }

    @Test
    public void list() {
        List<PessoaDto> pessoa = pessoaController.list();
        assertTrue("A lista não deve ser nula e vaiza", pessoa != null && pessoa.size() == 0);
    }

    @Test
    public void pesquisarOk() {
        List<PessoaDto> pessoa = pessoaController.pesquisar("", 3);
        assertTrue("A lista não deve ser nula e vaiza", pessoa != null && pessoa.size() == 0);
    }

    @Test
    public void pesquisaErroParametroNegativo() {
        esperarErroGenerico(ERRO_PESQUISA_INVALIDA.getMensagem());
        List<PessoaDto> pessoa = pessoaController.pesquisar("", -1);
    }

    @Test
    public void pesquisaErroPesquisaNula() {
        esperarErroGenerico(ERRO_PESQUISA_INVALIDA.getMensagem());
        List<PessoaDto> pessoa = pessoaController.pesquisar(null, 1);
    }

    @Test
    public void pesquisaErroLimiteNulo() {
        esperarErroGenerico(ERRO_PESQUISA_INVALIDA.getMensagem());
        List<PessoaDto> pessoa = pessoaController.pesquisar(null, 1);
    }
}
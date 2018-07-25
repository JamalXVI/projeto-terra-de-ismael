package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.FarmaciadanaturezaApplication;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FarmaciadanaturezaApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaServiceImplTest {
    @Autowired
    private PessoaService pessoaService;
    private List<Pessoa> erros;

    @Before
    public void setUp() throws Exception {
        erros = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        List<Pessoa> pessoas = pessoaService.findAll();
        for (Pessoa p : pessoas) {
            this.pessoaService.remove(p);
        }
    }

    @Test
    public void save() {
        String palavrao = "";
        for (int i = 0; i < 256; i++) {
            palavrao += "a";
        }
        Pessoa pessoa = criarPessoa("233.255.678-93", "João", "da Silva");
        Pessoa erro1 = criarPessoa("23325767893", "João", "da Silva");
        Pessoa erro2 = criarPessoa("", "João", "da Silva");
        Pessoa erro3 = criarPessoa("233.257.678-93", "", "da Silva");
        Pessoa erro4 = criarPessoa("233.257.678-93", "João", "");
        Pessoa erro5 = criarPessoa("233.257.678-93", "João", null);
        Pessoa erro6 = criarPessoa(null, "João", "da Silva");
        Pessoa erro7 = criarPessoa("233.257.678-93", null, "da Silva");
        Pessoa erro8 = criarPessoa("233.257.678-93", palavrao, "da Silva");
        Pessoa erro9 = criarPessoa("233.257.678-93", "João", palavrao);
        assertTrue(pessoa != null && erro1 == null && erro2 == null && erro3 == null && erro4 == null &&
                erro5 == null && erro6 == null && erro7 == null && erro7 == null && erro8 == null && erro9 == null);
    }

    private Pessoa criarPessoa(String cpf, String nome, String sobrenome) {
        Pessoa pessoa = Pessoa.builder().cpf(cpf).nome(nome).sobrenome(sobrenome).build();
        pessoa = this.pessoaService.save(pessoa);
        return pessoa;
    }

    @Test
    public void findByCpf() {
        Pessoa pessoa = criarPessoa("233.257.678-93", "João", "da Silva");
        pessoa = pessoaService.findByCpf("233.257.678-93");
        erros.add(pessoaService.findByCpf(null));
        erros.add(pessoaService.findByCpf(""));
        erros.add(pessoaService.findByCpf("2325767893"));
        List<Pessoa> pessoas = filtrarErros();
        assertPessoaEErros(pessoa, pessoas);
    }

    private void assertPessoaEErros(Pessoa pessoa, List<Pessoa> pessoas) {
        assertTrue(pessoa != null && pessoas.isEmpty());
    }

    private List<Pessoa> filtrarErros() {
        return erros.stream().filter(p -> p != null).collect(Collectors.toList());
    }

    @Test
    public void findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();
        assertTrue(pessoas == null || pessoas.size() == 0);
    }

    @Test
    public void findById() {
        Pessoa pessoa = criarPessoa("233.257.678-93", "João", "da Silva");
        pessoa = pessoaService.findById(pessoa.getId());
        erros.add(pessoaService.findById(new Long(0)));
        erros.add(pessoaService.findById(new Long(-1)));
        erros.add(pessoaService.findById(new Long(100)));
        erros.add(pessoaService.findById(null));
        List<Pessoa> pessoas = filtrarErros();
        assertPessoaEErros(pessoa, pessoas);
    }

    @Test
    public void remove() {
        Pessoa pessoa = criarPessoa("233.269.678-93", "João", "da Silva");
        pessoaService.remove(pessoa);
        Pessoa busca = pessoaService.findByCpf("233.269.678-93");
        List<Pessoa> pessoas = pessoaService.findAll();
        assertTrue(busca == null && pessoas.isEmpty());
    }
}
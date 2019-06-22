package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.models.*;
import br.com.jamalxvi.farmaciadanatureza.models.dto.FormularioReceitaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.ReceitaMedicamentoRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.ReceitaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaServiceImpl extends BaseServiceImpl<Receita, ReceitaRepository> implements ReceitaService {
  @Autowired
  UsuarioService usuarioService;
  @Autowired
  PessoaService pessoaService;
  @Autowired
  MedicoService medicoService;
  @Autowired
  MedicamentoService medicamentoService;
  @Autowired
  MedicamentoPrincipioAtivoService medicamentoPrincipioAtivoService;
  @Autowired
  ReceitaMedicamentoService receitaMedicamentoService;


  @Autowired
  ReceitaRepository receitaRepository;

  @Override
  public Receita cria(FormularioReceitaDto dto) {

    final Usuario usuario = usuarioService.getUsuarioLogado();

    final Pessoa paciente = pessoaService.encontra(dto.getPaciente());

    final Medico medico = medicoService.encontra(dto.getMedico());

    Receita receita = Receita.builder().medico(medico).usuario(usuario).pessoa(paciente)
        .validadeReceita(dto.getDataReceita()).build();
    return salva(receita);
  }


  @Override
  public void cadastra(FormularioReceitaDto receitaDto) {

    final Receita receita = cria(receitaDto);

    List<ReceitaMedicamento> receitaMedicamentos = new ArrayList<>();
    receitaDto.getReceita().stream().peek(receitaMedicamentoDto -> {

      List<MedicamentoPrincipioAtivo> principioAtivos = new ArrayList<>();

      final Medicamento medicamento = medicamentoService.cria(receitaMedicamentoDto.getTipo());

      receitaMedicamentoDto.getPrincipioAtivos().stream().peek(principioAtivoDto -> {

        principioAtivos.add(medicamentoPrincipioAtivoService.cria(principioAtivoDto, medicamento));

      });

      medicamento.setPrincipioAtivo(principioAtivos);

      final Medicamento med = medicamentoService.salva(medicamento);

      receitaMedicamentos.add(receitaMedicamentoService.cria(receita, receitaMedicamentoDto, medicamento));


    });
    salva(receita);
  }

  @Override
  void config() {
    this.repository = this.receitaRepository;
  }
}

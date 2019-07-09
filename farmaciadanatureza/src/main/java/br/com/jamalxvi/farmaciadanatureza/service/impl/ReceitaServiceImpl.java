package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.models.*;
import br.com.jamalxvi.farmaciadanatureza.models.dto.FormularioReceitaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.ReceitaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  ReportService reportService;


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
  public JasperPrint cadastra(FormularioReceitaDto receitaDto) {

    final Receita receita = cria(receitaDto);

    List<ReceitaMedicamento> receitaMedicamentos = new ArrayList<>();
    receitaDto.getReceita().stream().forEach(receitaMedicamentoDto -> {

      List<MedicamentoPrincipioAtivo> principioAtivos = new ArrayList<>();

      final Medicamento medicamento = medicamentoService.cria(receitaMedicamentoDto.getTipo());

      receitaMedicamentoDto.getPrincipioAtivos().stream().forEach(principioAtivoDto -> {

        principioAtivos.add(medicamentoPrincipioAtivoService.cria(principioAtivoDto, medicamento));

      });

      medicamento.setPrincipioAtivo(principioAtivos);

      final Medicamento med = medicamentoService.salva(medicamento);

      receitaMedicamentos.add(receitaMedicamentoService.cria(receita, receitaMedicamentoDto, med));


    });

    receita.setReceita(receitaMedicamentos);
    Receita receitaSalva = salva(receita);


    Map<String, Object> params = new HashMap<>();
    params.put("P_ID", receitaSalva.getId());

    return reportService.gerarRelatorio("etiqueta", params);
  }

  @Override
  void config() {
    this.repository = this.receitaRepository;
  }
}

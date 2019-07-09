package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Receita;
import br.com.jamalxvi.farmaciadanatureza.models.dto.FormularioReceitaDto;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReceitaService {

    Receita cria(FormularioReceitaDto dto);

    JasperPrint cadastra(FormularioReceitaDto receitaDto);
}

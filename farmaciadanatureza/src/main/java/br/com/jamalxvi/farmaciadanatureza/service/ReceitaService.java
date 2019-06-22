package br.com.jamalxvi.farmaciadanatureza.service;

import br.com.jamalxvi.farmaciadanatureza.models.Medico;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.Receita;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.FormularioReceitaDto;

public interface ReceitaService {

    Receita cria(FormularioReceitaDto dto);

    void cadastra(FormularioReceitaDto receitaDto);
}

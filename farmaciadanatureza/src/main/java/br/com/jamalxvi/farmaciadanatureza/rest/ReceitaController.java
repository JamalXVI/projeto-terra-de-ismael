package br.com.jamalxvi.farmaciadanatureza.rest;


import br.com.jamalxvi.farmaciadanatureza.models.dto.FormularioReceitaDto;
import br.com.jamalxvi.farmaciadanatureza.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @RequestMapping(method = POST, value = "/receita/")
    public void nova(@RequestBody FormularioReceitaDto dto){
        receitaService.cadastra(dto);
    }
}

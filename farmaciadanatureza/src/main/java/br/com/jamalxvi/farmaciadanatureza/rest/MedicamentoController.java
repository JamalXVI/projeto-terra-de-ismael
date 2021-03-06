package br.com.jamalxvi.farmaciadanatureza.rest;

import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api/medicines", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @RequestMapping(method = GET, value = "/")
    @PreAuthorize("hasRole('USUARIO')")
    public List<ElementoDeListaDto> list() {
        return medicamentoService.retornaListaDeTipoDeMedicamento();
    }



    @RequestMapping(method = GET, value = "/active")
    @PreAuthorize("hasRole('USUARIO')")
    public List<ElementoDeListaDto> detailsOfMedicine(String pesquisa, Integer limite) {
        return medicamentoService.retonarListaPrincipioAtivo(pesquisa, limite);
    }

}

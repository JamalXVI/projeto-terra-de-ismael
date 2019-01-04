package br.com.jamalxvi.farmaciadanatureza.rest;

import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;
import br.com.jamalxvi.farmaciadanatureza.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @RequestMapping(method = GET, value = "/")
    @PreAuthorize("hasRole('USUARIO')")
    public List<MedicoDto> list() {
        return medicoService.listarTodosDto();
    }
}

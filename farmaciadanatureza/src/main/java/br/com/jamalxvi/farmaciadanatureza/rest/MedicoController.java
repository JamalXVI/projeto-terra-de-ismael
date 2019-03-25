package br.com.jamalxvi.farmaciadanatureza.rest;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_PESQUISA_INVALIDA;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasRole('USUARIO')")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @RequestMapping(method = GET, value = "/")
    public List<MedicoDto> list() {
        return medicoService.listarTodosDto();
    }

    @RequestMapping(method = GET, value = "/listaPesquisa")
    public List<MedicoDto> pesquisar(String pesquisa, Integer limite) {
        if (pesquisa == null || limite == null){
            throw new MensagemExcecao(ERRO_PESQUISA_INVALIDA.getMensagem(),
                    EnumExcecaoDto.PARAMETROS_INVALIDOS);
        }
        if (limite <= 0){
            throw new MensagemExcecao(ERRO_PESQUISA_INVALIDA.getMensagem(),
                    EnumExcecaoDto.PARAMETROS_INVALIDOS);
        }
        return medicoService.pesquisar(pesquisa, limite);
    }
}

package br.com.jamalxvi.farmaciadanatureza.util;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.ElementoParaIrNaLista;
import org.junit.Test;

import static br.com.jamalxvi.farmaciadanatureza.util.MetodosGenericosUtils.transformarEnumEmDTO;
import static org.junit.Assert.assertTrue;

public class MetodosGenericosUtilsTest {

    @Test
    public void transformarEnumEmDTOSucesso() {
        ElementoDeListaDto elementoDeListaDto = transformarEnumEmDTO(EnumTipoMedicamento.CAPSULA);
        assertTrue("O código deve ser o mesmo da mensagem",
                elementoDeListaDto.getId().equals(EnumTipoMedicamento.CAPSULA.getId().toString()));
        assertTrue("A Descrição deve ser a mesma da mensagem",
                elementoDeListaDto.getNome().equals(EnumMesagens.CAPSULA.getMensagem()));
    }

    @Test
    public void transformarEnumEmDTONullNaoEncontrado() {
        ElementoDeListaDto elementoDeListaDto = transformarEnumEmDTO(null);
        assertTrue("O código deve ser o mesmo da mensagem",
                elementoDeListaDto.getId().equals("-1"));
        assertTrue("A Descrição deve ser a mesma da mensagem",
                elementoDeListaDto.getNome().equals(EnumMesagens.NAO_ENCONTRADA.getMensagem()));
    }

    @Test
    public void transformarEnumEmDTOVazioNaoEncontrado() {
        ElementoDeListaDto elementoDeListaDto = transformarEnumEmDTO(new ElementoParaIrNaLista() {
            @Override
            public Integer getId() {
                return 1;
            }

            @Override
            public String getDesc() {
                return "naoExisteDescricao";
            }
        });
        assertTrue("O código deve ser o mesmo da mensagem",
                elementoDeListaDto.getId().equals("1"));
        assertTrue("A Descrição deve ser a mesma da mensagem",
                elementoDeListaDto.getNome().equals(EnumMesagens.NAO_ENCONTRADA.getMensagem()));
    }
}
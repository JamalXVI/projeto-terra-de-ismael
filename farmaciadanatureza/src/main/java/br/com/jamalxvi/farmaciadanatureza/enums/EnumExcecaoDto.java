package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Define possíveis mensagens de exceções
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@AllArgsConstructor
@Getter
public enum EnumExcecaoDto {
    ATRIBUTO_EXISTE(1L, "Atributo Existe"),
    ATRIBUTOS_VAZIOS(2L, "Atributo Nulo"),
    NAO_ENCONTRADO(3L, "Não Encontrado"),
    ATRIBUTOS_VAZIOS_OU_NAO_ENCONTRADO(4L, "Atributo Nulo ou Não Encontrado"),
    SALVAR(5L, "Erro ao Salvar"),
    DESCONHECIDO(6L, "Erro Desconhecido"),;

    Long codigoErro;
    String mensagemErro;
}

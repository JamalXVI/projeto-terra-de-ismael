package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Lista todas mensagens do sistema
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@AllArgsConstructor
public enum EnumMesagens {
    ERRO_LISTAR_PESSOA_ATRIBUTO_NULO("Alguma pessoa está com atributo Nulo"),
    ERRO_LISTAR_PESSOA("Pessoa não encontrada."),
    ERRO_USUARIO_NAO_ENCONTRADO("Usuário não encontrado, ou usuário possuí erros!"),
    ERRO_INSERIR_PESSOA("Os dados inseridos para a pessoa são inválidos ou esta pessoa já " +
            "consta no nosso cadastro."),
    ERRO_SALVAR_PESSOA("Erro desconhecido ao salvar a pessoa."),
    ERRO_INSERIR_USUARIO("Os dados inseridos do usuário são inválidos ou o usuário já " +
            "existe.");
    @Getter
    private String mensagem;
}
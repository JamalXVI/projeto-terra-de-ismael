package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Lista todas mensagens do sistema
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@AllArgsConstructor
public enum EnumMesagens {
    ERRO_LISTAR_PESSOA_ATRIBUTO_NULO("", "Alguma pessoa está com atributo Nulo"),
    ERRO_LISTAR_PESSOA("pessoaNaoEncontrada", "Pessoa não encontrada."),
    ERRO_BUSCAR_TIPO_MEDICAMENTO("medicamentoNaoEncontrado", "O tipo de medicamento não foi " +
            "encontrado!"),
    ERRO_BUSCAR_AUTORIDADE("", "Autoridade não encontrada."),
    ERRO_IMPOSSIVEL_SALVAR_AUTORIDADE("", "Impossível Salvar Autoridade Específicada"),
    ERRO_USUARIO_NAO_ENCONTRADO("", "Usuário não encontrado, ou usuário possuí erros!"),
    ERRO_INSERIR_PESSOA("", "Os dados inseridos para a pessoa são inválidos ou esta pessoa já " +
            "consta no nosso cadastro."),
    ERRO_SALVAR_PESSOA("", "Erro desconhecido ao salvar a pessoa."),
    ERRO_INSERIR_USUARIO("", "Os dados inseridos do usuário são inválidos ou o usuário já " +
            "existe."),
    CAPSULA("capsula", "Capsula"),
    FLORAL("floral", "Floral"),
    HOMEOPATIA("homeopatia", "Homeopatia"),
    OUTROS_MEDICAMENTOS("outrosMedicamentos", "Outros Medicamentos"),
    PLANTA_DESIDRATADA("plantaDesidratada", "Plantas Desidratadas"),
    POMADA("pomada", "Pomada"),
    TINTURA("tintura", "Tintura"),
    NAO_ENCONTRADA("naoEncontrado", "Descrição não encontrada."),
    ERRO_CAMPO_MEDICAMENTO_NAO_ENCONTRADO("campoMedicamentoNaoEncontrado","O campo do " +
            "Medicamento não foi encontrado!"),
    ERRO_MEDICAMENTO_ACESSO_INVALIDO("erroMedicamentoArgumentoInvalido",
            "Acesso Inválido para o medicamento."),
    ERRO_METODO_NAO_EXISTE("erroMetodoNaoExiste", "Erro, método não existe: "),
    ERRO_ALVO_INVOCACAO_INVALIDO("erroAlvoInvocacaoInvalido", "Erro, alvo de invocação inválido: "),
    ERRO_AO_INSTANCIAR("erroAoInstanciar", "Erro, ao instanciar: "),
    ERRO_ACESSO_METODO_NAO_PERMITIDO("erroAcessoMetodoNaoPermitido", "Erro, acesso ao método não " +
            "permitido: "),
    ERRO_DESCONHECIDO("erroDesconhecido", "Erro Desconhecido. Por favor entre em contato a TI.");

    @Getter
    private String codigo;
    @Getter
    private String mensagem;

    public static String pegarMensagemPeloCodigo(String codigo){
        return Arrays.asList(values()).stream().filter(m -> m.getCodigo().equals(codigo))
                .findFirst().map(m -> m.getMensagem()).orElse(NAO_ENCONTRADA.getMensagem());
    }
}

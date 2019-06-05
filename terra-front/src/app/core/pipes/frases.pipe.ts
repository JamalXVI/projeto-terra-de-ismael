import { Pipe, PipeTransform } from '@angular/core';




@Pipe({
  name: 'frases'
})
export class FrasesPipe implements PipeTransform {
  
  transform(mensagem: string, args?: any): any {
    if(mensagem.match("[\\s,:-]+")){
      return mensagem;
    }
    const resultado = eval(`Frases.${mensagem}`);
    return !!resultado ? resultado : mensagem;
  }

}

enum Frases{
  ERRO_ENTRADA_INVALIDA="Você digitou algo errado aí talkei?",
  ERRO_VAZIO="Não sou advinha, digita alguma coisa que eu te ajudo!",
  ERRO_MEDICO_VAZIO="Nossa! Médico Invisível, esse não conheço não!",
  ERRO_INESPERADO="Minha pergunta é: Como aconteceu esse erro?",
  ERRO_PESSOA_NAO_ENCONTRADA="Oxi! Encontrei esse cabra aqui não!",
  ERRO_PROPORCAO="A proporção deve ser um número de 0 até 99",
  ERRO_USUARIO_VAZIO="Não tem como você entrar sem digitar um usuário!",
  ERRO_USUARIO_INVALIDO="Me ajude a te ajudar, é só digitar um usuário válido.",
  ERRO_SENHA_INVALIDA="Ninguém coloca uma senha de 3 dígitos, né?",
  ERRO_SENHA_VAZIA="Não vou nem comentar, sério? Sem senha?",
  ERRO_ACESSO_INVALIDO="Aha! Parece que alguém estava tentando acessar sem estar logado, né?",
  ERRO_PELO_MENOS_UM_PRINCIPIO="Você deve ter ao menos um princípio ativo!",
  ERRO_SELECIONAR_VALIDO="Selecione alguma coisa pelo menos né?",
  MENSAGEM_USUARIO="Digite seu usuário jovem!",
  MENSAGEM_SENHA="Agora só falta a senha, rs",
  LOGIN_SISTEMA="Reencarne Aqui",
  LOGIN_SUCESSO='Quem procura acha né homi?',
  LOGIN_FALHA='Oxi, encontrei foi não! Tem certeza que está certo ai?',
  NOME_APLICACAO="Farmácia Terra de Ismael",
  ADICIONAR_USUARIO="Adicionar Usuário",
  NOVA_RECEITA="Nova Receita",
  INFORMACOES_INICIAIS="Informações Iniciais",
  PESQUISA_PACIENTE="Pesquise por um Paciente",
  SAIR_MENSAGEM="Então é assim? Então vai! *cries*",
  SAIR_MENSAGEM_RETORNO='Desculpado, mas não quero me magoar novamente, ta?',
  REMOVER_ITEM_RECEITA_MENSAGEM='Sucesso ao remover medicamento da receita!',
  SAIR_BOTAO="Pedir Desculpas",
  SELECIONE_MEDICO="Selecione um Médico",
  SELECIONE_TIPO_MEDICAMENTO="Escolha o Tipo de Medicamento",
  DETALHES_MEDICAMENTO="Detalhes do medicamento",
  ADICIONAR_MEDICAMENTO="Adicionar Medicamento",
  PRINCIPIOS_ATIVOS="PRINCÍPIOS ATIVOS",
  PRINCIPIO_ATIVO="PRINCÍPIO ATIVO",
  PESQUISE_PRINCIPIO_ATIVO="Pesquise por um Princípio Ativo",
  MEDICAMENTOS_NA_RECEITA="Medicamentos na receita",
  POSOLOGIA="Posologia",
  SEM_POSOLOGIA="Não existe posologia para este medicamento",
  QUANTIDADE="Quantidade",
  TIPO="Tipo",
  ACAO="Ação"

}
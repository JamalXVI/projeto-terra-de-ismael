package br.com.jamalxvi.farmaciadanatureza.enums;

/**
 * Define todos os possíveis logs que precisam ser registrados, com suas respectivas mensagens
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public enum EnumLogAcoes {
  CADASTRAR_USUARIO(new Long(1), "CADASTRO_USUARIO", "Realizar Cadastro de Usuario");

  private final Long id;
  private final String nome;
  private final String descricao;
  EnumLogAcoes(Long id, String nome, String descricao){
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return String.valueOf(this.id);
  }
}

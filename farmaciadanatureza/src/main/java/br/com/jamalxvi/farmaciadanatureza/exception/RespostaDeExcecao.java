package br.com.jamalxvi.farmaciadanatureza.exception;

public class RespostaDeExcecao {

  private String codigoDeErro;
  private String mensagemDeErro;

  public RespostaDeExcecao() {}

  public String getCodigoDeErro() {
    return codigoDeErro;
  }

  public void setCodigoDeErro(String codigoDeErro) {
    this.codigoDeErro = codigoDeErro;
  }

  public String getMensagemDeErro() {
    return mensagemDeErro;
  }

  public void setMensagemDeErro(String mensagemDeErro) {
    this.mensagemDeErro = mensagemDeErro;
  }
}

package br.com.postechfiap.jlapppagamento.domain.enums;

public enum StatusPagamento {

  AGUARDANDO(1), //
  APROVADO(2), //
  NEGADO(3); //

  private int valorStatusPagamento;

  StatusPagamento(int statusPagamento) {
    this.valorStatusPagamento = statusPagamento;
  }

  public boolean estaAguardando() {
    return AGUARDANDO.getValorEstado() == this.valorStatusPagamento;
  }

  public boolean estaAprovado() {
    return APROVADO.getValorEstado() == this.valorStatusPagamento;
  }

  public boolean estaNegado() {
    return NEGADO.getValorEstado() == this.valorStatusPagamento;
  }

  public int getValorEstado() {
    return this.valorStatusPagamento;
  }

}

package br.com.postechfiap.jlapppagamento.domain.enums;

public enum Estado {

  RECEBIDO(1), //
  EM_PREPARACAO(2), //
  PRONTO(3), //
  FINALIZADO(4); //

  private int valorEstado;

  Estado(int estado) {
    this.valorEstado = estado;
  }

  public boolean foiRecebido() {
    return RECEBIDO.getValorEstado() == this.valorEstado;
  }

  public boolean estaEmPreparacao() {
    return EM_PREPARACAO.getValorEstado() == this.valorEstado;
  }

  public boolean estaPronto() {
    return PRONTO.getValorEstado() == this.valorEstado;
  }

  public boolean estaFinalizado() {
    return FINALIZADO.getValorEstado() == this.valorEstado;
  }

  public int getValorEstado() {
    return this.valorEstado;
  }

}

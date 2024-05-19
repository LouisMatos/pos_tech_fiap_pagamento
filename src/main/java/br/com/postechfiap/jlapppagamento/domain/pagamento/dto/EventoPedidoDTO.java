package br.com.postechfiap.jlapppagamento.domain.pagamento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import br.com.postechfiap.jlapppagamento.domain.enums.Estado;
import br.com.postechfiap.jlapppagamento.domain.enums.StatusPagamento;


public class EventoPedidoDTO {

  private String idMongoDB;

  private Long id;

  private String numeroPedido;

  private StatusPagamento statusPagamento;

  private Estado estado;

  private LocalDateTime dataPedido;

  private BigDecimal valorPedido;

  public EventoPedidoDTO() {}

  public String getIdMongoDB() {
    return idMongoDB;
  }

  public void setIdMongoDB(String idMongoDB) {
    this.idMongoDB = idMongoDB;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumeroPedido() {
    return numeroPedido;
  }

  public void setNumeroPedido(String numeroPedido) {
    this.numeroPedido = numeroPedido;
  }

  public StatusPagamento getStatusPagamento() {
    return statusPagamento;
  }

  public void setStatusPagamento(StatusPagamento statusPagamento) {
    this.statusPagamento = statusPagamento;
  }

  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  public LocalDateTime getDataPedido() {
    return dataPedido;
  }

  public void setDataPedido(LocalDateTime dataPedido) {
    this.dataPedido = dataPedido;
  }

  public BigDecimal getValorPedido() {
    return valorPedido;
  }

  public void setValorPedido(BigDecimal valorPedido) {
    this.valorPedido = valorPedido;
  }

  @Override
  public String toString() {
    return "EventoPedidoDTO [idMongoDB=" + idMongoDB + ", id=" + id + ", numeroPedido="
        + numeroPedido + ", statusPagamento=" + statusPagamento + ", estado=" + estado
        + ", dataPedido=" + dataPedido + ", valorPedido=" + valorPedido + "]";
  }

}

package br.com.postechfiap.jlapppagamento.domain.webhook.model;

public class Evento {

  private String id;

  private String idNotificacao;

  private String tipoEvento;

  private String dataEvento;

  private String idWebhook;

  // private Object payload;

  public String getId() {
    return id;
  }

  public Evento(String id, String idNotificacao, String tipoEvento, String dataEvento,
      String idWebhook) {
    this.id = id;
    this.idNotificacao = idNotificacao;
    this.tipoEvento = tipoEvento;
    this.dataEvento = dataEvento;
    this.idWebhook = idWebhook;
  }

  public Evento() {

  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdNotificacao() {
    return idNotificacao;
  }

  public void setIdNotificacao(String idNotificacao) {
    this.idNotificacao = idNotificacao;
  }

  public String getTipoEvento() {
    return tipoEvento;
  }

  public void setTipoEvento(String tipoEvento) {
    this.tipoEvento = tipoEvento;
  }

  public String getDataEvento() {
    return dataEvento;
  }

  public void setDataEvento(String dataEvento) {
    this.dataEvento = dataEvento;
  }

  public String getIdWebhook() {
    return idWebhook;
  }

  public void setIdWebhook(String idWebhook) {
    this.idWebhook = idWebhook;
  }

  @Override
  public String toString() {
    return "Evento [id=" + id + ", idNotificacao=" + idNotificacao + ", tipoEvento=" + tipoEvento
        + ", dataEvento=" + dataEvento + ", idWebhook=" + idWebhook + "]";
  }

}

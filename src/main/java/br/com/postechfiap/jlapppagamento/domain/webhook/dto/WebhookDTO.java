package br.com.postechfiap.jlapppagamento.domain.webhook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WebhookDTO {

  @JsonIgnore
  private String id;

  @JsonProperty("id_notificacao")
  private String idNotificacao;

  @JsonProperty("tipo_evento")
  private String tipoEvento;

  @JsonProperty("data_evento")
  private String dataEvento;

  @JsonProperty("id_webhook")
  private String idWebhook;

  @JsonProperty("data")
  private Object payload;

  public WebhookDTO() {}

  public String getId() {
    return id;
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

  public Object getPayload() {
    return payload;
  }

  public void setPayload(Object payload) {
    this.payload = payload;
  }

  @Override
  public String toString() {
    return "WebhookDTO [id=" + id + ", idNotificacao=" + idNotificacao + ", tipoEvento="
        + tipoEvento + ", dataEvento=" + dataEvento + ", idWebhook=" + idWebhook + ", payload="
        + payload + "]";
  }



}

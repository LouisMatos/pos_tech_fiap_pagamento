package br.com.postechfiap.jlapppagamento.domain.webhook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

}

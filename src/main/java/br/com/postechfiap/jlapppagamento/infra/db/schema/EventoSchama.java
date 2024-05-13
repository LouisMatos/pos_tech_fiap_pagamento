package br.com.postechfiap.jlapppagamento.infra.db.schema;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "eventos")
public class EventoSchama implements Serializable {

  private static final long serialVersionUID = -8920249848735316255L;

  @Id
  private String id;

  private String idNotificacao;

  private String tipoEvento;

  private String dataEvento;

  private String idWebhook;

}

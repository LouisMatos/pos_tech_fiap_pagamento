package br.com.postechfiap.jlapppagamento.infra.db.schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import br.com.postechfiap.jlapppagamento.domain.enums.Estado;
import br.com.postechfiap.jlapppagamento.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pedidos")
public class PedidoSchema implements Serializable {

  private static final long serialVersionUID = 106181416585362479L;

  @Id
  private String id;

  private Long peidoId;

  private String numeroPedido;

  private StatusPagamento statusPagamento;

  private Estado estado;

  private LocalDateTime dataPedido;

  private BigDecimal valorPedido;

}

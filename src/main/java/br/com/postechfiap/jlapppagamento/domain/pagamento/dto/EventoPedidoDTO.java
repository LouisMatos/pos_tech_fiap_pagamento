package br.com.postechfiap.jlapppagamento.domain.pagamento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import br.com.postechfiap.jlapppagamento.domain.enums.Estado;
import br.com.postechfiap.jlapppagamento.domain.enums.StatusPagamento;
import lombok.Data;

@Data
public class EventoPedidoDTO {

  private String idMongoDB;

  private Long id;

  private String numeroPedido;

  private StatusPagamento statusPagamento;

  private Estado estado;

  private LocalDateTime dataPedido;

  private BigDecimal valorPedido;

}

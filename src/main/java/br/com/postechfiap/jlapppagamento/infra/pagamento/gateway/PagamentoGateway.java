package br.com.postechfiap.jlapppagamento.infra.pagamento.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.PedidoPagamentoDTO;
import br.com.postechfiap.jlapppagamento.domain.pagamento.gateway.IPagamentoGateway;
import br.com.postechfiap.jlapppagamento.domain.pagamento.mapper.PedidoMapper;
import br.com.postechfiap.jlapppagamento.infra.db.repository.PedidoRepository;
import br.com.postechfiap.jlapppagamento.infra.db.schema.PedidoSchema;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@Component
public class PagamentoGateway implements IPagamentoGateway {

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private Logger log;


  @Override
  public PedidoPagamentoDTO inserir(PedidoPagamentoDTO pedidoPagamentoDTO) {
    PedidoSchema pedidoSchema =
        pedidoRepository.save(PedidoMapper.toPedidoSchema(pedidoPagamentoDTO));

    log.info("Cadastrando novo pedido para o pagamento na base de dados!");
    return PedidoMapper.toPedidoPagamentoDTO(pedidoSchema);
  }

}

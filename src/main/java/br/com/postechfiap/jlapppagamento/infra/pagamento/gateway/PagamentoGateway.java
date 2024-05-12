package br.com.postechfiap.jlapppagamento.infra.pagamento.gateway;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import br.com.postechfiap.jlapppagamento.domain.pagamento.gateway.IPagamentoGateway;
import br.com.postechfiap.jlapppagamento.domain.pagamento.mapper.PedidoMapper;
import br.com.postechfiap.jlapppagamento.infra.db.repository.PedidoRepository;
import br.com.postechfiap.jlapppagamento.infra.db.schema.PedidoSchema;
import br.com.postechfiap.jlapppagamento.shared.exception.NotFoundException;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@Component
public class PagamentoGateway implements IPagamentoGateway {

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private Logger log;


  @Override
  public EventoPedidoDTO inserir(EventoPedidoDTO eventoPedidoDTO) {
    PedidoSchema pedidoSchema =
        pedidoRepository.insert(PedidoMapper.toPedidoSchema(eventoPedidoDTO));

    log.info("Cadastrando novo pedido para o pagamento na base de dados!");
    return PedidoMapper.toPedidoPagamentoDTO(pedidoSchema);
  }


  @Override
  public EventoPedidoDTO buscaPedidoNumeroPedido(String numeroPedido) {
    Optional<PedidoSchema> pedidoSchema = pedidoRepository.findByNumeroPedido(numeroPedido);

    EventoPedidoDTO eventoPedidoDTO = PedidoMapper.toPedidoPagamentoDTO(pedidoSchema.orElseThrow(
        () -> new NotFoundException("Número de pedido: " + numeroPedido + " não encontrado!")));

    return eventoPedidoDTO;
  }


  @Override
  public EventoPedidoDTO atualizar(EventoPedidoDTO eventoPedidoDTO) {
    PedidoSchema pedidoSchema = pedidoRepository.save(PedidoMapper.toPedidoSchema(eventoPedidoDTO));

    log.info("Atualizando pedido na base de dados!");
    return PedidoMapper.toPedidoPagamentoDTO(pedidoSchema);
  }



}

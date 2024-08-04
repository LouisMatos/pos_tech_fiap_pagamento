package br.com.postechfiap.jlapppagamento.infra.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;
import br.com.postechfiap.jlapppagamento.usecase.pagamento.PagamentoUseCase;

@Component
public class PedidosSubscriber {

  private final PagamentoUseCase pagamentoUseCase;

  private final Logger log;

  public PedidosSubscriber(PagamentoUseCase pagamentoUseCase, Logger log) {
    this.pagamentoUseCase = pagamentoUseCase;
    this.log = log;
  }

  @RabbitListener(queues = "${mq.queues.pedidos}")
  public void receive(String message) {
    boolean isProcessado = false;

    log.info("Recebendo evento de pedido: {}", message);

    isProcessado = pagamentoUseCase.processarPedido(message);

    if (isProcessado) {
      log.info("Evendo de pedido foi processado com sucesso: {}", message);
    } else {
      log.error("Erro ao processar pedido: {}", message);
    }

  }

}

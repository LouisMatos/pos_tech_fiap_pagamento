
package br.com.postechfiap.jlapppagamento.infra.mq;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;

class PedidoPublisherTest {

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  private Queue statusPedidoQueue;

  private PedidoPublisher pedidoPublisher;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    pedidoPublisher = new PedidoPublisher(rabbitTemplate, statusPedidoQueue);
  }


  @Test
  void deveriaEnviarMenssagemParaFilaComSucesso() throws JsonProcessingException {
    // Crie um EventoPedidoDTO de exemplo
    EventoPedidoDTO eventoPedidoDTO = new EventoPedidoDTO();
    // ... configure o EventoPedidoDTO conforme necessário

    // Chame o método a ser testado
    pedidoPublisher.send(eventoPedidoDTO);

    // Verifique se o método foi chamado com o EventoPedidoDTO correto
    verify(rabbitTemplate, times(1)).convertAndSend(statusPedidoQueue.getName(),
        pedidoPublisher.convertIntoJson(eventoPedidoDTO));
  }


}

package br.com.postechfiap.jlapppagamento.infra.mq;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;

@SpringBootTest
@TestExecutionListeners(MockitoTestExecutionListener.class)
public class PedidoPublisherTest {

  @Mock
  private RabbitTemplate rabbitTemplate;

  @Mock
  private Queue statusPedidoQueue;

  @InjectMocks
  private PedidoPublisher pedidoPublisher;

  @Test
  public void shouldSendEventToQueue() throws JsonProcessingException {
    EventoPedidoDTO eventoPedidoDTO = new EventoPedidoDTO();
    when(statusPedidoQueue.getName()).thenReturn("testQueue");

    pedidoPublisher.send(eventoPedidoDTO);

    verify(rabbitTemplate).convertAndSend(any(String.class), any(String.class));
  }

  @Test
  public void shouldConvertEventToJSON() throws JsonProcessingException {
    EventoPedidoDTO eventoPedidoDTO = new EventoPedidoDTO();

    String json = pedidoPublisher.convertIntoJson(eventoPedidoDTO);

    assertNotNull(json);
    assertTrue(json.contains("\"id\":null")); // assuming id is a field in EventoPedidoDTO
  }
}


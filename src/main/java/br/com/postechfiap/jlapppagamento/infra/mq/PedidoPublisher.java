package br.com.postechfiap.jlapppagamento.infra.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PedidoPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final Queue statusPedidoQueue;


  public void send(EventoPedidoDTO eventoPedidoDTO) throws JsonProcessingException {
    String json = convertIntoJson(eventoPedidoDTO);
    rabbitTemplate.convertAndSend(statusPedidoQueue.getName(), json);
  }

  private String convertIntoJson(EventoPedidoDTO eventoPedidoDTO) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return mapper.writeValueAsString(eventoPedidoDTO);
  }


}

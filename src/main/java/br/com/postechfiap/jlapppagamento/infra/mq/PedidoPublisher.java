package br.com.postechfiap.jlapppagamento.infra.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PedidoPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final Queue pedidoQueue;


  // public void send(Pedido pedido) throws JsonProcessingException {
  // String json = convertIntoJson(pedido);
  // rabbitTemplate.convertAndSend(pedidoQueue.getName(), json);
  // }
  //
  // private String convertIntoJson(Pedido pedido) throws JsonProcessingException {
  // ObjectMapper mapper = new ObjectMapper();
  // mapper.registerModule(new JavaTimeModule());
  // return mapper.writeValueAsString(pedido);
  // }


}

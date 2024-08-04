package br.com.postechfiap.jlapppagamento.infra.mq;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;
import br.com.postechfiap.jlapppagamento.usecase.pagamento.PagamentoUseCase;

@SpringBootTest
@TestExecutionListeners(MockitoTestExecutionListener.class)
class PedidosSubscriberTest {

  @Mock
  private PagamentoUseCase pagamentoUseCase;

  @Mock
  private Logger log;

  @InjectMocks
  private PedidosSubscriber pedidosSubscriber;

  @Test
  void shouldLogInfoWhenMessageReceived() {
    String message = "testMessage";
    when(pagamentoUseCase.processarPedido(message)).thenReturn(true);

    pedidosSubscriber.receive(message);

    verify(log, times(2)).info(any(String.class), any(String.class));
  }

  @Test
  void shouldProcessMessageSuccessfully() {
    String message = "testMessage";
    when(pagamentoUseCase.processarPedido(message)).thenReturn(true);

    pedidosSubscriber.receive(message);

    verify(log).info("Evendo de pedido foi processado com sucesso: {}", message);
  }

  @Test
  void shouldLogErrorWhenMessageProcessingFails() {
    String message = "testMessage";
    when(pagamentoUseCase.processarPedido(message)).thenReturn(false);

    pedidosSubscriber.receive(message);

    verify(log).error("Erro ao processar pedido: {}", message);
  }
}

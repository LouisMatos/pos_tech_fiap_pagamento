package br.com.postechfiap.jlapppagamento.usecase.pagamento;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import br.com.postechfiap.jlapppagamento.domain.enums.Estado;
import br.com.postechfiap.jlapppagamento.domain.enums.StatusPagamento;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import br.com.postechfiap.jlapppagamento.domain.pagamento.gateway.IPagamentoGateway;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@SpringBootTest
@TestExecutionListeners(MockitoTestExecutionListener.class)
public class PagamentoUseCaseTest {

  @Mock
  private IPagamentoGateway pagamentoGateway;

  @Mock
  private Logger log;

  @InjectMocks
  private PagamentoUseCase pagamentoUseCase;

  @Test
  public void shouldLogInfoWhenPaymentOrderProcessedSuccessfully() {
    String message = "testMessage";
    when(pagamentoGateway.inserir(any())).thenReturn(createFakeEventoPedidoDTO());

    pagamentoUseCase.processarPedido(message);

    verify(log, times(0)).info(any(String.class), any(EventoPedidoDTO.class));
  }

  @Test
  public void shouldReturnTrueWhenPaymentOrderProcessedSuccessfully() {
    String message = "{\r\n" + "    \"id\": 11,\r\n" + "    \"numeroPedido\": \"IXUHGD\",\r\n"
        + "    \"statusPagamento\": \"AGUARDANDO\",\r\n" + "    \"estado\": \"RECEBIDO\",\r\n"
        + "    \"dataPedido\": [\r\n" + "        2024,\r\n" + "        5,\r\n" + "        18,\r\n"
        + "        21,\r\n" + "        50,\r\n" + "        28,\r\n" + "        69400700\r\n"
        + "    ],\r\n" + "    \"valorPedido\": 169.17\r\n" + "}";
    when(pagamentoGateway.inserir(any())).thenReturn(createFakeEventoPedidoDTO());

    boolean result = pagamentoUseCase.processarPedido(message);

    assertTrue(result);
  }

  @Test
  public void shouldHandleJsonProcessingException() {
    String message = "invalidJsonMessage";

    boolean result = pagamentoUseCase.processarPedido(message);

    assertFalse(result);
  }

  public EventoPedidoDTO createFakeEventoPedidoDTO() {
    EventoPedidoDTO eventoPedidoDTO = new EventoPedidoDTO();
    eventoPedidoDTO.setIdMongoDB("f46dd524-d9d4-4ae6-ad94-ef6e1e93e2e1");
    eventoPedidoDTO.setId(1L);
    eventoPedidoDTO.setNumeroPedido("IXUHGD");
    eventoPedidoDTO.setStatusPagamento(StatusPagamento.APROVADO);
    eventoPedidoDTO.setEstado(Estado.RECEBIDO);
    eventoPedidoDTO.setDataPedido(LocalDateTime.parse("2024-05-18T21:50:28.0694007"));
    eventoPedidoDTO.setValorPedido(new BigDecimal(169.17));

    return eventoPedidoDTO;
  }
}

package br.com.postechfiap.jlapppagamento.usecase.webhook;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.postechfiap.jlapppagamento.domain.enums.Estado;
import br.com.postechfiap.jlapppagamento.domain.enums.StatusPagamento;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import br.com.postechfiap.jlapppagamento.domain.pagamento.gateway.IPagamentoGateway;
import br.com.postechfiap.jlapppagamento.domain.webhook.dto.WebhookDTO;
import br.com.postechfiap.jlapppagamento.domain.webhook.gateway.IWebhookGateway;
import br.com.postechfiap.jlapppagamento.domain.webhook.model.AtualizacaoStatusPagamento;
import br.com.postechfiap.jlapppagamento.infra.mq.PedidoPublisher;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@SpringBootTest
@TestExecutionListeners(MockitoTestExecutionListener.class)
public class WebhookUseCaseTest {

  @Mock
  private IWebhookGateway webhookGateway;

  @Mock
  private IPagamentoGateway pagamentoGateway;

  @Mock
  private PedidoPublisher pedidoPublisher;

  @Mock
  private Logger log;

  @InjectMocks
  private WebhookUseCase webhookUseCase;

  @Test
  public void shouldLogInfoWhenWebhookEventReceived()
      throws JsonMappingException, JsonProcessingException {
    WebhookDTO webhookDTO = createFakeWebhookDTO();

    when(webhookGateway.inserir(any())).thenReturn(createFakeAtualizacaoStatusPagamento());
    when(pagamentoGateway.buscaPedidoNumeroPedido(any())).thenReturn(createFakeEventoPedidoDTO());
    when(pagamentoGateway.atualizar(any())).thenReturn(createFakeEventoPedidoDTO());


    webhookUseCase.recuperandoEventoWebhook(webhookDTO);

    verify(log, times(5)).info(any(String.class), any(String.class));
  }

  @Test
  public void shouldUpdatePaymentStatusSuccessfully()
      throws JsonMappingException, JsonProcessingException {
    WebhookDTO webhookDTO = createFakeWebhookDTO();
    when(webhookGateway.inserir(any())).thenReturn(createFakeAtualizacaoStatusPagamento());
    when(pagamentoGateway.buscaPedidoNumeroPedido(any())).thenReturn(createFakeEventoPedidoDTO());
    when(pagamentoGateway.atualizar(any())).thenReturn(createFakeEventoPedidoDTO());

    webhookUseCase.recuperandoEventoWebhook(webhookDTO);

    verify(pagamentoGateway).atualizar(any());
  }

  @Test
  public void shouldSendUpdatedOrderToQueue() throws JsonProcessingException {
    WebhookDTO webhookDTO = createFakeWebhookDTO();
    when(webhookGateway.inserir(any())).thenReturn(createFakeAtualizacaoStatusPagamento());
    when(pagamentoGateway.buscaPedidoNumeroPedido(any())).thenReturn(createFakeEventoPedidoDTO());
    when(pagamentoGateway.atualizar(any())).thenReturn(createFakeEventoPedidoDTO());

    webhookUseCase.recuperandoEventoWebhook(webhookDTO);

    verify(pedidoPublisher).send(any());
  }

  public WebhookDTO createFakeWebhookDTO() throws JsonMappingException, JsonProcessingException {
    WebhookDTO webhookDTO = new WebhookDTO();
    webhookDTO.setId("f46dd524-d9d4-4ae6-ad94-ef6e1e93e2e1");
    webhookDTO.setIdNotificacao("10000P0001");
    webhookDTO.setTipoEvento("notificacao_atualizacao_status_pagamento");
    webhookDTO.setDataEvento("14/01/2024 13:23:41");
    webhookDTO.setIdWebhook("10000W0001");

    ObjectMapper mapper = new ObjectMapper();
    String json =
        "{\"numero_pedido\": \"IXUHGD\", \"pagamento\": \"APROVADO\", \"valor_pago\": 169.17, \"meio_pagamento\": \"credito\"}";

    webhookDTO.setPayload(mapper.readValue(json, Object.class));

    return webhookDTO;
  }

  public AtualizacaoStatusPagamento createFakeAtualizacaoStatusPagamento() {
    AtualizacaoStatusPagamento atualizacaoStatusPagamento = new AtualizacaoStatusPagamento();
    atualizacaoStatusPagamento.setNumeroPedido("IXUHGD");
    atualizacaoStatusPagamento.setPagamento("APROVADO");
    atualizacaoStatusPagamento.setValorPago(169.17);
    atualizacaoStatusPagamento.setMeioPagamento("credito");
    return atualizacaoStatusPagamento;
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

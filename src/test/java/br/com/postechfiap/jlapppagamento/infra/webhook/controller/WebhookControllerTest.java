package br.com.postechfiap.jlapppagamento.infra.webhook.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.postechfiap.jlapppagamento.domain.webhook.dto.WebhookDTO;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;
import br.com.postechfiap.jlapppagamento.usecase.webhook.WebhookUseCase;


public class WebhookControllerTest {

  @InjectMocks
  private WebhookController webhookController;

  @Mock
  private WebhookUseCase webhookUseCase;

  @Mock
  private Logger log;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldReturnAcceptedWhenWebhookInsertedSuccessfully()
      throws JsonMappingException, JsonProcessingException {
    WebhookDTO webhookDTO = createFakeWebhookDTO();
    doNothing().when(webhookUseCase).recuperandoEventoWebhook(any());

    ResponseEntity<Void> response = webhookController.inserir(webhookDTO);

    verify(log).info(any(String.class));
    assertEquals(ResponseEntity.accepted().build(), response);
  }

  @Test
  public void shouldLogInfoWhenWebhookInsertedSuccessfully() {
    WebhookDTO webhookDTO = new WebhookDTO();
    doNothing().when(webhookUseCase).recuperandoEventoWebhook(any());

    webhookController.inserir(webhookDTO);

    verify(log).info(any(String.class));
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
}

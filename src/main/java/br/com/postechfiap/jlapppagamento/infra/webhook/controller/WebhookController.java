package br.com.postechfiap.jlapppagamento.infra.webhook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.postechfiap.jlapppagamento.domain.webhook.dto.WebhookDTO;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;
import br.com.postechfiap.jlapppagamento.usecase.webhook.WebhookUseCase;

@RestController
@RequestMapping(path = "/api/v1/webhook")
public class WebhookController {

  private final WebhookUseCase webhookUseCase;

  private final Logger log;

  public WebhookController(WebhookUseCase webhookUseCase, Logger log) {
    this.webhookUseCase = webhookUseCase;
    this.log = log;
  }

  @PostMapping
  public ResponseEntity<Void> inserir(@RequestBody WebhookDTO webhookDTO) {
    log.info("Iniciando a coleta do webhook.");
    webhookUseCase.recuperandoEventoWebhook(webhookDTO);
    return ResponseEntity.accepted().build();
  }

}

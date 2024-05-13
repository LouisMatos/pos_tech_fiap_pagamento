package br.com.postechfiap.jlapppagamento.usecase.webhook;


import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import br.com.postechfiap.jlapppagamento.domain.enums.StatusPagamento;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import br.com.postechfiap.jlapppagamento.domain.pagamento.gateway.IPagamentoGateway;
import br.com.postechfiap.jlapppagamento.domain.webhook.dto.WebhookDTO;
import br.com.postechfiap.jlapppagamento.domain.webhook.gateway.IWebhookGateway;
import br.com.postechfiap.jlapppagamento.domain.webhook.model.AtualizacaoStatusPagamento;
import br.com.postechfiap.jlapppagamento.infra.mq.PedidoPublisher;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@Service
public class WebhookUseCase {

  private final IWebhookGateway webhookGateway;

  private final IPagamentoGateway pagamentoGateway;

  private final PedidoPublisher pedidoPublisher;

  private final Logger log;

  private static final String EVENTO_NOTIFICACAO_ATUALIZACAO_PAGAMENTO =
      "notificacao_atualizacao_status_pagamento";

  public WebhookUseCase(IWebhookGateway webhookGateway, Logger log,
      IPagamentoGateway pagamentoGateway, PedidoPublisher pedidoPublisher) {
    this.webhookGateway = webhookGateway;
    this.pagamentoGateway = pagamentoGateway;
    this.pedidoPublisher = pedidoPublisher;
    this.log = log;
  }

  public void recuperandoEventoWebhook(WebhookDTO webhookDTO) {

    log.info("Evento webhook recebido! Evento: {}", webhookDTO.toString());

    String tipoEvento = webhookDTO.getTipoEvento();

    log.info("Tipo de evento recebido: {}", tipoEvento);

    if (tipoEvento.equals(EVENTO_NOTIFICACAO_ATUALIZACAO_PAGAMENTO)) {
      AtualizacaoStatusPagamento atualizacaoStatusPagamento =
          recuperaAtualizacaoStatusPagamento(webhookDTO);
      AtualizacaoStatusPagamento statusPagamento =
          webhookGateway.inserir(atualizacaoStatusPagamento);
      log.info("Evento: {} salvo com sucesso!", statusPagamento.toString());

      log.info("Iniciando atualização de status de pagamento do pedido!");

      EventoPedidoDTO eventoPedidoDTO =
          pagamentoGateway.buscaPedidoNumeroPedido(statusPagamento.getNumeroPedido());

      log.info("Atualizando status de pagamento do pedido {} de {} > para {}",
          statusPagamento.getNumeroPedido(), eventoPedidoDTO.getStatusPagamento(),
          statusPagamento.getPagamento());
      eventoPedidoDTO.setStatusPagamento(StatusPagamento.valueOf(statusPagamento.getPagamento()));

      eventoPedidoDTO = pagamentoGateway.atualizar(eventoPedidoDTO);

      log.info("Status de pagamento do pedido atualizado com sucesso!");

      try {
        pedidoPublisher.send(eventoPedidoDTO);
        log.info("Pedido enviado para fila status-pedidos-mq: {}", eventoPedidoDTO.toString());
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }

    }

  }

  private AtualizacaoStatusPagamento recuperaAtualizacaoStatusPagamento(WebhookDTO webhookDTO) {

    if (webhookDTO.getPayload() != null) {
      Gson gson = new Gson();

      JsonElement jsonElement = gson.toJsonTree(webhookDTO.getPayload());
      JsonObject payload = (JsonObject) jsonElement;

      AtualizacaoStatusPagamento atualizacaoStatusPagamento =
          new AtualizacaoStatusPagamento(webhookDTO.getId(), webhookDTO.getIdNotificacao(),
              webhookDTO.getTipoEvento(), webhookDTO.getDataEvento(), webhookDTO.getIdWebhook(),
              payload.get("numero_pedido").getAsString(), payload.get("pagamento").getAsString(),
              payload.get("valor_pago").getAsDouble(), payload.get("meio_pagamento").getAsString());

      log.info("Evento Atualização de pagamento: {}", atualizacaoStatusPagamento.toString());

      return atualizacaoStatusPagamento;
    }

    return null;
  }

}

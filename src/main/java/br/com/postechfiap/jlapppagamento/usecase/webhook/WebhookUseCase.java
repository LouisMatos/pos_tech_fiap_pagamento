package br.com.postechfiap.jlapppagamento.usecase.webhook;


import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import br.com.postechfiap.jlapppagamento.domain.webhook.dto.WebhookDTO;
import br.com.postechfiap.jlapppagamento.domain.webhook.gateway.IWebhookGateway;
import br.com.postechfiap.jlapppagamento.domain.webhook.model.AtualizacaoStatusPagamento;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@Service
public class WebhookUseCase {

  private final IWebhookGateway iWebhookGateway;

  private final Logger log;

  private static final String EVENTO_NOTIFICACAO_ATUALIZACAO_PAGAMENTO =
      "notificacao_atualizacao_status_pagamento";

  public WebhookUseCase(IWebhookGateway iWebhookGateway, Logger log) {
    this.iWebhookGateway = iWebhookGateway;
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
          iWebhookGateway.inserir(atualizacaoStatusPagamento);
      log.info("Evento: {} salvo com sucesso!", statusPagamento.toString());

      log.info("Iniciando atualização de status de pagamento do pedido!");

      // PedidoDTO pedidoDTO =
      // pedidoInputPort.buscaPedidoNumeroPedido(statusPagamento.getNumeroPedido());
      //
      // log.info("Atualizando status de pagamento do pedido {} de {} > para {}",
      // statusPagamento.getNumeroPedido(), pedidoDTO.getStatusPagamento(),
      // statusPagamento.getPagamento());
      // pedidoDTO.setStatusPagamento(StatusPagamento.valueOf(statusPagamento.getPagamento()));
      //
      // pedidoInputPort.atualizar(pedidoDTO, statusPagamento.getNumeroPedido());

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

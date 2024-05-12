package br.com.postechfiap.jlapppagamento.domain.webhook.gateway;

import br.com.postechfiap.jlapppagamento.domain.webhook.model.AtualizacaoStatusPagamento;

public interface IWebhookGateway {

  public AtualizacaoStatusPagamento inserir(AtualizacaoStatusPagamento atualizacaoStatusPagamento);

}

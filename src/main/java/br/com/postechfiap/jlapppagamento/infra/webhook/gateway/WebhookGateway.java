package br.com.postechfiap.jlapppagamento.infra.webhook.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.postechfiap.jlapppagamento.domain.webhook.gateway.IWebhookGateway;
import br.com.postechfiap.jlapppagamento.domain.webhook.mapper.AtualizacaoStatusPagamentoMapper;
import br.com.postechfiap.jlapppagamento.domain.webhook.model.AtualizacaoStatusPagamento;
import br.com.postechfiap.jlapppagamento.infra.db.repository.WebhookRepository;
import br.com.postechfiap.jlapppagamento.infra.db.schema.AtualizacaoStatusPagamentoSchema;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@Component
public class WebhookGateway implements IWebhookGateway {

  @Autowired
  private WebhookRepository webhookRepository;

  @Autowired
  private Logger log;

  @Override
  public AtualizacaoStatusPagamento inserir(AtualizacaoStatusPagamento atualizacaoStatusPagamento) {
    AtualizacaoStatusPagamentoSchema atualizacaoStatusPagamentoSchema =
        AtualizacaoStatusPagamentoMapper
            .toAtualizacaoStatusPagamentoSchema(atualizacaoStatusPagamento);
    log.info("Salvando novo evento atualizacao Status Pagamento no banco de dados!");
    return AtualizacaoStatusPagamentoMapper
        .toDomain(webhookRepository.save(atualizacaoStatusPagamentoSchema));
  }

}

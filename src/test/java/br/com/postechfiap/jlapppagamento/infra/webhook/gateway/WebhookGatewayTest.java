package br.com.postechfiap.jlapppagamento.infra.webhook.gateway;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import br.com.postechfiap.jlapppagamento.domain.webhook.model.AtualizacaoStatusPagamento;
import br.com.postechfiap.jlapppagamento.infra.db.repository.WebhookRepository;
import br.com.postechfiap.jlapppagamento.infra.db.schema.AtualizacaoStatusPagamentoSchema;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

public class WebhookGatewayTest {

  @InjectMocks
  private WebhookGateway webhookGateway;

  @Mock
  private WebhookRepository webhookRepository;

  @Mock
  private Logger log;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void shouldReturnStatusUpdateWhenInsertedSuccessfully() {
    AtualizacaoStatusPagamento atualizacaoStatusPagamento = new AtualizacaoStatusPagamento();
    when(webhookRepository.save(any())).thenReturn(new AtualizacaoStatusPagamentoSchema());

    AtualizacaoStatusPagamento response = webhookGateway.inserir(atualizacaoStatusPagamento);

    verify(log).info(any(String.class));
    assertNotNull(response);
  }

  @Test
  public void shouldLogInfoWhenStatusUpdateInsertedSuccessfully() {
    AtualizacaoStatusPagamento atualizacaoStatusPagamento = new AtualizacaoStatusPagamento();
    when(webhookRepository.save(any())).thenReturn(new AtualizacaoStatusPagamentoSchema());

    webhookGateway.inserir(atualizacaoStatusPagamento);

    verify(log).info(any(String.class));
  }
}

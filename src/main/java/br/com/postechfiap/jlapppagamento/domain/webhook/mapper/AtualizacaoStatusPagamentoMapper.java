package br.com.postechfiap.jlapppagamento.domain.webhook.mapper;

import br.com.postechfiap.jlapppagamento.domain.webhook.model.AtualizacaoStatusPagamento;
import br.com.postechfiap.jlapppagamento.infra.db.schema.AtualizacaoStatusPagamentoSchema;

public class AtualizacaoStatusPagamentoMapper {

  public static AtualizacaoStatusPagamentoSchema toAtualizacaoStatusPagamentoSchema(
      AtualizacaoStatusPagamento atualizacaoStatusPagamento) {

    AtualizacaoStatusPagamentoSchema atualizacaoStatusPagamentoSchema =
        new AtualizacaoStatusPagamentoSchema();

    atualizacaoStatusPagamentoSchema.setId(atualizacaoStatusPagamento.getId());
    atualizacaoStatusPagamentoSchema
        .setIdNotificacao(atualizacaoStatusPagamento.getIdNotificacao());
    atualizacaoStatusPagamentoSchema.setTipoEvento(atualizacaoStatusPagamento.getTipoEvento());
    atualizacaoStatusPagamentoSchema.setDataEvento(atualizacaoStatusPagamento.getDataEvento());
    atualizacaoStatusPagamentoSchema.setIdWebhook(atualizacaoStatusPagamento.getIdWebhook());
    atualizacaoStatusPagamentoSchema.setNumeroPedido(atualizacaoStatusPagamento.getNumeroPedido());
    atualizacaoStatusPagamentoSchema.setPagamento(atualizacaoStatusPagamento.getPagamento());
    atualizacaoStatusPagamentoSchema.setValorPago(atualizacaoStatusPagamento.getValorPago());
    atualizacaoStatusPagamentoSchema
        .setMeioPagamento(atualizacaoStatusPagamento.getMeioPagamento());

    return atualizacaoStatusPagamentoSchema;
  }


  public static AtualizacaoStatusPagamento toDomain(AtualizacaoStatusPagamentoSchema schema) {
    AtualizacaoStatusPagamento atualizacaoStatusPagamento = new AtualizacaoStatusPagamento();

    atualizacaoStatusPagamento.setId(schema.getId());
    atualizacaoStatusPagamento.setIdNotificacao(schema.getIdNotificacao());
    atualizacaoStatusPagamento.setTipoEvento(schema.getTipoEvento());
    atualizacaoStatusPagamento.setDataEvento(schema.getDataEvento());
    atualizacaoStatusPagamento.setIdWebhook(schema.getIdWebhook());
    atualizacaoStatusPagamento.setNumeroPedido(schema.getNumeroPedido());
    atualizacaoStatusPagamento.setPagamento(schema.getPagamento());
    atualizacaoStatusPagamento.setValorPago(schema.getValorPago());
    atualizacaoStatusPagamento.setMeioPagamento(schema.getMeioPagamento());

    return atualizacaoStatusPagamento;
  }


}

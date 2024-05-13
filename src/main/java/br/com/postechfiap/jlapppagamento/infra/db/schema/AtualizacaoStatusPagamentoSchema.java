package br.com.postechfiap.jlapppagamento.infra.db.schema;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "atualizacao_status_pagamento")
public class AtualizacaoStatusPagamentoSchema extends EventoSchama {

  private static final long serialVersionUID = -4449804229929062359L;

  private String numeroPedido;

  private String pagamento;

  private Double valorPago;

  private String meioPagamento;

}

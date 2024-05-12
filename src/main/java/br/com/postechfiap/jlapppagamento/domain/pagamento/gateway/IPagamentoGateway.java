package br.com.postechfiap.jlapppagamento.domain.pagamento.gateway;

import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.PedidoPagamentoDTO;

public interface IPagamentoGateway {

  public PedidoPagamentoDTO inserir(PedidoPagamentoDTO pedidoPagamentoDTO);

}

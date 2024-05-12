package br.com.postechfiap.jlapppagamento.domain.pagamento.gateway;

import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;

public interface IPagamentoGateway {

  public EventoPedidoDTO inserir(EventoPedidoDTO eventoPedidoDTO);

  public EventoPedidoDTO buscaPedidoNumeroPedido(String numeroPedido);

  public EventoPedidoDTO atualizar(EventoPedidoDTO eventoPedidoDTO);

}

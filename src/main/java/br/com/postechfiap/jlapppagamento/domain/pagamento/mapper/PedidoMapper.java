package br.com.postechfiap.jlapppagamento.domain.pagamento.mapper;

import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.PedidoPagamentoDTO;
import br.com.postechfiap.jlapppagamento.infra.db.schema.PedidoSchema;

public class PedidoMapper {

  public static PedidoSchema toPedidoSchema(PedidoPagamentoDTO pedidoPagamentoDTO) {

    PedidoSchema pedidoSchema = new PedidoSchema();
    pedidoSchema.setPeidoId(pedidoPagamentoDTO.getId());
    pedidoSchema.setNumeroPedido(pedidoPagamentoDTO.getNumeroPedido());
    pedidoSchema.setStatusPagamento(pedidoPagamentoDTO.getStatusPagamento());
    pedidoSchema.setEstado(pedidoPagamentoDTO.getEstado());
    pedidoSchema.setDataPedido(pedidoPagamentoDTO.getDataPedido());
    pedidoSchema.setValorPedido(pedidoPagamentoDTO.getValorPedido());

    return pedidoSchema;
  }


  public static PedidoPagamentoDTO toPedidoPagamentoDTO(PedidoSchema pedidoSchema) {
    PedidoPagamentoDTO pedidoPagamentoDTO = new PedidoPagamentoDTO();

    pedidoPagamentoDTO.setId(pedidoSchema.getPeidoId());
    pedidoPagamentoDTO.setNumeroPedido(pedidoSchema.getNumeroPedido());
    pedidoPagamentoDTO.setStatusPagamento(pedidoSchema.getStatusPagamento());
    pedidoPagamentoDTO.setEstado(pedidoSchema.getEstado());
    pedidoPagamentoDTO.setDataPedido(pedidoSchema.getDataPedido());
    pedidoPagamentoDTO.setValorPedido(pedidoSchema.getValorPedido());

    return pedidoPagamentoDTO;
  }



}

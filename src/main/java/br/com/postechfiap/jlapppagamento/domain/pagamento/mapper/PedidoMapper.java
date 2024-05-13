package br.com.postechfiap.jlapppagamento.domain.pagamento.mapper;

import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import br.com.postechfiap.jlapppagamento.infra.db.schema.PedidoSchema;

public class PedidoMapper {

  public static PedidoSchema toPedidoSchema(EventoPedidoDTO eventoPedidoDTO) {

    PedidoSchema pedidoSchema = new PedidoSchema();
    pedidoSchema.setId(eventoPedidoDTO.getIdMongoDB());
    pedidoSchema.setPedidoId(eventoPedidoDTO.getId());
    pedidoSchema.setNumeroPedido(eventoPedidoDTO.getNumeroPedido());
    pedidoSchema.setStatusPagamento(eventoPedidoDTO.getStatusPagamento());
    pedidoSchema.setEstado(eventoPedidoDTO.getEstado());
    pedidoSchema.setDataPedido(eventoPedidoDTO.getDataPedido());
    pedidoSchema.setValorPedido(eventoPedidoDTO.getValorPedido());

    return pedidoSchema;
  }


  public static EventoPedidoDTO toPedidoPagamentoDTO(PedidoSchema pedidoSchema) {
    EventoPedidoDTO eventoPedidoDTO = new EventoPedidoDTO();

    eventoPedidoDTO.setIdMongoDB(pedidoSchema.getId());
    eventoPedidoDTO.setId(pedidoSchema.getPedidoId());
    eventoPedidoDTO.setNumeroPedido(pedidoSchema.getNumeroPedido());
    eventoPedidoDTO.setStatusPagamento(pedidoSchema.getStatusPagamento());
    eventoPedidoDTO.setEstado(pedidoSchema.getEstado());
    eventoPedidoDTO.setDataPedido(pedidoSchema.getDataPedido());
    eventoPedidoDTO.setValorPedido(pedidoSchema.getValorPedido());

    return eventoPedidoDTO;
  }



}

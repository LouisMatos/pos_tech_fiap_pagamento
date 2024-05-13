package br.com.postechfiap.jlapppagamento.usecase.pagamento;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import br.com.postechfiap.jlapppagamento.domain.pagamento.gateway.IPagamentoGateway;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@Service
public class PagamentoUseCase {

  private final IPagamentoGateway pagamentoGateway;

  private final Logger log;

  public PagamentoUseCase(IPagamentoGateway pagamentoGateway, Logger log) {
    this.pagamentoGateway = pagamentoGateway;
    this.log = log;
  }

  public boolean processarPedido(String message) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());


    try {
      EventoPedidoDTO eventoPedidoDTO = mapper.readValue(message, EventoPedidoDTO.class);

      EventoPedidoDTO dto = pagamentoGateway.inserir(eventoPedidoDTO);

      log.info("Pedido de pagamento recebido e processado com sucesso: {}", dto);
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return true;

  }


}

package br.com.postechfiap.jlapppagamento.infra.pagamento.gateway;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.postechfiap.jlapppagamento.domain.pagamento.dto.EventoPedidoDTO;
import br.com.postechfiap.jlapppagamento.infra.db.repository.PedidoRepository;
import br.com.postechfiap.jlapppagamento.infra.db.schema.PedidoSchema;
import br.com.postechfiap.jlapppagamento.shared.logger.log.Logger;

@SpringBootTest
public class PagamentoGatewayTest {

  @Mock
  private PedidoRepository pedidoRepository;

  @Mock
  private Logger log;

  @InjectMocks
  private PagamentoGateway pagamentoGateway;

  @Test
  public void shouldInsertNewOrderSuccessfully() {
    EventoPedidoDTO eventoPedidoDTO = new EventoPedidoDTO();

    when(pedidoRepository.insert(any(PedidoSchema.class))).thenReturn(new PedidoSchema());


    pagamentoGateway.inserir(eventoPedidoDTO);

    verify(log).info(any(String.class));
  }

  @Test
  public void shouldFindOrderSuccessfully() {
    String numeroPedido = "testNumeroPedido";
    when(pedidoRepository.findByNumeroPedido(any())).thenReturn(Optional.of(new PedidoSchema()));

    pagamentoGateway.buscaPedidoNumeroPedido(numeroPedido);

    verify(log, times(0)).info(any(String.class));
  }

  @Test
  public void shouldUpdateOrderSuccessfully() {
    EventoPedidoDTO eventoPedidoDTO = new EventoPedidoDTO();
    when(pedidoRepository.save(any())).thenReturn(new PedidoSchema());

    pagamentoGateway.atualizar(eventoPedidoDTO);

    verify(log).info(any(String.class));
  }
}

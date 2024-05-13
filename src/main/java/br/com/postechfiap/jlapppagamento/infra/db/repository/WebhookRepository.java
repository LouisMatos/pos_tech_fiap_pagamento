package br.com.postechfiap.jlapppagamento.infra.db.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.postechfiap.jlapppagamento.infra.db.schema.AtualizacaoStatusPagamentoSchema;
import br.com.postechfiap.jlapppagamento.infra.db.schema.PedidoSchema;

@Repository
public interface WebhookRepository
    extends MongoRepository<AtualizacaoStatusPagamentoSchema, String> {

  Optional<PedidoSchema> findByNumeroPedido(String numero_pedido);

}

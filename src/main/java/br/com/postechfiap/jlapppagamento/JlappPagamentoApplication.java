package br.com.postechfiap.jlapppagamento;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class JlappPagamentoApplication {

  public static void main(String[] args) {
    SpringApplication.run(JlappPagamentoApplication.class, args);
  }

}

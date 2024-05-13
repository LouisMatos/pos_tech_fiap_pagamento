package br.com.postechfiap.jlapppagamento.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Violation {

  private String campo;

  private String mensagem;

}

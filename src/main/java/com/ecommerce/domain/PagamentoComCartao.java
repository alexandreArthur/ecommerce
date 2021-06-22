package com.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoComCartao extends Pagamento{
    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;
}

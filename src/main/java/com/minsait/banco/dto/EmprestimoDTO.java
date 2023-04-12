package com.minsait.banco.dto;

import com.minsait.banco.types.RelacionamentoEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class EmprestimoDTO {
    private String cpfCliente;
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    private RelacionamentoEnum relacionamento;
    private Date dataInicial;
    private Date dataFinal;
    private ClienteDTO cliente;
}

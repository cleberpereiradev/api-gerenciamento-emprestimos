package com.minsait.banco.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.minsait.banco.types.RelacionamentoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Esta classe define os atributos de um empréstimo
 * Está diretamente associada à classe Cliente.java, através da anotação @ManyToOne
 *
 * Uso de anotações para redução de código e criação de padrão de data
 *
 *
 @author Cleber Pereira
 @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf_cliente")

    private String cpfCliente;
    @NotNull(message = "Campo valor final não pode ser nulo!")
    @Min(0)
    @Column(name = "valor_inicial")
    private BigDecimal valorInicial;
    @Column(name = "valor_final")
    @NotNull(message = "Campo valor final não pode ser nulo!")
    @Min(0)
    private BigDecimal valorFinal;
    @NotNull(message = "Campo de relacionamento é obrigatório!")
    @Column(name = "relacionameto")
    private RelacionamentoEnum relacionamento;

    @NotNull(message = "Valor obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_inicial")
    private Date dataInicial;

    @NotNull(message = "Valor obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_final")
    private Date dataFinal;


    @ManyToOne
    @JoinColumn(name = "cliente_cpf")
    @JsonBackReference
    private Cliente cliente;


    public BigDecimal getValorFinal() {
        return valorFinal;
    }
    public void setValorFinal() {
        if (this.valorFinal != null) {
            this.valorFinal = this.relacionamento.calculaValorFinal(this);
        }
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public RelacionamentoEnum getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(RelacionamentoEnum relacionamento) {
        this.relacionamento = relacionamento;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
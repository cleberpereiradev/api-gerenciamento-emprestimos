package com.minsait.banco.dto;

import com.minsait.banco.entity.Cliente;
import com.minsait.banco.entity.Endereco;

import java.io.Serializable;
import java.math.BigDecimal;


public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cpf;
    private String nome;
    private String telefone;
    private BigDecimal rendimentoMensal;
    private Endereco endereco;

    public ClienteDTO(String cpf, String nome, String telefone, BigDecimal rendimentoMensal, Endereco endereco) {
        super();
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.rendimentoMensal = rendimentoMensal;
        this.endereco = endereco;
    }

    public ClienteDTO() {

    }
    public static ClienteDTO retornaCliente(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getCpf(), cliente.getNome(), cliente.getTelefone(), cliente.getRendimentoMensal(),cliente.getEndereco());
        return clienteDTO;
    }

    public static Cliente retornaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getCpf(), clienteDTO.getNome(), clienteDTO.getTelefone(), clienteDTO.getRendimentoMensal(),clienteDTO.getEndereco());
        return cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public BigDecimal getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(BigDecimal rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}

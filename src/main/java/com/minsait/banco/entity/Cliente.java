package com.minsait.banco.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "cliente")
@Table(name = "cliente")
public class Cliente implements Serializable {


    @Id
    private String cpf;

    @NotBlank(message = "Campo nome não pode estar vazio!")
    @Column(name = "nome_cliente")
    private String nome;

    @NotBlank(message = "Campo telefone não pode estar vazio!")
    @Column(name = "telefone", unique = true)
    private String telefone;

    @NotNull(message = "Campo rendimento mensal não pode estar vazio!")
    @Column(name = "rendimento_mensal")
    private BigDecimal rendimentoMensal;

    @NotNull(message = "Atributos de endereço não podem estar vazios!")
    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Emprestimo> emprestimos;


    public Cliente(String cpf, String nome, String telefone, BigDecimal rendimentoMensal, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.rendimentoMensal = rendimentoMensal;
        this.endereco = endereco;
    }

    public Cliente(String cpf, String nome, String telefone, BigDecimal rendimentoMensal, Endereco endereco, List<Emprestimo> emprestimos) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.rendimentoMensal = rendimentoMensal;
        this.endereco = endereco;
        this.emprestimos = emprestimos;
    }
    public Cliente() {

    }

    public void addEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    public void removeEmprestimo(Emprestimo emprestimo){
        emprestimos.remove(emprestimo);
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

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

}

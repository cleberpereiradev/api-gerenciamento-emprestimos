package com.minsait.banco.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Endereco implements Serializable {

    private String rua;
    private int numero;
    private String cep;




    public Endereco(Long id, Cliente cliente, String rua, int numero, String cep) {
        super();
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public Endereco() {

    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

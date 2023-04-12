package com.minsait.banco.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Esta classe define os atributos de um endereço
 * Uso de anotação para associar a classe Endereço à um cliente
 * Neste caso, quando a classe é instanciada, a classe endereço deve ser instanciada em conjunto.
 * No Banco de dados, compõe a mesma entidade de cliente
 *
 @author Cleber Pereira
 @version 0.1
 */
@Embeddable
public class Endereco implements Serializable {

    private String rua;
    private int numero;
    private String cep;




    public Endereco( String rua, int numero, String cep) {
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

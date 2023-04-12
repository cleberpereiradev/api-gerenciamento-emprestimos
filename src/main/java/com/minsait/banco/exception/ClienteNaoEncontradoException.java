package com.minsait.banco.exception;

public class ClienteNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;


    public ClienteNaoEncontradoException(String cpf) {
        super(String.format("Cliente com CPF = %s não encontado!", cpf));
    }
}

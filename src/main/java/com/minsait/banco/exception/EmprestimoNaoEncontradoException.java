package com.minsait.banco.exception;

public class EmprestimoNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;


    public EmprestimoNaoEncontradoException(long id) {
        super(String.format("Emprestimo com ID = %s não encontado!", id));
    }
}

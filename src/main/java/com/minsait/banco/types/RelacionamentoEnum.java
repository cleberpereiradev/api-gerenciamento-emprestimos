package com.minsait.banco.types;

import com.minsait.banco.entity.Cliente;
import com.minsait.banco.entity.Emprestimo;

import java.math.BigDecimal;
import java.util.List;

public enum RelacionamentoEnum {

    BRONZE(1) {
        @Override
        public BigDecimal calculaValorFinal(Emprestimo emprestimo) {
            BigDecimal valorAtual = emprestimo.getValorInicial();
            BigDecimal valorFinal = valorAtual.multiply(new BigDecimal(1.8));
            return valorFinal;
        }
    },
    PRATA(2) {
        @Override
        public BigDecimal calculaValorFinal(Emprestimo emprestimo) {
            BigDecimal valorAtual = emprestimo.getValorInicial();
            BigDecimal comparativo = new BigDecimal(5000);
            BigDecimal valorFinal = BigDecimal.ZERO;
            if (valorAtual.compareTo(comparativo) == 1){
                valorFinal = valorAtual.multiply(new BigDecimal(1.4));
            }else{
                valorFinal = valorAtual.multiply(new BigDecimal(1.6));
            }
            return valorFinal;
        }
    },
    OURO(3) {
        @Override
        public BigDecimal calculaValorFinal(Emprestimo emprestimo) {
            BigDecimal valorAtual = emprestimo.getValorInicial();
            Cliente cliente = emprestimo.getCliente();
            List<Emprestimo> emprestimos = cliente.getEmprestimos();
            int quantidadeEmprestimos = 0;
            if(!emprestimos.isEmpty()){
                quantidadeEmprestimos = emprestimos.size();
            }
            BigDecimal valorFinal = BigDecimal.ZERO;
            if(quantidadeEmprestimos < 2){
                valorFinal = valorAtual.multiply(new BigDecimal(1.2));
            } else {
                valorFinal = valorAtual.multiply(new BigDecimal(1.3));
            }
            return valorFinal;
        }
    };
    private int codigo;
    private RelacionamentoEnum(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return this.codigo;
    }
    public abstract BigDecimal calculaValorFinal(Emprestimo emprestimo);

}

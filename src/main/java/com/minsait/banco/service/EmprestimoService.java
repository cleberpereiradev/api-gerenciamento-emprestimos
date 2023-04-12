package com.minsait.banco.service;

import com.minsait.banco.entity.Cliente;
import com.minsait.banco.entity.Emprestimo;
import com.minsait.banco.exception.EmprestimoNaoEncontradoException;
import com.minsait.banco.message.MensagemDeSucesso;
import com.minsait.banco.repository.ClienteRepository;
import com.minsait.banco.repository.EmprestimoRepository;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Emprestimo> retornaEmprestimos(){
        return this.emprestimoRepository.findAll();
    }


    public ResponseEntity<?> retornaEmprestimoPorId(Long id) {
        Optional<Emprestimo> retorno = this.emprestimoRepository.findById(id);
        if(retorno.orElseGet(() -> null) != null) {
            return new ResponseEntity<>(retorno.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Emprestimo não encontrado, tente novamente!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> cadastrarEmprestimo(Emprestimo emprestimo){
        if(clienteRepository.existsById(emprestimo.getCpfCliente())){
            Cliente cliente = this.clienteRepository.findById(emprestimo.getCpfCliente()).get();
            try{
                somaTotalEmprestimos(emprestimo);
                if(somaTotalEmprestimos(emprestimo).compareTo(cliente.getRendimentoMensal().multiply(new BigDecimal(10))) <= 0){
                    emprestimo.setCpfCliente(cliente.getCpf());
                    emprestimo.setCliente(cliente);
                    emprestimo.setValorFinal();
                    cliente.getEmprestimos().add(emprestimo);
                    return new ResponseEntity<>(this.emprestimoRepository.save(emprestimo), HttpStatus.CREATED);
                }
            }catch (JpaSystemException | GenericJDBCException | HttpMessageNotReadableException |
                    DataIntegrityViolationException e){
                e.printStackTrace();
                return new ResponseEntity<>("Dados inseridos invalidos, ou valor máximo ultrapassado!", HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>("Cliente não cadastrado!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Valor máximo ultrapassado!",HttpStatus.BAD_REQUEST);
    }

    public MensagemDeSucesso deletaEmprestimoPorId(long id) throws EmprestimoNaoEncontradoException{
       if(emprestimoRepository.existsById(id)){
           this.emprestimoRepository.deleteById(id);
           MensagemDeSucesso msg = new MensagemDeSucesso();
           msg.setMensagem("Empréstimo deletado com sucesso!");
           return msg;
       }
       throw new EmprestimoNaoEncontradoException(id);
    }

    public BigDecimal somaTotalEmprestimos(Emprestimo emp){
        BigDecimal valorTotalEmprestimos = new BigDecimal("0");
        for (Emprestimo emprestimos : retornaEmprestimos()){
            valorTotalEmprestimos = emprestimos.getValorInicial().multiply(new BigDecimal(10));
        }
        return valorTotalEmprestimos;
    }

}

package com.minsait.banco.service;

import com.minsait.banco.entity.Cliente;
import com.minsait.banco.exception.ClienteNaoEncontradoException;
import com.minsait.banco.message.MensagemDeSucesso;
import com.minsait.banco.repository.ClienteRepository;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired



    public List<Cliente> retornaTodosClientes(){
        return this.clienteRepository.findAll();
    }

    public ResponseEntity<?> retornaClientePorCpf(String cpf) {
        Optional<Cliente> retorno = this.clienteRepository.findById(cpf);
        if(retorno.orElseGet(() -> null) != null) {
            return new ResponseEntity<Cliente>(retorno.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Cliente não encontrado, tente novamente!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> cadastraCliente(@Valid Cliente cliente){
        if(!clienteRepository.existsById(cliente.getCpf())){
            try{
                return new ResponseEntity<>(this.clienteRepository.save(cliente), HttpStatus.CREATED);
            } catch (JpaSystemException | GenericJDBCException | HttpMessageNotReadableException | DataIntegrityViolationException e){
                e.printStackTrace();
                return new ResponseEntity<>("Dados inválidos para cadastro de cliente no sistema!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Cliente já possui cadastro!", HttpStatus.BAD_REQUEST);
        }

    }

    public Cliente alterarCliente(String cpf, @Valid Cliente cliente) throws ClienteNaoEncontradoException {

        if (this.clienteRepository.existsById(cpf)) {
            Cliente clienteASerAlterado = this.clienteRepository.findById(cpf).get();

            cliente.setCpf(cpf);

            if (cliente.getNome().equals(null)) {
                cliente.setNome(clienteASerAlterado.getNome());
            }
            if (cliente.getTelefone().equals(null)) {
                cliente.setTelefone(clienteASerAlterado.getTelefone());
            }
            if (cliente.getEndereco().equals(null)) {
                cliente.setEndereco(clienteASerAlterado.getEndereco());
            }
            if (cliente.getRendimentoMensal().equals(null)) {
                cliente.setRendimentoMensal(clienteASerAlterado.getRendimentoMensal());
            }

            return this.clienteRepository.save(cliente);
        }

        throw new ClienteNaoEncontradoException(cpf);

    }

    @Transactional
    public MensagemDeSucesso deletaClientePorCpf(String cpf) throws ClienteNaoEncontradoException {
        if(clienteRepository.existsById(cpf)){
            this.clienteRepository.deleteById(cpf);
            MensagemDeSucesso msg = new MensagemDeSucesso();
            msg.setMensagem("Cliente deletado com sucesso!");
            return msg;
        }
        throw new ClienteNaoEncontradoException(cpf);
    }



}

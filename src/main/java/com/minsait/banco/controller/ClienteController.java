package com.minsait.banco.controller;

import com.minsait.banco.dto.ClienteDTO;
import com.minsait.banco.entity.Cliente;
import com.minsait.banco.exception.ClienteNaoEncontradoException;
import com.minsait.banco.service.ClienteService;
import com.minsait.banco.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/banco")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    private EmprestimoService emprestimoService;


    @GetMapping(value = "/clientesCompleto")
    public List<Cliente> RetornaClientes(){
        List<Cliente> retorno = this.clienteService.retornaTodosClientes();
        return retorno;
    }

    @ResponseBody
    @GetMapping(value = "/clientes")
    public ResponseEntity<List<ClienteDTO>> retornaDadosCliente() {
        List<ClienteDTO> clienteDTOs = new ArrayList<ClienteDTO>();
        List<Cliente> clientes = this.clienteService.retornaTodosClientes();

        for(Cliente cliente : clientes){
            ClienteDTO dto = new ClienteDTO();
            dto.setCpf(cliente.getCpf());
            dto.setNome(cliente.getNome());
            dto.setTelefone(cliente.getTelefone());
            dto.setEndereco(cliente.getEndereco());
            dto.setRendimentoMensal(cliente.getRendimentoMensal());
            clienteDTOs.add(dto);
        }
        return new ResponseEntity<List<ClienteDTO>>(clienteDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/clientes/{cpf}")
    public ResponseEntity<?> retornaCliente(@PathVariable String cpf){
        return clienteService.retornaClientePorCpf(cpf);
    }


    @PostMapping(value = "/clientes")
    public ResponseEntity<?> cadastraCliente(@Valid @RequestBody Cliente cliente){
        return this.clienteService.cadastraCliente(cliente);
    }

    @PutMapping("/clientes/{cpf}")
    public ClienteDTO alteraCliente(@PathVariable String cpf, @Valid @RequestBody ClienteDTO cliente) throws ClienteNaoEncontradoException{
        //transforma cliente em dto
        Cliente clienteRequest = ClienteDTO.retornaCliente(cliente);

        Cliente clienteAtualizado = this.clienteService.alterarCliente(cpf, clienteRequest);
        //transformacao de cliente em clientedto

        return ClienteDTO.retornaCliente(clienteAtualizado);
    }

    @DeleteMapping(value = "/clientes/{cpf}")
    public void deletaClientePorCpf(@PathVariable String cpf) throws ClienteNaoEncontradoException {
        this.clienteService.deletaClientePorCpf(cpf);
    }


}

package com.minsait.banco.controller;

import com.minsait.banco.entity.Emprestimo;
import com.minsait.banco.exception.EmprestimoNaoEncontradoException;
import com.minsait.banco.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/banco/clientes/{cpf}/emprestimos")
public class EmprestimoController {


    @Autowired
    private EmprestimoService emprestimoService;


    @GetMapping
    public List<Emprestimo> retornaEmprestimos(){
        return this.emprestimoService.retornaEmprestimos();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> retornaEmprestimo(@PathVariable long id){
        return emprestimoService.retornaEmprestimoPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> cadastraEmprestimo(@Valid @RequestBody Emprestimo emprestimo){
            return this.emprestimoService.cadastrarEmprestimo(emprestimo);
    }

    @DeleteMapping(value = "/{id}")
    public void deletarEmprestimoPorId(@PathVariable long id) throws EmprestimoNaoEncontradoException {
        this.emprestimoService.deletaEmprestimoPorId(id);
    }



}

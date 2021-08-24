package br.com.biopark.sistemacadastro.controller;

import br.com.biopark.sistemacadastro.dto.Cliente;
import br.com.biopark.sistemacadastro.repository.ClienteDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteDTORepository repository;

    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> listar() {
        return new ResponseEntity<>(repository.findAll(),
                HttpStatus.OK);
    }

    @GetMapping("/porId/{id}")
    public ResponseEntity<Cliente> porId(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(repository.findById(id).get(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(
            @RequestBody Cliente cliente
    ) {
        repository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> remover(
            @PathVariable Long id
    ) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>("Removido,",
                    HttpStatus.OK);
        } catch ( Exception ex) {
            return new ResponseEntity<>("Não encontrado,",
                    HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Cliente> atualizar(
            @RequestBody Cliente cliente,
            @PathVariable  Long id
    ) {
        Cliente clienteBD = repository.findById(id).get();
        clienteBD.setNome(cliente.getNome());
        clienteBD.setAtivo(cliente.isAtivo());
        clienteBD.setCpf(cliente.getCpf());
        clienteBD.setDataNascimento(cliente.getDataNascimento());
        clienteBD.setTelefone(cliente.getTelefone());
        clienteBD = repository.save(clienteBD);
        return new ResponseEntity<>(clienteBD, HttpStatus.OK);
    }

}

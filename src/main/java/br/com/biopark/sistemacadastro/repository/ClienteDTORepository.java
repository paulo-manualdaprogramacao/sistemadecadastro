package br.com.biopark.sistemacadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biopark.sistemacadastro.dto.Cliente;

public interface ClienteDTORepository extends
        JpaRepository<Cliente, Long> {

    public Cliente findByCpf(String cpf);

}
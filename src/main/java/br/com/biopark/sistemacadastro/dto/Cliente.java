package br.com.biopark.sistemacadastro.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Cliente implements Serializable {

    @GeneratedValue
    @Id
    private Long id;

    private String nome;

    private String cpf;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    private String telefone;

    private boolean ativo;

}

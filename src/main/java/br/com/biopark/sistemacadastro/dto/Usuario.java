package br.com.biopark.sistemacadastro.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Usuario implements Serializable {

    private Long id;

    private String login;

    private String token;

}

package br.com.biopark.sistemacadastro.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class PedidoItem implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private String descricao;

    private Double quantidade;

    private Double valorUnitario;

}

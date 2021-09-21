package br.com.biopark.sistemacadastro.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Date dataPedido;

    @Column(name = "valor_total")
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoItem> itens;

    private EnumStatus statusPedido;

}

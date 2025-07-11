package com.lab4.buen_sabor_backend.model;


import com.lab4.buen_sabor_backend.model.enums.Estado;
import com.lab4.buen_sabor_backend.model.enums.FormaPago;
import com.lab4.buen_sabor_backend.model.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido extends Master{

    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private boolean pagado;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    private LocalDateTime fechaPedido;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "empleado_delivery_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "domicilio_id", nullable = true)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalles = new ArrayList<>();
}


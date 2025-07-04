package com.lab4.buen_sabor_backend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria extends Master {

    private String denominacion;

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre;

    @OneToMany(mappedBy = "categoriaPadre", cascade = CascadeType.REFRESH)
    private Set<Categoria> subcategorias = new HashSet<>();

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REFRESH)
    private Set<Articulo> articulos = new HashSet<>();

    private String urlImagen;
}


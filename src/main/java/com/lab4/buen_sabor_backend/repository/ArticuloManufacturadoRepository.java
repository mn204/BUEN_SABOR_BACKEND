package com.lab4.buen_sabor_backend.repository;

import com.lab4.buen_sabor_backend.model.ArticuloInsumo;
import com.lab4.buen_sabor_backend.model.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends MasterRepository<ArticuloManufacturado, Long>, JpaSpecificationExecutor<ArticuloManufacturado> {

    // Buscar por denominación (nombre del producto)
    List<ArticuloManufacturado> findByDenominacionContainingIgnoreCaseAndEliminadoFalse(String denominacion);

    // Buscar por categoría
    List<ArticuloManufacturado> findByCategoriaIdAndEliminadoFalse(Long categoriaId);

    // Buscar por rango de precio
    List<ArticuloManufacturado> findByPrecioVentaBetweenAndEliminadoFalse(Double precioMin, Double precioMax);

    // Buscar productos activos con paginación
    Page<ArticuloManufacturado> findByEliminadoFalseOrderByDenominacionAsc(Pageable pageable);

    // Buscar por tiempo de preparación
    List<ArticuloManufacturado> findByTiempoEstimadoMinutosLessThanEqualAndEliminadoFalse(Integer tiempoMaximo);

    // Query personalizada: productos con sus ingredientes
    @Query("SELECT DISTINCT am FROM ArticuloManufacturado am " +
            "LEFT JOIN FETCH am.detalles d " +
            "LEFT JOIN FETCH d.articuloInsumo " +
            "WHERE am.eliminado = false")
    List<ArticuloManufacturado> findAllWithIngredientes();
    @Query("SELECT a FROM ArticuloInsumo a WHERE a.id = :id")
    ArticuloManufacturado findByArticuloInsumoId(@Param("id") Long id);
    // Query personalizada: productos por categoría con ingredientes
    @Query("SELECT DISTINCT am FROM ArticuloManufacturado am " +
            "LEFT JOIN FETCH am.detalles d " +
            "LEFT JOIN FETCH d.articuloInsumo " +
            "WHERE am.categoria.id = :categoriaId AND am.eliminado = false")
    List<ArticuloManufacturado> findByCategoriaWithIngredientes(@Param("categoriaId") Long categoriaId);
    List<ArticuloManufacturado> findByDetalles_ArticuloInsumo_Id(Long id);

    // Verificar si existe por denominación (para evitar duplicados)
    boolean existsByDenominacionIgnoreCaseAndEliminadoFalse(String denominacion);

    // Verificar si existe por denominación excluyendo un ID específico (para updates)
    boolean existsByDenominacionIgnoreCaseAndEliminadoFalseAndIdNot(String denominacion, Long id);
}
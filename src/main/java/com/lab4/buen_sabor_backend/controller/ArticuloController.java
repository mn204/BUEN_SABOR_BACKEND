package com.lab4.buen_sabor_backend.controller;

import com.lab4.buen_sabor_backend.dto.ArticuloDTO;
import com.lab4.buen_sabor_backend.mapper.ArticuloMapper;
import com.lab4.buen_sabor_backend.model.Articulo;
import com.lab4.buen_sabor_backend.model.Categoria;
import com.lab4.buen_sabor_backend.service.ArticuloService;
import com.lab4.buen_sabor_backend.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulo-insumos")
@CrossOrigin(origins = "*")
@Tag(name = "Artículos Insumos", description = "Operaciones relacionadas con artículos insumos")
public class ArticuloController extends MasterControllerImpl<Articulo, ArticuloDTO, Long> implements MasterController<ArticuloDTO, Long> {

    private static final Logger logger = LoggerFactory.getLogger(ArticuloController.class);

    private final ArticuloService articuloService;
    private final CategoriaService categoriaService;
    private final ArticuloMapper articuloMapper;

    @Autowired
    protected ArticuloController(ArticuloService articuloService, CategoriaService categoriaService, ArticuloMapper articuloMapper) {
        super(articuloService);
        this.articuloService = articuloService;
        this.categoriaService = categoriaService;
        this.articuloMapper = articuloMapper;
    }

    @Override
    protected Articulo toEntity(ArticuloDTO dto) {
        return articuloMapper.toEntity(dto);
    }

    @Override
    protected ArticuloDTO toDTO(Articulo entity) {
        return articuloMapper.toDTO(entity);
    }

    @Operation(summary = "Buscar artículos por denominación no eliminados")
    @GetMapping("/buscar")
    public ResponseEntity<List<ArticuloDTO>> buscarPorDenominacion(
            @Parameter(description = "Denominación para búsqueda") @RequestParam String denominacion) {
        logger.info("Buscando articulos que contengan: {}", denominacion);
        List<Articulo> articulos = articuloService.findByDenominacionAndEliminadoFalse(denominacion);
        List<ArticuloDTO> articulosDTO = articuloMapper.toDTOsList(articulos);
        return ResponseEntity.ok(articulosDTO);
    }

    @Operation(summary = "Verificar stock de un artículo para un pedido específico")
    @PostMapping("/verificar-stock/{id}")
    public ResponseEntity<Boolean> verificarStockPedido(
            @Parameter(description = "Artículo a verificar") @RequestBody Articulo articulo,
            @Parameter(description = "ID del artículo") @PathVariable Long id) {
        try {
            boolean resultado = articuloService.verificarStockArticulo(articulo, id);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            logger.error("Error en controlador: ", e);
            return ResponseEntity.ok(false);
        }
    }

    @Operation(summary = "Buscar artículos por categoría")
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ArticuloDTO>> buscarPorCategoria(
            @Parameter(description = "ID de categoría") @PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.getById(id);
            List<Articulo> articulos = articuloService.findArticuloByCategoriaId(id);
            List<ArticuloDTO> articulosDTO = articuloMapper.toDTOsList(articulos);
            return ResponseEntity.ok(articulosDTO);
        } catch (Exception e) {
            logger.error("Error en controlador: ", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

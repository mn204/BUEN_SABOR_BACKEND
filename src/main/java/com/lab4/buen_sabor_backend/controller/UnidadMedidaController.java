package com.lab4.buen_sabor_backend.controller;

import com.lab4.buen_sabor_backend.dto.ImagenArticuloDTO;
import com.lab4.buen_sabor_backend.dto.UnidadMedidaDTO;
import com.lab4.buen_sabor_backend.mapper.UnidadMedidaMapper;
import com.lab4.buen_sabor_backend.model.UnidadMedida;
import com.lab4.buen_sabor_backend.service.UnidadMedidaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/unidadmedida")
@CrossOrigin(origins = "*")
@Tag(name = "Unidad de Medida", description = "Operaciones sobre unidades de medida")
public class UnidadMedidaController extends MasterControllerImpl<UnidadMedida, UnidadMedidaDTO, Long> implements MasterController<UnidadMedidaDTO, Long> {

    private static final Logger logger = LoggerFactory.getLogger(UnidadMedidaController.class);

    private final UnidadMedidaService unidadMedidaService;
    private final UnidadMedidaMapper unidadMedidaMapper;

    @Autowired
    public UnidadMedidaController(UnidadMedidaService unidadMedidaService, UnidadMedidaMapper unidadMedidaMapper) {
        super(unidadMedidaService);
        this.unidadMedidaService = unidadMedidaService;
        this.unidadMedidaMapper = unidadMedidaMapper;
    }

    @Override
    protected UnidadMedida toEntity(UnidadMedidaDTO dto) {
        return unidadMedidaMapper.toEntity(dto);
    }

    @Override
    protected UnidadMedidaDTO toDTO(UnidadMedida entity) {
        return unidadMedidaMapper.toDTO(entity);
    }
}
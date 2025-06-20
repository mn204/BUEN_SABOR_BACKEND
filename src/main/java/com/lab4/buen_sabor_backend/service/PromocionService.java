package com.lab4.buen_sabor_backend.service;

import com.lab4.buen_sabor_backend.model.Promocion;
import com.lab4.buen_sabor_backend.model.Sucursal;
import com.lab4.buen_sabor_backend.model.enums.TipoPromocion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface PromocionService extends MasterService<Promocion, Long> {
    boolean existsById(Long id);
    List<Promocion> findPromocionsBySucursal(Sucursal sucursal);

    List<Promocion> findByDetalles_Articulo_Id(Long id);

    Page<Promocion> buscarPromocionesFiltradas(Long idSucursal, Boolean activa, TipoPromocion tipoPromocion,
                                               LocalDate fechaDesde, LocalDate fechaHasta, Pageable pageable);


}
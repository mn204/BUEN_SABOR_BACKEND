package com.lab4.buen_sabor_backend.service;

import com.lab4.buen_sabor_backend.model.Pedido;

public interface PdfService {
    byte[] generarFacturaPedido(Pedido pedido);
    byte[] generarNotaCreditoPedido(Pedido pedido);
    byte[] generarAvisoCancelacionEfectivo(Pedido pedido);
}

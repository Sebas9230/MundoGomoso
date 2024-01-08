package com.yourcompany.mundogomoso.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity @Getter @Setter
@View(extendsView="super.DEFAULT", // Extiende de la vista de DocumentoComercial
members="factura { factura } " // Añadimos factura dentro de una pestaña
)

@View( name="SinClienteNiFactura", // Una vista llamada SinClienteNiFactura
members=                       // que no incluye cliente ni factura
    "anyo, numero, fecha;" +   // Ideal para ser usada desde Factura
    "detalles;" +
    "observaciones"
)
public class Pedido extends DocumentoComercial {
 
    @ManyToOne
    @ReferenceView("SinClienteNiPedidos") // Esta vista se usa para visualizar factura
    Factura factura; // Referencia a factura añadida
 
}

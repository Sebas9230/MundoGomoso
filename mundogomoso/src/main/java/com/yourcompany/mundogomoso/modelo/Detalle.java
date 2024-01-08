package com.yourcompany.mundogomoso.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import com.tuempresa.facturacion.calculadores.*;

import lombok.*;
 
@Embeddable @Getter @Setter
public class Detalle {
 
    int cantidad;
 
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    Producto producto;
    
    @Money
    
    @Depends("precioPorUnidad, cantidad") // Cuando usuario cambie producto o cantidad
    public BigDecimal getImporte() { // esta propiedad se recalculará y se redibujará
        if (precioPorUnidad == null) return BigDecimal.ZERO;
        return new BigDecimal(cantidad).multiply(precioPorUnidad);
    }
    
    @DefaultValueCalculator(
    	    value=CalculadorPrecioPorUnidad.class, // Esta clase calcula el valor inicial
    	    properties=@PropertyValue(
    	        name="numeroProducto", // La propiedad numeroProducto del calculador...
    	        from="producto.numero") // ... se llena con el valor de producto.numero de la entidad
    	)
    	@Money
    	BigDecimal precioPorUnidad; // Una propiedad persistente convencional
}
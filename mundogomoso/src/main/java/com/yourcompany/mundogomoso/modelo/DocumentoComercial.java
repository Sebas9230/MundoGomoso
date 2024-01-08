package com.yourcompany.mundogomoso.modelo;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import com.tuempresa.facturacion.calculadores.*;

import lombok.*;
 
@Entity @Getter @Setter
@View( members=
"anyo, numero, fecha;" +
	"datos {" +
	    "cliente;" +
	    "detalles;" +
	    "observaciones" +
	"}" 
)


abstract public class DocumentoComercial extends Identificable {

 
    @Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Año actual
    int anyo;
 
    @Column(length=6)
    @DefaultValueCalculator(value=CalculadorSiguienteNumeroParaAnyo.class,
        properties=@PropertyValue(name="anyo") // Para inyectar el valor de anyo de Factura
                                               // en el calculador antes de llamar a calculate()
    )
    int numero;
    
    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Fecha actual
    LocalDate fecha;
 
    @TextArea
    String observaciones;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=false) // El cliente es obligatorio
    @ReferenceView("Simple") // La vista llamada 'Simple' se usará para visualizar esta referencia
    Cliente cliente;
    
    @ElementCollection
    @ListProperties(
        "producto.numero, producto.descripcion, cantidad, precioPorUnidad, " +
        "importe+[" + 
        	"documentoComercial.porcentajeIVA," +
        	"documentoComercial.iva," +
        	"documentoComercial.importeTotal" +
        "]" 
    )	
    private Collection<Detalle> detalles;
    
    @Digits(integer=2, fraction=0) // Para indicar su tamaño
    BigDecimal porcentajeIVA;
       
    @ReadOnly
    @Money
    @Calculation("sum(detalles.importe) * porcentajeIVA / 100")
    BigDecimal iva;

    @ReadOnly
    @Money
    @Calculation("sum(detalles.importe) + iva")    
    BigDecimal importeTotal;    
    
}
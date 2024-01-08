package com.yourcompany.mundogomoso.modelo;

import javax.persistence.*;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import lombok.*;
 
@MappedSuperclass // Marcada como una superclase mapeada en vez de como una entidad
@Getter @Setter
public class Identificable {
 
    @Id 
    @Hidden
    @GeneratedValue(generator="system-uuid") 
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length=32)
    String oid; // La definición de propiedad incluye anotaciones de OpenXava y JPA
    
}
 
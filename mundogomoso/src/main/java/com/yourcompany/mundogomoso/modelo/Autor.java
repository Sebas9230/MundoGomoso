package com.yourcompany.mundogomoso.modelo;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity @Getter @Setter
public class Autor extends Identificable { // Extiende de Identificable por tanto no necesita tener una propiedad id
 
 
    @Column(length=50) @Required
    String nombre;
    
    @OneToMany(mappedBy="autor")
    @ListProperties("numero, descripcion, precio")
    Collection<Producto> productos;
  
}
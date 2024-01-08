package com.yourcompany.mundogomoso.modelo;

import lombok.*;

/*public class Incidencia {

	private void metodo() {
		Propiedad elObjeto = new Propiedad();
		int c = elObjeto.getCantidad(); // Nunca int c = elObjeto.cantidad 
		elObjeto.setCantidad(c + 10); // Nunca elObjeto.cantidad = c + 10
	}
}*/

//@Data // NUNCA USES @Data
/*@Getter @Setter
public class Incidencia {

 int numero;
 String descripcion;
	
 public String getDescripcion() { // Tu propio getter sobrescribe el generado por Lombok
     if (descripcion == null) return "Todavía sin descripción";
     return descripcion;
 }
}*/

public class Incidencia {

	//Propiedad persistente
	@Getter @Setter // Tiene getter y setter
	int cantidad; // Tiene un campo, por tanto es persistente
	
	@Getter @Setter // Tiene getter y setter
	int precio;
	
	//Propiedad calculada
	public int getImporte() { // No tiene campo, ni setter, solo un getter
	 return cantidad * precio; // con un cálculo
	}
}
package mx.tecno.business;

import java.util.List;

import mx.tecno.dao.ProveedorDAO;
import mx.tecno.dto.Proveedor;

public class LogicaProveedor {
	
public List<Proveedor> cargarProveedores(){
		
		List<Proveedor> listaProveedores= new ProveedorDAO().obtenerProveedores();
		return listaProveedores;
	}



public void agregarProveedor(Proveedor proveedor){
	new ProveedorDAO().guardarProveedor(proveedor);
}

public void borrarProveedor(Proveedor proveedor){

	new  ProveedorDAO().eliminarProveedor(proveedor);
}


public void actualizarProveedor(Proveedor proveedor){

	new ProveedorDAO().modificarProveedor(proveedor);
}


public boolean validarProveedor (String nomProveedor){
	

	List<Proveedor> listaProveedores= new LogicaProveedor().cargarProveedores();


	for(Proveedor proveedor:listaProveedores){

		if(proveedor.getNombre().equalsIgnoreCase(nomProveedor))
			return true;
	}

	return false;
}

public Proveedor obtenerProveedorPorNomProveedor(String nomProveedor){
	
	return new ProveedorDAO().obtenerProveedorPorNomProveedor(nomProveedor);
}

public boolean validarPatronProveedor(String proveedor){

	boolean patronCorrecto=false;

	for(int z=0;z<proveedor.length();z++){
		if(!(    
				(proveedor.charAt(z)>='a' &&  proveedor.charAt(z)<='z') || 
				(proveedor.charAt(z)>='A' &&  proveedor.charAt(z)<='Z') ||
				(proveedor.charAt(z)==' ')
				)){
			patronCorrecto=true;
			break;


		}
	}
	return patronCorrecto;

}
}

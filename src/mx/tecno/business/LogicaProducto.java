package mx.tecno.business;

import java.util.List;

import mx.tecno.dao.ProductoDAO;
import mx.tecno.dto.Producto;


public class LogicaProducto {

	public List<Producto> cargarProductos(){

		List<Producto> listaProveedores= new ProductoDAO().obtenerProductos();
		return listaProveedores;
	}

	

	public Producto cargarProductoId(int id){
		return new ProductoDAO().findById(id);
	}


	public void borrarProducto(Producto producto) {

		new ProductoDAO().eliminarProducto(producto);
	}

	public void agregarProducto(Producto producto){

		new ProductoDAO().guardarProducto(producto);
	}


	public void actualizarProducto(Producto producto){
		
		new ProductoDAO().modificarProducto(producto);
	}
	
	
	public boolean validarProducto (String nomProducto){
	

	List<Producto> listaProductos= new LogicaProducto().cargarProductos();


	for(Producto producto:listaProductos){

		if(producto.getNombre().equalsIgnoreCase(nomProducto))
			return true;
	}

	return false;
}
	
	public boolean validarPatronProducto(String producto){

		boolean patronCorrecto=false;

		for(int z=0;z<producto.length();z++){
			if(!(    
					(producto.charAt(z)>='a' &&  producto.charAt(z)<='z') || 
					(producto.charAt(z)>='A' &&  producto.charAt(z)<='Z') ||
					(producto.charAt(z)==' ')
					)){
				patronCorrecto=true;
				break;


			}
		}
		return patronCorrecto;

	}
	
	
	public Producto obtenerProductoPorNombreProducto(String nomProducto){
		
	
		return new ProductoDAO().obtenerProductoPorNomProducto(nomProducto);
	}
}

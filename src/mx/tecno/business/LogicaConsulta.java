package mx.tecno.business;

import java.util.ArrayList;
import java.util.List;
import mx.tecno.dao.ProductoDAO;
import mx.tecno.dto.Producto;

public class LogicaConsulta {
	
	public List<Producto> cargarProductos(){
		List<Producto>lista= new ProductoDAO().obtenerProductos();
		return lista;
	}
	
	public List<Producto> cargarProducto(String categoria){
		List<Producto> lista= new ProductoDAO().obtenerProductos();
		List<Producto> sublista = new ArrayList<Producto>();
		for(Producto producto:lista)
		{
			if(producto.getCategoria().equals(categoria))
			sublista.add(producto);
		}
		return sublista;
	}

}

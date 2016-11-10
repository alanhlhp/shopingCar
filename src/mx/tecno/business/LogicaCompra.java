package mx.tecno.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.DateFormatter;

import mx.tecno.dao.CompraDAO;
import mx.tecno.dao.ProductoDAO;
import mx.tecno.dao.UsuarioDAO;
import mx.tecno.dto.Compra;
import mx.tecno.dto.ConsultaCompra;
import mx.tecno.dto.Producto;
import mx.tecno.dto.Usuario;

public class LogicaCompra {

	public void nuevaCompra(int cantidad, Date fecha, int id, Usuario usuario){
		new CompraDAO().nuevaCompra((Integer)cantidad, fecha, new ProductoDAO().findById(id), usuario);
	}

	public List<ConsultaCompra> consultaCompra(Date fecha){

		DateFormat formatter ; 
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Sfecha = formatter.format(fecha);

		List<ConsultaCompra> lista=new ArrayList<ConsultaCompra>();
		List<Compra>compra = new CompraDAO().consultaFecha(Sfecha);
		ConsultaCompra consulta = new ConsultaCompra();
		
		Usuario usr;
		Producto prod;
		
		for(int i=0; i<compra.size(); i++){
			usr = new UsuarioDAO().obtenerUsuariosbyId(compra.get(i).getUsuario().getIdusuario());
			prod = new ProductoDAO().findById(compra.get(i).getProducto().getIdproducto());
			
			consulta.setIdUsuario(usr.getIdusuario());
			consulta.setNombreUsuario(usr.getNombre());
			consulta.setIdProducto(prod.getIdproducto());
			consulta.setCantidad(compra.get(i).getCantidad());
			
			lista.add(consulta);
		}
		return lista;
	}

	public boolean consultaCompraExiste(Date fecha) {
		DateFormat formatter ; 
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Sfecha = formatter.format(fecha);
		
		return new CompraDAO().consultaFechaExiste(Sfecha);
	}

}

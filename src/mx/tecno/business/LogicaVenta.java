package mx.tecno.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.tecno.dao.CompraDAO;
import mx.tecno.dao.ProductoDAO;
import mx.tecno.dao.VentaDAO;
import mx.tecno.dao.UsuarioDAO;
import mx.tecno.dto.Compra;
import mx.tecno.dto.ConsultaCompra;
import mx.tecno.dto.ConsultaVenta;
import mx.tecno.dto.Producto;
import mx.tecno.dto.Usuario;
import mx.tecno.dto.Venta;

public class LogicaVenta {

	public void crearNuevaCompra(int idUsuario, Producto producto, Date fecha,
			Integer cantidad) {

		new VentaDAO().nuevaVenta(new UsuarioDAO().obtenerUsuariosbyId(idUsuario),
				producto, fecha, cantidad);

	}

	public List<ConsultaVenta> consultaVenta(Date fecha) {
		DateFormat formatter ; 
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Sfecha = formatter.format(fecha);

		List<ConsultaVenta> lista=new ArrayList<ConsultaVenta>();
		List<Venta>venta = new VentaDAO().consultabyFecha(Sfecha);

		ConsultaVenta consulta = new ConsultaVenta();
		Usuario u;
		Producto p;

		for(Venta v: venta){

			u = new UsuarioDAO().obtenerUsuariosbyId(v.getUsuario().getIdusuario());
			p = new ProductoDAO().findById(v.getProducto().getIdproducto());

			consulta.setIdUsuario(u.getIdusuario());
			consulta.setNombreUsuario(u.getNombre());
			consulta.setIdProducto(p.getIdproducto());
			consulta.setNombreProducto(p.getNombre());
			consulta.setCantidad(v.getCantidad());

			lista.add(consulta);
		}
		return lista;
	}

	public boolean consultaVentaExiste(Date fecha) {
		DateFormat formatter ; 
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String Sfecha = formatter.format(fecha);

		return new VentaDAO().consultabyFechaExiste(Sfecha);
	}

}

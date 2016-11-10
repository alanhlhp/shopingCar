package mx.tecno.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mx.tecno.dto.Compra;
import mx.tecno.dto.Producto;
import mx.tecno.dto.Usuario;
import mx.tecno.dto.Venta;

public class VentaDAO {


	public void nuevaVenta(Usuario usuario, Producto producto,
			Date fecha, Integer cantidad) {

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		Venta venta = new Venta();

		venta.setUsuario(usuario);
		venta.setProducto(producto);
		venta.setFecha(fecha);
		venta.setCantidad(cantidad);

		session.save(venta);
		producto.setExistencia(producto.getExistencia()-cantidad);
		session.update(producto);

		tran.commit();
		session.close();
	}

	public List<Venta> consultabyFecha(String sfecha) {

		List<Venta> lista= null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran= session.beginTransaction();
		lista = (List<Venta>) session.createQuery("FROM Venta WHERE fecha='"+sfecha+"'").list();
		tran.commit();
		session.close();
		return lista;
	}

	public boolean consultabyFechaExiste(String sfecha) {
		
		List<Venta> venta=null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran= session.beginTransaction();
		venta = (List<Venta>) session.createQuery("FROM Venta WHERE fecha='"+sfecha+"'").list();
		tran.commit();
		session.close();

		if (venta!=null && !venta.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}

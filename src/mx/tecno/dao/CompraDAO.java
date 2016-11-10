package mx.tecno.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mx.tecno.dto.Compra;
import mx.tecno.dto.Producto;
import mx.tecno.dto.Proveedor;
import mx.tecno.dto.Usuario;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CompraDAO {

	public void nuevaCompra(Integer cantidad, Date fecha, Producto producto, Usuario usuario){

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();

		Compra compra = new Compra();
		compra.setCantidad(cantidad);
		compra.setFecha(fecha);
		compra.setUsuario(usuario);
		compra.setProducto(producto);

		session.save(compra);
		producto.setExistencia(cantidad+producto.getExistencia());
		session.update(producto);

		tran.commit(); 
		session.close();

	}

	public List<Compra> consultaFecha(String fecha) {
		List <Compra> lista = null;

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		lista=(List<Compra>)session.createQuery("FROM Compra WHERE fecha='"+fecha+"'").list();
		tran.commit(); 
		session.close();
		return lista;
	}

	public boolean consultaFechaExiste(String sfecha) {
		
		List <Compra> lista = null;

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		lista=(List<Compra>)session.createQuery("FROM Compra WHERE fecha='"+sfecha+"'").list();
		tran.commit(); 
		session.close();
		
		if(lista!=null && !lista.isEmpty())
			return true;
		else
			return false;
	}


}

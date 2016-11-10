package mx.tecno.dao;

import java.util.List;
import mx.tecno.dto.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductoDAO {
	
	
public void eliminarProducto(Producto producto){
		
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
	    session.delete(producto);
		tran.commit();
		session.close();
	}

	public void modificarProducto(Producto producto){


		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		session.update(producto);
		tran.commit();
		session.close();
	}

	
	
	public void guardarProducto(Producto producto){
		
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		session.save(producto);
		tran.commit();
		session.close();
	}
	
	public 	List <Producto> obtenerProductos() {
		List <Producto> lista = null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		lista=(List<Producto>)session.createQuery("FROM Producto").list();
		tran.commit(); 
		session.close();

		return lista;
	}

	public Producto findById(int id) {
		Producto producto = null;

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();		
		producto=(Producto) session.createQuery("FROM Producto WHERE idproducto="+id).uniqueResult();
		tran.commit(); 
		session.close();

		return producto;

	}
	
	

	public Producto obtenerProductoPorNomProducto(String nomProducto){

		Producto producto=null;

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();	
		producto= (Producto)session.createQuery("FROM Producto WHERE nombre='"+nomProducto+"'").uniqueResult();
		tran.commit(); 
		session.close();
		return producto;


	}

}

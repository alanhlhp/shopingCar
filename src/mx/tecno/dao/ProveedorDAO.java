package mx.tecno.dao;

import java.util.List;

import mx.tecno.dto.Proveedor;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProveedorDAO {
	
	
	
	public 	List <Proveedor> obtenerProveedores() {
		List <Proveedor> lista = null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		lista=(List<Proveedor>)session.createQuery("FROM Proveedor").list();
		tran.commit(); 
		session.close();
		return lista;
	}
	public void eliminarProveedor(Proveedor proveedor){

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		session.delete(proveedor);
		tran.commit();
		session.close();
	}

	public void modificarProveedor(Proveedor proveedor){


		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		session.update(proveedor);
		tran.commit();
		session.close();
	}



	public void guardarProveedor(Proveedor proveedor){

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		session.save(proveedor);
		tran.commit();
		session.close();
	}

	public Proveedor obtenerProveedorPorNomProveedor(String nomProveedor){

		Proveedor proveedor=null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		proveedor= (Proveedor)session.createQuery("FROM Proveedor WHERE nombre='"+nomProveedor+"'").uniqueResult();
		tran.commit(); 
		session.close();
		return proveedor;


	}


}

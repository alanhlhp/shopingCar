package mx.tecno.dao;

import java.util.List;
import mx.tecno.dto.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {


	public 	List <Usuario> obtenerUsuarios() {


		List <Usuario> lista = null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		lista=(List<Usuario>)session.createQuery("FROM Usuario").list();

		tran.commit(); 
		session.close();

		return lista;
	}

	public 	Usuario obtenerUsuariosbyId(int id) {


		Usuario usuario = null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		usuario=(Usuario)session.createQuery("FROM Usuario WHERE idusuario="+id).uniqueResult();

		tran.commit(); 
		session.close();

		return usuario;
	}

	public List<Usuario> obtenerUsuariosbyTipo(boolean b) {
		List<Usuario> usuariosTipo;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		usuariosTipo=(List)session.createQuery("FROM Usuario WHERE tipoUsuario="+b).list();
		tran.commit();
		session.close();
		return usuariosTipo;
	}

	public void eliminarUsuario(Usuario usuario){
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
	    session.delete(usuario);
		tran.commit();
		session.close();
	}

	public void modificarUsuario(Usuario usuario){


		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		session.update(usuario);
		tran.commit();
		session.close();
	}

	public Usuario obtenerUsuarioPorNomUsuario(String nomUsuario){

		Usuario usuario=null;
		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		usuario= (Usuario)session.createQuery("FROM Usuario WHERE usuario='"+nomUsuario+"'").uniqueResult();
		tran.commit(); 
		session.close();
		return usuario;


	}

	
	public void guardarUsuario(Usuario usuario){

		Session session = HibernateSingleton.getInstance().openSession();
		Transaction tran = session.beginTransaction();
		session.save(usuario);
		tran.commit();
		session.close();

	}



}

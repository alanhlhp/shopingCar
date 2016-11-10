package mx.tecno.business;

import java.util.List;

import mx.tecno.dao.UsuarioDAO;
import mx.tecno.dto.Usuario;


public class LogicaUsuario {

	public Usuario cargarUsuarios(String nomUsuario , String  contraseña){
		
		List<Usuario> listaUsuarios= new UsuarioDAO().obtenerUsuarios();


		for(Usuario usuario:listaUsuarios){

			if(usuario.getUsuario().equalsIgnoreCase(nomUsuario) && 
					usuario.getPassword().equalsIgnoreCase(contraseña))
				return usuario;
		}

		return null;


	}

	

	public List<Usuario> cargarUsuariosbyTipo(boolean b) {
		List<Usuario> listaUsuarios = new UsuarioDAO().obtenerUsuariosbyTipo(false);
		return listaUsuarios;
	}
	
	public void insertarUsuario(Usuario usuario){

		new UsuarioDAO().guardarUsuario(usuario);
	}

	
	public void borrarUsuario(Usuario usuario){
		
		new UsuarioDAO().eliminarUsuario(usuario);
	}

	public void  actualizarUsuario(Usuario usuario){

		new UsuarioDAO().modificarUsuario(usuario);
	}

	public List<Usuario> cargarUsuarios(){

		return new UsuarioDAO().obtenerUsuarios();
	}

	public Usuario obtenerUsuarioPorNomUsuario(String usuario){

		return new UsuarioDAO().obtenerUsuarioPorNomUsuario(usuario);
	}

	public boolean validarPatronUsuario(String usuario){

		boolean patronCorrecto=false;

		for(int z=0;z<usuario.length();z++){
			if(!((usuario.charAt(z)>='0' &&  usuario.charAt(z)<='9') || 
					(usuario.charAt(z)>='a' &&  usuario.charAt(z)<='z') || 
					(usuario.charAt(z)>='A' &&  usuario.charAt(z)<='Z'))){
				patronCorrecto=true;
				break;


			}
		}
		return patronCorrecto;

	}




	public boolean validarUsuario(String nomUsuario , String  contraseña){

		List<Usuario> listaUsuarios= new UsuarioDAO().obtenerUsuarios();


		for(Usuario usuario:listaUsuarios){

			if(usuario.getUsuario().equalsIgnoreCase(nomUsuario) && 
					usuario.getPassword().equalsIgnoreCase(contraseña))
				return true;
		}

		return false;
	}

	public boolean validarUsuario(String nomUsuario){

		List<Usuario> listaUsuarios= new UsuarioDAO().obtenerUsuarios();


		for(Usuario usuario:listaUsuarios){

			if(usuario.getUsuario().equalsIgnoreCase(nomUsuario))
				return true;
		}

		return false;
	}


}

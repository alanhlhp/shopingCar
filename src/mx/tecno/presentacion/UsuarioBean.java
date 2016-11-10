package mx.tecno.presentacion;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.tecno.business.LogicaUsuario;
import mx.tecno.dto.Usuario;

@ManagedBean(name="usuario")
@SessionScoped

public class UsuarioBean {

	private String nombre;
	private String contra;


	public UsuarioBean(){
	}

	public String validarUsuario(){

		boolean existeUsuario= new LogicaUsuario().validarUsuario(nombre, contra);

		if(existeUsuario==true){
			Usuario usuario= new LogicaUsuario().cargarUsuarios(nombre, contra);
			Login.setLogin(usuario);
			if(usuario.isTipoUsuario())
			{
				return "Administrador.xhtml"; 
			}
			else{
				return "Consulta_productos.xhtml";  
			}
		}
		else
		{
			return "index.xhtml";
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

}

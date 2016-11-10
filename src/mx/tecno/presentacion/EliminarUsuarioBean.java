package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.tecno.business.LogicaUsuario;
import mx.tecno.dto.Usuario;


@ManagedBean(name="eliminarUsuario")
@SessionScoped
public class EliminarUsuarioBean {

	
	
	private Usuario usuario= new Usuario();

	public EliminarUsuarioBean(){
		
	}
	
	public void validarExisteUsuario(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String usuario= arg2.toString();

			if(new LogicaUsuario().validarPatronUsuario(usuario)) {

				FacesMessage msg = new FacesMessage("Numeros y Letras");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}



			if(!(new LogicaUsuario().validarUsuario(usuario))){

				FacesMessage msg = new FacesMessage("Usuario No Existe");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		}
	}
	
	
	public String eliminarUsuario(){
		
		usuario = new LogicaUsuario().obtenerUsuarioPorNomUsuario(usuario.getUsuario());
		new LogicaUsuario().borrarUsuario(usuario);
		this.usuario.setUsuario(null);
		return "EliminarUsuario.xhtml";
	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}

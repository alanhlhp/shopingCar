package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


import mx.tecno.business.LogicaUsuario;
import mx.tecno.dto.Usuario;

@ManagedBean(name="altaUsuario")
@SessionScoped

public class AltaUsuarioBean {

	protected Usuario usuario = new Usuario();
	protected String tipoUsuario="";


	public AltaUsuarioBean(){

	}


	public void validarRepeticionUsuario(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String usuario= arg2.toString();


			if(new LogicaUsuario().validarPatronUsuario(usuario)) {

					FacesMessage msg = new FacesMessage("Numeros y Letras");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);}

			

			if(new LogicaUsuario().validarUsuario(arg2.toString())){

				FacesMessage msg = new FacesMessage("Usuario Repetido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} 

	}

	public String limpiarAltaUsuario(){
		this.usuario= new Usuario();
		this.tipoUsuario="";

		return "AltaUsuario.xhtml";
	}

	public String agregarUsuario(){

		if(this.tipoUsuario.equals("1"))
			usuario.setTipoUsuario(true);
		else
			usuario.setTipoUsuario(false);

		new LogicaUsuario().insertarUsuario(this.usuario);


		limpiarAltaUsuario();
		return "AltaUsuario.xhtml";


	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}

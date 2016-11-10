package mx.tecno.presentacion;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;




import mx.tecno.business.LogicaUsuario;
import mx.tecno.dto.Usuario;

@ManagedBean(name="modificarUsuario")
@SessionScoped
public class ModificarUsuarioBean extends AltaUsuarioBean{


	private String nomUsuario;	
	private boolean usuarioDesabilitado=false;

	public ModificarUsuarioBean(){

	}




	public String buscarUsuario( )  {		

		if(this.nomUsuario!=null){

			Usuario usuarioObtenido= new LogicaUsuario().obtenerUsuarioPorNomUsuario(nomUsuario);
			this.usuario=usuarioObtenido;

			if(usuarioObtenido.isTipoUsuario())
				this.tipoUsuario="1";
			else
				this.tipoUsuario="0";	
			setUsuarioDesabilitado(true);

		}
		
		
		return "ModificarUsuario.xhtml";
	}


	public String limpiarModificarUsuario(){
		this.usuario= new Usuario();
		this.tipoUsuario="";
		this.nomUsuario="";
		setUsuarioDesabilitado(false);
		return "ModificarUsuario.xhtml";
	}

	
	
	public String modificarUsuario(){

		if(this.tipoUsuario.equals("1"))
			usuario.setTipoUsuario(true);
		else
			usuario.setTipoUsuario(false);

		new LogicaUsuario().actualizarUsuario(this.usuario);
		setUsuarioDesabilitado(false);
		return limpiarModificarUsuario();
	}



	

	public void validarRepeticionUsuario2(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String usuario= arg2.toString();


			if(new LogicaUsuario().validarPatronUsuario(usuario)) {

					FacesMessage msg = new FacesMessage("Numeros y Letras");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);}

			
			if((new LogicaUsuario().validarUsuario(usuario)) && (!(this.usuario.getUsuario().equalsIgnoreCase(usuario)))){

				FacesMessage msg = new FacesMessage("Usuario Repetido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} 

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





	public String getNomUsuario() {
		return nomUsuario;
	}


	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}




	public boolean isUsuarioDesabilitado() {
		return usuarioDesabilitado;
	}




	public void setUsuarioDesabilitado(boolean usuarioDesabilitado) {
		this.usuarioDesabilitado = usuarioDesabilitado;
	}


}

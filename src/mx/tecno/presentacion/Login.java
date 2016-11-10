package mx.tecno.presentacion;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.tecno.dto.Usuario;

@ManagedBean(name="log")
@SessionScoped
public class Login{

	private static Usuario login;
	public Login(){
	}
	public static Usuario getLogin() {
		return login;
	}
	public static void setLogin(Usuario login) {
		Login.login = login;
	}
		
		public void regresar() throws IOException{
			if(Login.getLogin()==null)
			{
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect( "index.xhtml" );			
			}
			else
				if(Login.getLogin().isTipoUsuario()==false)
				{
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect( "Consulta_productos.xhtml" );
				}
		}

}

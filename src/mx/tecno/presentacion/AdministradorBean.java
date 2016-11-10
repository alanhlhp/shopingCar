package mx.tecno.presentacion;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="admin")
@SessionScoped
public class AdministradorBean {

	public AdministradorBean(){
	}

	public void load(){
		try{
			FacesContext contex = FacesContext.getCurrentInstance();
			contex.getExternalContext().redirect( "/index.xhtml" );
		}catch(  Exception e ){}

	}

}

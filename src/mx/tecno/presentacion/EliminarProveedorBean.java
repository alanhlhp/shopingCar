package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.tecno.business.LogicaProveedor;
import mx.tecno.dto.Proveedor;

@ManagedBean(name="eliminarProveedor")
@SessionScoped
public class EliminarProveedorBean {

	private Proveedor proveedor= new Proveedor();
	
	
	public EliminarProveedorBean(){
		
	}
	
	
	public void validarExisteProveedor(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String proveedor= arg2.toString();

			if(new LogicaProveedor().validarPatronProveedor(proveedor)) {

				FacesMessage msg = new FacesMessage("Solo Letras");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}



			if(!(new LogicaProveedor().validarProveedor(proveedor))){

				FacesMessage msg = new FacesMessage("Proveedor No Existe");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		}
	}
	
	
	public String eliminarProveedor(){
		
		proveedor = new LogicaProveedor().obtenerProveedorPorNomProveedor(proveedor.getNombre());
		new LogicaProveedor().borrarProveedor(proveedor);
		this.proveedor.setNombre(null);
		return "EliminarProveedor.xhtml";
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	

	
}

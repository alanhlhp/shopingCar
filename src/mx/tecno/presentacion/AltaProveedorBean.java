package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


import mx.tecno.business.LogicaProveedor;
import mx.tecno.dto.Proveedor;

@ManagedBean(name="altaProveedor")
@SessionScoped

public class AltaProveedorBean {

	protected Proveedor proveedor= new Proveedor();
	
	public AltaProveedorBean(){
		
	}

	public String agregarProveedor(){
		
		new LogicaProveedor().agregarProveedor(proveedor);
		return limpiarAltaProveedor();
	}
	
	public String limpiarAltaProveedor(){
		
		proveedor= new Proveedor();
		return "AltaProveedor.xhtml";
	}
	
	
	public void validarRepeticionProveedor(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String proveedor = arg2.toString();


			if(new LogicaProveedor().validarPatronProveedor(proveedor)) {

					FacesMessage msg = new FacesMessage("Solo Letras");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);}

			

			if(new LogicaProveedor().validarProveedor(arg2.toString())){

				FacesMessage msg = new FacesMessage("Proveedor Repetido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} 

	}
	
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}

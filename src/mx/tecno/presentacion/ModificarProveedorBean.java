package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.tecno.business.LogicaProveedor;
import mx.tecno.dto.Proveedor;

@ManagedBean(name="modificarProveedor")
@SessionScoped

public class ModificarProveedorBean extends AltaProveedorBean{

	
	private String nombreProveedor;
	private boolean proveedorDesabilitado=false;
	

	public ModificarProveedorBean(){
		
	}
	
	public String buscarProveedor( )  {		

		if(this.nombreProveedor !=null){

			Proveedor proveedorObtenido= new LogicaProveedor().obtenerProveedorPorNomProveedor(nombreProveedor);
			this.proveedor=proveedorObtenido;
			setProveedorDesabilitado(true);
		}
	
		
		return "ModificarProveedor.xhtml";
	}

	
	 public String modificarProveedor(){
		 new LogicaProveedor().actualizarProveedor(proveedor);
		 return limpiarModificarProveedor();
	 }
	
	public String limpiarModificarProveedor(){
		
		this.proveedor= new Proveedor();
		this.nombreProveedor=null;
		setProveedorDesabilitado(false);
		return "ModificarProveedor.xhtml";
		
	}

	

	public void validarRepeticionProveedor2(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String proveedor= arg2.toString();


			if(new LogicaProveedor().validarPatronProveedor(proveedor)) {

					FacesMessage msg = new FacesMessage("Solo Letras");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);}

			
			if((new LogicaProveedor().validarProveedor(proveedor)) && (!(this.proveedor.getNombre().equalsIgnoreCase(proveedor)))){

				FacesMessage msg = new FacesMessage("Proveedor Repetido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} 

	}
	public String validarExisteProveedor(FacesContext arg0, UIComponent arg1, Object arg2)  {

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
return "ModificarProveedor.xhtml";
	} 
	
	public String getNombreProveedor() {
		return nombreProveedor;
	}


	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}


	public boolean isProveedorDesabilitado() {
		return proveedorDesabilitado;
	}


	public void setProveedorDesabilitado(boolean proveedorDesabilitado) {
		this.proveedorDesabilitado = proveedorDesabilitado;
	}



}

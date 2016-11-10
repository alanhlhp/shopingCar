package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.tecno.business.LogicaProducto;
import mx.tecno.dto.Producto;

@ManagedBean(name="altaProducto")
@SessionScoped

public class AltaProductoBean {

	protected Producto producto = new Producto();
	protected String precio;
	protected String existencia;

	
	public AltaProductoBean(){
		
	}
	
	public  String agregarProducto(){
		
		producto.setPrecioUnitario(Float.parseFloat(precio));
		producto.setExistencia(Integer.parseInt(existencia));
		new LogicaProducto().agregarProducto(producto);
		
		return limpiarAltaProducto();
	}
		
	
	public String limpiarAltaProducto(){
		this.producto= new Producto();
		this.precio="";
		this.existencia="";
		
		return "AltaProducto.xhtml";
	}
	
	
	
	public void validarRepeticionProducto(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String producto= arg2.toString();


			if(new LogicaProducto().validarPatronProducto(producto)) {

					FacesMessage msg = new FacesMessage("Solo Letras");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);}

			

			if(new LogicaProducto().validarProducto(arg2.toString())){

				FacesMessage msg = new FacesMessage("Producto Repetido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} 

	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	public String getPrecio() {
		return precio;
	}



	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getExistencia() {
		return existencia;
	}

	public void setExistencia(String existencia) {
		this.existencia = existencia;
	}

	
	
	
	
}

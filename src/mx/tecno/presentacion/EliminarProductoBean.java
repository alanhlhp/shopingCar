package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.tecno.business.LogicaProducto;
import mx.tecno.dto.Producto;


@ManagedBean(name="eliminarProducto")
public class EliminarProductoBean {

	private Producto producto= new Producto();

	public EliminarProductoBean(){
		
	}
	
	public void validarExisteProducto(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String producto= arg2.toString();

			if(new LogicaProducto().validarPatronProducto(producto)) {

				FacesMessage msg = new FacesMessage("Solo Letras");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}



			if(!(new LogicaProducto().validarProducto(producto))){

				FacesMessage msg = new FacesMessage("Producto No Existe");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		}
	}
	
	
	public String eliminarProducto(){
		
		producto = new LogicaProducto().obtenerProductoPorNombreProducto(producto.getNombre());
		new LogicaProducto().borrarProducto(producto);
		this.producto.setNombre(null);
		return "EliminarProducto.xhtml";
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	

	
	
}

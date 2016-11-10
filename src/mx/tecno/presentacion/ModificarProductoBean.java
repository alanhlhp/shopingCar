package mx.tecno.presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mx.tecno.business.LogicaProducto;
import mx.tecno.dto.Producto;


@ManagedBean(name="modificarProducto")
@SessionScoped

public class ModificarProductoBean  extends AltaProductoBean{
  
	private String nombreProducto;
	private boolean productoDesabilitado=false;
	
	
	
	
	public ModificarProductoBean(){
		
	}

	
	
	public String buscarProducto( )  {		

		if(this.nombreProducto !=null){

			Producto productoObtenido= new LogicaProducto().obtenerProductoPorNombreProducto(nombreProducto);
			this.producto=productoObtenido;
			this.existencia=String.valueOf( productoObtenido.getExistencia());
			this.precio=String.valueOf(productoObtenido.getPrecioUnitario());
			
			setProductoDesabilitado(true);
		}
	
		
		return "ModificarProducto.xhtml";
	}

	
	 public String modificarProducto(){
		 this.producto.setExistencia(Integer.parseInt(existencia));
		 this.producto.setPrecioUnitario(Float.parseFloat(precio));
		 new LogicaProducto().actualizarProducto(producto);
		 
		 return limpiarModificarProducto();
	 }
	
	public String limpiarModificarProducto(){
		this.precio=null;
		this.existencia=null;
		this.producto= new Producto();
		this.nombreProducto=null;
		setProductoDesabilitado(false);
		return "ModificarProducto.xhtml";
		
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void validarRepeticionProducto2(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String Producto= arg2.toString();


			if(new LogicaProducto().validarPatronProducto(Producto)) {

					FacesMessage msg = new FacesMessage("Solo Letras");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);}

			
			if((new LogicaProducto().validarProducto(Producto)) && (!(this.producto.getNombre().equalsIgnoreCase(Producto)))){

				FacesMessage msg = new FacesMessage("Producto Repetido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		} 

	}

	public void validarExisteProducto(FacesContext arg0, UIComponent arg1, Object arg2)  {

		if (arg2.toString()!=null) {

			String Producto= arg2.toString();

			if(new LogicaProducto().validarPatronProducto(Producto)) {

				FacesMessage msg = new FacesMessage("Solo Letras");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}



			if(!(new LogicaProducto().validarProducto(Producto))){

				FacesMessage msg = new FacesMessage("Producto No Existe");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		}

	} 


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}




	public boolean isProductoDesabilitado() {
		return productoDesabilitado;
	}




	public void setProductoDesabilitado(boolean productoDesabilitado) {
		this.productoDesabilitado = productoDesabilitado;
	}
}

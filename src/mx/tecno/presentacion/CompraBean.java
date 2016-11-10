package mx.tecno.presentacion;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import mx.tecno.business.LogicaCompra;
import mx.tecno.business.LogicaProducto;
import mx.tecno.dto.Producto;


@ManagedBean(name="compra")
@SessionScoped
public class CompraBean {
	private int idProducto;
	private int cantidad;
	private Date fecha;
	private List<Producto> listaProducto;
	private List<SelectItem> listaItemProducto = new ArrayList<SelectItem>();
	private String compraHecha;

	public CompraBean(){
		cantidad=0;
		SelectItem producto = new SelectItem();
		listaProducto = new LogicaProducto().cargarProductos();
		for(Producto p: listaProducto){
			producto = new SelectItem(p.getIdproducto(),p.getIdproducto().toString());
			listaItemProducto.add(producto);
		}
	}


	public void cargaProducto (ValueChangeEvent event){

		if(event.getNewValue()!=null)
		{
			int id = (Integer) event.getNewValue();
			//String nombre = (String) event.getNewValue();
			for (Producto p : listaProducto)
			{
				if(id==p.getIdproducto())
					setIdProducto(id);
			}
		}
	}

	public void crearCompra(){
		if(cantidad!=0 && Login.getLogin()!=null){
			{	new LogicaCompra().nuevaCompra(cantidad, fecha, idProducto,Login.getLogin());
			setCompraHecha("Compra Hecha");
			}
		}
	}

	public String limpiar(){
		cantidad=0;
		compraHecha="";
		fecha=null;
		return "Compra.xhtml";
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<SelectItem> getListaItemProducto() {
		return listaItemProducto;
	}

	public void setListaItemProducto(List<SelectItem> listaItemProducto) {
		this.listaItemProducto = listaItemProducto;
	}


	public String getCompraHecha() {
		return compraHecha;
	}


	public void setCompraHecha(String compraHecha) {
		this.compraHecha = compraHecha;
	}
}

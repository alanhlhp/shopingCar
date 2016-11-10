package mx.tecno.presentacion;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import mx.tecno.business.LogicaConsulta;
import mx.tecno.dto.Producto;

@ManagedBean(name="consulta")
@SessionScoped
public class ConsultaBean {

	private String categoria;
	private List<Producto> listaProductos;
	private List<SelectItem> listItem = new ArrayList<SelectItem>();
	private int idproducto;
	private Producto producto;
	private HtmlDataTable tabla;
	
	public ConsultaBean(){
		SelectItem item = new SelectItem("","");
		listItem.add(item);
		listaProductos = new LogicaConsulta().cargarProductos();
		for(Producto productos: listaProductos){
			item = new SelectItem(productos.getCategoria(),productos.getCategoria());
			listItem.add(item);
		}
		
		listaProductos.clear();
	}
	
	public void carga(ValueChangeEvent event){
		
		if(event.getNewValue()!=null)
		{
			listaProductos.clear();
			String categoria = (String) event.getNewValue();
			listaProductos = new LogicaConsulta().cargarProducto(categoria);
		}
	}
	
	public void seleccionLista(){
		producto = (Producto) tabla.getRowData();
		this.setIdproducto(producto.getIdproducto());
	}
	
	public String regresoConsultas(){
		return "Consulta_productos.xhtml";
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<SelectItem> getListItem() {
		return listItem;
	}

	public void setListItem(List<SelectItem> listItem) {
		this.listItem = listItem;
	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public HtmlDataTable getTabla() {
		return tabla;
	}

	public void setTabla(HtmlDataTable tabla) {
		this.tabla = tabla;
	}

	
}

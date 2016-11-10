package mx.tecno.presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import mx.tecno.business.LogicaProducto;
import mx.tecno.dto.Producto;

@ManagedBean(name="lista")
@SessionScoped

public class SeleccionListaBean {
	
	private int idproducto;
	private List<Producto> lista = new ArrayList<Producto>();
	private Producto producto;
	
	private HtmlDataTable tabla;
	
	public SeleccionListaBean(){
	}
	
	public void cargarLista(){
		producto= new LogicaProducto().cargarProductoId(idproducto);
	}
	
	public void seleccionLista(){
		producto = (Producto) tabla.getRowData();
		this.idproducto=producto.getIdproducto();
	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public List<Producto> getLista() {
		return lista;
	}

	public void setLista(List<Producto> lista) {
		this.lista = lista;
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

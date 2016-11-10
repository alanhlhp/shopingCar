package mx.tecno.presentacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import mx.tecno.business.LogicaProducto;
import mx.tecno.business.LogicaUsuario;
import mx.tecno.business.LogicaVenta;
import mx.tecno.dto.Producto;
import mx.tecno.dto.Usuario;

@ManagedBean(name="venta")
@SessionScoped
public class VentaBean {

	private Date fecha;
	private Integer cantidad;
	private List<Usuario> listaUsuario;
	private List<SelectItem> listaItemUsuario = new ArrayList<SelectItem>();
	private List<Producto> listaProducto;
	private List<SelectItem> listaItemProducto = new ArrayList<SelectItem>();
	private String ventaHecha;
	private int idProducto;
	private int idUsuario;
	

	public VentaBean(){
		cantidad=0;
		SelectItem producto = new SelectItem();
		SelectItem usuario = new SelectItem();
		listaProducto = new LogicaProducto().cargarProductos();
		for(Producto p: listaProducto){
			producto = new SelectItem(p.getIdproducto(),p.getIdproducto().toString());
			listaItemProducto.add(producto);
		}
		listaUsuario = new LogicaUsuario().cargarUsuariosbyTipo(false);
		for(Usuario u: listaUsuario){
			usuario = new SelectItem(u.getIdusuario(),u.getNombre()+" "+u.getApellido()+ " | id: "+u.getIdusuario());
			listaItemUsuario.add(usuario);
		}
	}

	public void cargaProducto (ValueChangeEvent event){

		if(event.getNewValue()!=null)
		{
			int id = (Integer) event.getNewValue();
			for (Producto p : listaProducto)
			{
				if(id==p.getIdproducto())
					setIdProducto(id);
			}
		}
	}

	public void cargaUsuario(ValueChangeEvent event){
		if(event.getNewValue()!=null)
		{
			int id = (Integer) event.getNewValue();
			for(Usuario u : listaUsuario){
				if(id==u.getIdusuario())
					setIdUsuario(id);
			}
		}
	}
	
	public void consultaVenta(){
		if(cantidad!=0){
			Producto producto = new LogicaProducto().cargarProductoId(idProducto);
			if(producto.getExistencia()>=cantidad && !getCantidad().equals(null))
			{
				new LogicaVenta().crearNuevaCompra(idUsuario, producto, fecha, cantidad);
				setVentaHecha("Venta Realizada");
			}
			else
			{
				setVentaHecha("La venta no puede realizarse, no hay suficienes porductos en existencia");
			}
		}
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<SelectItem> getListaItemUsuario() {
		return listaItemUsuario;
	}

	public void setListaItemUsuario(List<SelectItem> listaItemUsuario) {
		this.listaItemUsuario = listaItemUsuario;
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

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getVentaHecha() {
		return ventaHecha;
	}

	public void setVentaHecha(String ventaHecha) {
		this.ventaHecha = ventaHecha;
	}

}

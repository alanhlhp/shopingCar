package mx.tecno.presentacion;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.tecno.business.LogicaVenta;
import mx.tecno.dto.ConsultaVenta;


@ManagedBean(name="consulta_venta")
@SessionScoped
public class ConsultaVentaBean {
	
	private Date fecha;
	private List<ConsultaVenta> lista;
	private String mensajeConsulta;
	
	public ConsultaVentaBean(){
	}
	
	public void consultaVenta(){
		if(fecha!=null){
			try{
				boolean existeVenta = new LogicaVenta().consultaVentaExiste(fecha);
				if(existeVenta==true)
				{
					lista=new LogicaVenta().consultaVenta(fecha);
					mensajeConsulta="";
				}
				else
				{
					mensajeConsulta="No existen Ventas";
					lista.clear();
				}

			}catch(NullPointerException e){
			}
		}
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<ConsultaVenta> getLista() {
		return lista;
	}

	public void setLista(List<ConsultaVenta> lista) {
		this.lista = lista;
	}

	public String getMensajeConsulta() {
		return mensajeConsulta;
	}

	public void setMensajeConsulta(String mensajeConsulta) {
		this.mensajeConsulta = mensajeConsulta;
	}
	
	

}

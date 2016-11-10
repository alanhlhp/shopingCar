package mx.tecno.presentacion;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.tecno.business.LogicaCompra;
import mx.tecno.dto.ConsultaCompra;

@ManagedBean(name="consulta_compra")
@SessionScoped
public class ConsultaCompraBean {

	private Date fecha;
	private List<ConsultaCompra> lista;
	private String mensajeConsulta;

	public ConsultaCompraBean(){
	}

	public void cargaCompra(){
		if(fecha!=null){
			try{
				boolean existeCompra = new LogicaCompra().consultaCompraExiste(fecha);
				if(existeCompra==true)
				{				
					lista = new LogicaCompra().consultaCompra(fecha);
					mensajeConsulta="";
				}
				else
				{
					mensajeConsulta="No existen Compras";
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

	public List<ConsultaCompra> getLista() {
		return lista;
	}

	public void setLista(List<ConsultaCompra> lista) {
		this.lista = lista;
	}

	public String getMensajeConsulta() {
		return mensajeConsulta;
	}

	public void setMensajeConsulta(String mensajeConsulta) {
		this.mensajeConsulta = mensajeConsulta;
	}
}

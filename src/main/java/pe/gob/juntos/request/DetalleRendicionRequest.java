package pe.gob.juntos.request;

import java.util.Date;
import java.util.List;

import pe.gob.juntos.util.FechaUtil;

public class DetalleRendicionRequest {

	private Long idDetalleRendicion;
	private Long idRendicion;
	private Long idEspecifica;
	private Long idClase;
	private Date fecha;
	private String serieRecibo;
	private String fechatxt;
	private String numeroRecibo;
	private String rucEmpresa =" ";
	private String razonSocial=" ";
	private float importe;
	private String documentoReferencia;
	private int cantidadFolios;
	private String detalle;
	private boolean eliminar;
	private List<RutaDetalleRendicionRequest> listadoRutaDetalleRendicion;
	private List<String> listadoDocumentoArchivo;
	private RendicionAdministradoRequest rendicionReq;
	
	
	public Long getIdDetalleRendicion() {
		return idDetalleRendicion;
	}
	public void setIdDetalleRendicion(Long idDetalleRendicion) {
		this.idDetalleRendicion = idDetalleRendicion;
	}
	public Long getIdRendicion() {
		return idRendicion;
	}
	public void setIdRendicion(Long idRendicion) {
		this.idRendicion = idRendicion;
	}
	public Long getIdEspecifica() {
		return idEspecifica;
	}
	public void setIdEspecifica(Long idEspecifica) {
		this.idEspecifica = idEspecifica;
	}
	public Long getIdClase() {
		return idClase;
	}
	public void setIdClase(Long idClase) {
		this.idClase = idClase;
	}
	public Date getFecha() {
		if(this.getFechatxt()!=null && this.getFechatxt().length()==10) {
			this.fecha = FechaUtil.ConvertirCadenaFechaDDMMYYYY(this.fechatxt);
		}
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getSerieRecibo() {
		return serieRecibo;
	}
	public void setSerieRecibo(String serieRecibo) {
		this.serieRecibo = serieRecibo;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public String getRucEmpresa() {
		return rucEmpresa;
	}
	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public String getDocumentoReferencia() {
		return documentoReferencia;
	}
	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}
	public int getCantidadFolios() {
		return cantidadFolios;
	}
	public void setCantidadFolios(int cantidadFolios) {
		this.cantidadFolios = cantidadFolios;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public List<RutaDetalleRendicionRequest> getListadoRutaDetalleRendicion() {
		return listadoRutaDetalleRendicion;
	}
	public void setListadoRutaDetalleRendicion(List<RutaDetalleRendicionRequest> listadoRutaDetalleRendicion) {
		this.listadoRutaDetalleRendicion = listadoRutaDetalleRendicion;
	}
	public String getFechatxt() {
		return fechatxt;
	}
	public void setFechatxt(String fechatxt) {
		this.fechatxt = fechatxt;
	}
	
	public boolean isEliminar() {
		return eliminar;
	}
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	public List<String> getListadoDocumentoArchivo() {
		return listadoDocumentoArchivo;
	}
	public void setListadoDocumentoArchivo(List<String> listadoDocumentoArchivo) {
		this.listadoDocumentoArchivo = listadoDocumentoArchivo;
	}
	public RendicionAdministradoRequest getRendicionReq() {
		return rendicionReq;
	}
	public void setRendicionReq(RendicionAdministradoRequest rendicionReq) {
		this.rendicionReq = rendicionReq;
	}
	
	
	
}

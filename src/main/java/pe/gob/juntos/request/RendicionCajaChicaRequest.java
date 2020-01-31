package pe.gob.juntos.request;

import java.util.List;

public class RendicionCajaChicaRequest {
	
	private List<RendicionDetalleCajaChicaRequest> listadoRendicionCajachica;
	private String idSede;

	public List<RendicionDetalleCajaChicaRequest> getListadoRendicionCajachica() {
		return listadoRendicionCajachica;
	}

	public void setListadoRendicionCajachica(List<RendicionDetalleCajaChicaRequest> listadoRendicionCajachica) {
		this.listadoRendicionCajachica = listadoRendicionCajachica;
	}

	public String getIdSede() {
		return idSede;
	}

	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}
	
}

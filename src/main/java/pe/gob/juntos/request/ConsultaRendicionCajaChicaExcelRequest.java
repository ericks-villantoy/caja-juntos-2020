package pe.gob.juntos.request;

import java.util.List;

import pe.gob.juntos.response.DetalleRendicionCajaChicaResponse;

public class ConsultaRendicionCajaChicaExcelRequest {
	
	private List<DetalleRendicionCajaChicaResponse> listadoExcel;
	private String idSede;

	public List<DetalleRendicionCajaChicaResponse> getListadoExcel() {
		return listadoExcel;
	}

	public void setListadoExcel(List<DetalleRendicionCajaChicaResponse> listadoExcel) {
		this.listadoExcel = listadoExcel;
	}

	public String getIdSede() {
		return idSede;
	}

	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}
	
}

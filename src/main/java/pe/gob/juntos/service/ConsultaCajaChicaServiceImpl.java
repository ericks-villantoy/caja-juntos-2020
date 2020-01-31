package pe.gob.juntos.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.juntos.entity.DetalleRendicionCajaChica;
import pe.gob.juntos.request.ConsultaRendicionCajaChicaRequest;
import pe.gob.juntos.response.DetalleRendicionCajaChicaResponse;
import pe.gob.juntos.respository.DetalleRendicionCajaChicaRepository;

@Service("consultaCajaChicaService")
public class ConsultaCajaChicaServiceImpl implements ConsultaCajaChicaService {

	private static final Logger logger = LoggerFactory.getLogger(ConsultaCajaChicaServiceImpl.class);
	
	@Autowired
	private DetalleRendicionCajaChicaRepository detalleRendicionCajaChicaRepository;
	
	@Override
	public List<DetalleRendicionCajaChicaResponse> listarRendicionCajaChica(ConsultaRendicionCajaChicaRequest req) {
		
		List<DetalleRendicionCajaChicaResponse> listadoRendicionCajaChicaResp = new ArrayList<DetalleRendicionCajaChicaResponse>();
		try {
		
			Long idRendicionCajaChica = req.getIdRendicionCajaChica();
			
			List<DetalleRendicionCajaChica> listadoRendicionCajaChica = detalleRendicionCajaChicaRepository.listarRendicionCajaChica(idRendicionCajaChica);
			
			/*listadoRendicionCajaChica.forEach(t->{
				DetalleRendicionCajaChicaResponse resp = new DetalleRendicionCajaChicaResponse();
				BeanUtils.copyProperties(t, resp);
				listadoRendicionCajaChicaResp.add(resp);
				
			});*/
			
			for(DetalleRendicionCajaChica d: listadoRendicionCajaChica) {
				DetalleRendicionCajaChicaResponse resp = new DetalleRendicionCajaChicaResponse();
				BeanUtils.copyProperties(d, resp);
				listadoRendicionCajaChicaResp.add(resp);
			}		
			
			
		} catch (Exception e) {
			logger.error("error completo listarRendicionCajaChica: " + ExceptionUtils.getFullStackTrace(e));
		}
		
		return listadoRendicionCajaChicaResp;
		
	}

}

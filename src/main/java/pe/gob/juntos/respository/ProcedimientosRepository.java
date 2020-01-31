package pe.gob.juntos.respository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.juntos.response.RespuestaGeneralResponse;
import pe.gob.juntos.util.SeguridadUtil;

@Repository
@Transactional
public class ProcedimientosRepository  {

	private static final Logger logger = LoggerFactory.getLogger(ProcedimientosRepository.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	public RespuestaGeneralResponse flujoAprobacion(Long idRendicion, String estadoNuevo,String comentario) {

		logger.info("Iniciando flujoAprobacion");
		
		RespuestaGeneralResponse mResponse = new RespuestaGeneralResponse();

		String respuesta = "";
		
		try {
			StoredProcedureQuery storedProcedure = entityManager
					.createStoredProcedureQuery("CC_PR_LANZARACCIONWF");
			// seteamos parametros
			logger.info("seteando parametros sp CC_PR_LANZARACCIONWF");
			
			storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
			storedProcedure.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
			storedProcedure.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
			
			
			
			storedProcedure.setParameter(2, idRendicion);
			storedProcedure.setParameter(3, SeguridadUtil.obtenerCodigoUsuario());
			storedProcedure.setParameter(4, estadoNuevo);
			storedProcedure.setParameter(5, comentario);
			
			
			
			// ejecutamos SP
			logger.info("ejecutando CC_PR_LANZARACCIONWF");
			storedProcedure.execute();
			logger.info("termino ejecucion CC_PR_LANZARACCIONWF");
			respuesta = storedProcedure.getOutputParameterValue(1).toString();
			
			mResponse.setRespuesta(respuesta);
			

		} catch (Exception e) {
			logger.error("Errorx Completo flujoAprobacion: " + ExceptionUtils.getFullStackTrace(e));
			return mResponse;
		}
		
		return mResponse;
	}
	
}

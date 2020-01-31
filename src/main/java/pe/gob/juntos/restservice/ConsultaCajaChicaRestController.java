package pe.gob.juntos.restservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.BaseEncoding;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

import pe.gob.juntos.entity.Sede;
import pe.gob.juntos.request.ConsultaRendicionCajaChicaExcelRequest;
import pe.gob.juntos.request.ConsultaRendicionCajaChicaRequest;
import pe.gob.juntos.response.DetalleRendicionCajaChicaResponse;
import pe.gob.juntos.response.RespuestaExcelResponse;
import pe.gob.juntos.service.ComunService;
import pe.gob.juntos.service.ConsultaCajaChicaService;
import pe.gob.juntos.util.FechaUtil;

@RestController
public class ConsultaCajaChicaRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ConsultaCajaChicaRestController.class);
	
	@Autowired
	private ConsultaCajaChicaService consultaCajaChicaService;
	
	@Autowired
	private ComunService comunService;
	
	@Value("${sistema.download.path}")
	private String carpetaTemporalPDF;

	@CrossOrigin
	@RequestMapping(value="obtenerConsultaCajaChica", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DetalleRendicionCajaChicaResponse> obtenerConsultaCajaChica(@RequestBody ConsultaRendicionCajaChicaRequest req){	
		logger.info("obtenerConsultaCajaChica");
		List<DetalleRendicionCajaChicaResponse> listadoRendicionCajaChica = new ArrayList<DetalleRendicionCajaChicaResponse>();
		try {
			listadoRendicionCajaChica =  consultaCajaChicaService.listarRendicionCajaChica(req);
		} catch (Exception e) {
			logger.error("Errorx Completo obtenerConsultaCajaChica: " + ExceptionUtils.getFullStackTrace(e));
		}
		return listadoRendicionCajaChica;
	}

	@RequestMapping(value = "/ExportarExcelConsulta", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
	public void ExportarExcelConsulta(HttpServletRequest request, HttpServletResponse response) {

		String miXls = request.getParameter("miXls");
		String nombreXls = request.getParameter("nombreXls");
		
		byte[] miExcelDecode= BaseEncoding.base64().decode(miXls);
		
		ServletOutputStream out = null;
		
		try {
			   out = response.getOutputStream();
			   response.setContentType("application/vnd.ms-excel");
			   response.setHeader("Content-Disposition", "attachment; filename=\""+nombreXls+"\"");
			   
			   InputStream targetStream = ByteSource.wrap(miExcelDecode).openStream();
			   
			   int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = targetStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			   out.flush();
			   out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.info("error: " + e1.getMessage());
			e1.printStackTrace();
		}
		 finally {
			 
		 }
		

	}
	
	@RequestMapping(value = "generarExcelConsultaCajaChica", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaExcelResponse> generarExcelConsultaCajaChica(@RequestBody ConsultaRendicionCajaChicaExcelRequest req) {
		logger.info("Iniciando generarDJToPGHExcel");
		RespuestaExcelResponse mRespuesta = new RespuestaExcelResponse();

		List<DetalleRendicionCajaChicaResponse> listadoRendicionCajaChica =  req.getListadoExcel();

		try {
			
			
			
			Sede sede = comunService.obtenerSede(req.getIdSede());
			
			String rutaDirectorioPDF = carpetaTemporalPDF;

			HSSFWorkbook workbook = crearExcelConsulta(listadoRendicionCajaChica, sede.getDescripcionSede(), 1);
			String nombreXls = UUID.randomUUID().toString().substring(0, 8) + "_"
					+ FechaUtil.obtenerFechaActualCorrido() + ".xls";
			FileOutputStream fileOut = null;
			try {

				fileOut = new FileOutputStream(rutaDirectorioPDF + nombreXls);
				workbook.write(fileOut);
				fileOut.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			File miXls = new File(rutaDirectorioPDF + nombreXls);

			byte[] file = null;
			String mXlsBase64 = null;

			file = Files.toByteArray(miXls);
			mXlsBase64 = new String(BaseEncoding.base64().encode(file));

			logger.info("termino de envio base 64");

			mRespuesta.setmBase64Archivo(mXlsBase64);
			mRespuesta.setmNombreArchivo(nombreXls);

			this.eliminarArchivosLocalesPDF(rutaDirectorioPDF + nombreXls);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("error: " + e.getMessage());
			return new ResponseEntity<RespuestaExcelResponse>(mRespuesta, HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<RespuestaExcelResponse>(mRespuesta, HttpStatus.OK);
	}
	
	private HSSFWorkbook crearExcelConsulta(List<DetalleRendicionCajaChicaResponse> listado, String sedeReq,
			int numeroEnvio) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "DIRECTORIO NACIONAL DE EQUIPO TÉCNICO ULE");

		String[] headers = new String[] { "Nº", "FECHA", "CLASE", "SERIE", "NUMERO", "RUC / DNI", "NOMBRE", "DETALLE",
				"IMPORTE", "ESPECIFICA GASTO", "FOLIOS" };

		String[] subtitulos = new String[] { "Nº", "DIA", "MES", "AÑO" };

		String fechaSlash = FechaUtil.obtenerFechaActualDDMMYYYY();

		String[] fecha = fechaSlash.split("/");

		String[] datosSubtitulos = new String[] { String.valueOf(numeroEnvio), fecha[0].toString(), fecha[1].toString(),
				fecha[2].toString() };

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		CellStyle styleTitle = workbook.createCellStyle();

		styleTitle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		styleTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styleTitle.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle styleTitle2 = workbook.createCellStyle();
		styleTitle2.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		Font font2 = workbook.createFont();
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font2.setColor(IndexedColors.WHITE.getIndex());
		styleTitle2.setFont(font2);

		CellStyle styleAnexo = workbook.createCellStyle();
		styleAnexo.setAlignment(CellStyle.ALIGN_RIGHT);
		HSSFRow AnexoRow = sheet.createRow(0);
		HSSFCell cellAnexo = AnexoRow.createCell(0);
		cellAnexo.setCellValue("ANEXO N° 04");
		cellAnexo.setCellStyle(styleAnexo);

		CellStyle styleFormato = workbook.createCellStyle();
		styleFormato.setAlignment(CellStyle.ALIGN_RIGHT);
		HSSFRow formatoRow = sheet.createRow(1);
		HSSFCell cellFormato = formatoRow.createCell(0);
		cellFormato.setCellValue("JUNTOS-UA-TES-F-004");
		cellFormato.setCellStyle(styleFormato);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		HSSFRow tituloRow = sheet.createRow(2);
		HSSFCell cellTitulo = tituloRow.createCell(0);
		cellTitulo.setCellValue("PROGRAMA NACIONAL DE APOYO DIRECTO A LOS MAS POBRES - JUNTOS ");
		cellTitulo.setCellStyle(headerStyle);

		CellStyle styleFormatoT = workbook.createCellStyle();
		styleFormatoT.setAlignment(CellStyle.ALIGN_RIGHT);
		HSSFRow formatoTRow = sheet.createRow(3);
		HSSFCell cellFormatoT = formatoTRow.createCell(0);
		cellFormatoT.setCellValue("FORMATO DE RENDICIÓN DEL FONDO DE CAJA CHICA");
		cellFormatoT.setCellStyle(styleFormatoT);

		CellStyle styleSede = workbook.createCellStyle();
		styleSede.setAlignment(CellStyle.ALIGN_RIGHT);
		HSSFRow sedeRow = sheet.createRow(4);
		HSSFCell cellSede = sedeRow.createCell(0);
		cellSede.setCellValue("DE: " + sedeReq.toUpperCase());
		cellFormatoT.setCellStyle(styleFormatoT);

		CellStyle styleSubtitulo = workbook.createCellStyle();
		styleSubtitulo.setAlignment(CellStyle.ALIGN_RIGHT);
		HSSFRow subtituloRow = sheet.createRow(5);
		HSSFCell cellSubtitulo = subtituloRow.createCell(0);
		cellSubtitulo.setCellValue("UNIDAD DE ADMINISTRACIÓN");
		cellSubtitulo.setCellStyle(styleSubtitulo);

		CellStyle stylesubtitulo2 = workbook.createCellStyle();
		stylesubtitulo2.setAlignment(CellStyle.ALIGN_CENTER);
		HSSFRow cellSubtitulo2 = sheet.createRow(6);
		for (int i = 0; i < subtitulos.length; ++i) {
			String header = subtitulos[i];
			HSSFCell cell = cellSubtitulo2.createCell(i + 7);
			cell.setCellStyle(stylesubtitulo2);
			cell.setCellValue(header);
		}

		CellStyle stylesubtituloValor = workbook.createCellStyle();
		stylesubtituloValor.setAlignment(CellStyle.ALIGN_CENTER);
		HSSFRow cellSubtituloValor = sheet.createRow(7);
		for (int i = 0; i < datosSubtitulos.length; ++i) {
			String header = datosSubtitulos[i];
			HSSFCell cell = cellSubtituloValor.createCell(i + 7);
			cell.setCellStyle(stylesubtituloValor);
			cell.setCellValue(header);
		}

		CellRangeAddress cellMerge = new CellRangeAddress(0, 0, 0, 10);
		CellRangeAddress cellMerge1 = new CellRangeAddress(1, 1, 0, 10);
		CellRangeAddress cellMerge2 = new CellRangeAddress(2, 2, 0, 10);
		CellRangeAddress cellMerge3 = new CellRangeAddress(3, 3, 0, 10);
		CellRangeAddress cellMerge4 = new CellRangeAddress(4, 4, 0, 10);
		CellRangeAddress cellMerge5 = new CellRangeAddress(5, 5, 0, 5);

		sheet.addMergedRegion(cellMerge);
		sheet.addMergedRegion(cellMerge1);
		sheet.addMergedRegion(cellMerge2);
		sheet.addMergedRegion(cellMerge3);
		sheet.addMergedRegion(cellMerge4);
		sheet.addMergedRegion(cellMerge5);

		// Aqui pongo las cabeceras del reporte
		HSSFRow headerRow = sheet.createRow(8);
		for (int i = 0; i < headers.length; ++i) {
			String header = headers[i];
			HSSFCell cell = headerRow.createCell(i);
			cell.setCellStyle(styleTitle);
			cell.setCellValue(header);
		}

		int i = 9;
		int reg = 1;
		for (DetalleRendicionCajaChicaResponse vista : listado) {
			HSSFRow dataRow = sheet.createRow(i + 1);

			dataRow.createCell(0).setCellValue(reg);
			dataRow.createCell(1).setCellValue(
					FechaUtil.ConvertirFechaDDMMYYYY(vista.getRendicionCajaChica().getFechaRendicionCajaChica()));
			dataRow.createCell(2).setCellValue(vista.getDetalleRendicion().getClase().getDescripcionClase());
			dataRow.createCell(3).setCellValue(vista.getDetalleRendicion().getSerieRecibo());
			dataRow.createCell(4).setCellValue(vista.getDetalleRendicion().getNumeroRecibo());
			dataRow.createCell(5).setCellValue(vista.getDetalleRendicion().getRucEmpresa());
			dataRow.createCell(6).setCellValue(vista.getDetalleRendicion().getRazonSocial());
			dataRow.createCell(7).setCellValue(vista.getDetalleRendicion().getDetalle());
			//dataRow.createCell(8).setCellValue(vista.getDetalleRendicion().getImporte().toString());
			dataRow.createCell(8).setCellValue(vista.getDetalleRendicion().getImporte());
			dataRow.createCell(9).setCellValue(vista.getDetalleRendicion().getEspecifica().getDescripcionEspecifica());
			dataRow.createCell(10).setCellValue(vista.getDetalleRendicion().getCantidadFolios());
			reg++;

			i++;
		}

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		sheet.autoSizeColumn(8);
		sheet.autoSizeColumn(9);
		sheet.autoSizeColumn(10);
		sheet.autoSizeColumn(11);

		CellUtil.setAlignment(cellTitulo, workbook, CellStyle.ALIGN_CENTER);

		return workbook;
	}

	private void eliminarArchivosLocalesPDF(String rutaLocalArchivo) {

		File mArchivo = new File(rutaLocalArchivo);
		try {
			mArchivo.delete();
			mArchivo.deleteOnExit();
			System.out.println("Eliminado");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

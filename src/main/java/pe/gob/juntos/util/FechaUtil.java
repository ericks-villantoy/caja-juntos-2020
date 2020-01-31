package pe.gob.juntos.util;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FechaUtil
{
	static Logger logger = LoggerFactory.getLogger(FechaUtil.class);
	public static final String FORMATO_yyyyMMdd = "yyyy/MM/dd";
	public static final String FORMATO_ddMMyyyy = "dd/MM/yyyy";
	public static final String FORMATO_ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMATO_ddMMyyyyHHmm = "dd/MM/yyyy HH:mm";
	public static final String FORMATO_HHmmss = "HH:mm:ss";
	public static final String FORMATO_HHmm = "HH:mm";
	
	public static final String FORMATO_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String FORMATO_dd_MM_yyyy = "dd-MM-yyyy";
	
	public static String FormateaFechaATextoEspaniol(Date mFecha)
	{
		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("es", "ES"));
		   return formateador.format(mFecha);
	}
	
	public static Date FormatearFechaDDMMYYYY(Date mFecha)
	{
		String mFechaFormato = ConvertirFechaDDMMYYYY(mFecha);
		return ConvertirCadenaFechaDDMMYYYY(mFechaFormato);
	}
	
	public static Date FormatearFechaDDMMYYYYHHmmss(Date mFecha)
	{
		String mFechaFormato = ConvertirFechaDDMMYYYYHHmmss(mFecha);
		return ConvertirCadenaFechaDDMMYYYYHHmmss(mFechaFormato);
	}
	
	public static Date FormatearFechaHHmm(Date mFecha)
	{
		String mFechaFormato = ConvertirFechaHHmmss(mFecha);
		return ConvertirCadenaFechaHHmm(mFechaFormato);
	}

	public static Integer ConvertirFechaEnteroDD(Date mFecha)
	{
		return new Integer(ConvertirFechaDD(mFecha));
	}

	public static Integer ConvertirFechaEnteroHH(Date mFecha)
	{
		return new Integer(ConvertirFechaHH(mFecha));
	}

	public static Integer ConvertirFechaEnteroMinuto(Date mFecha)
	{
		return new Integer(ConvertirFechaMinuto(mFecha));
	}

	public static Integer ConvertirFechaEnteroYYYY(Date mFecha)
	{
		return new Integer(ConvertirFechaYYYY(mFecha));
	}

	public static String ConvertirFechaDD(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat("dd");
		return mFormato.format(mFecha);
	}
	
	public static String ConvertirFechaMes(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat("MM");
		return mFormato.format(mFecha);
	}
		
	public static String ConvertirFecha_dd_MM_yyyy(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_dd_MM_yyyy);
		return mFormato.format(mFecha);
	}
	
	public static String ConvertirFechaDDMMYYYY(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_ddMMyyyy);
		return mFormato.format(mFecha);
	}

	public static String ConvertirFechaDDMMYYYYHHmmss(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_ddMMyyyyHHmmss);
		return mFormato.format(mFecha);
	}

	public static String ConvertirFechaHH(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat("HH");
		return mFormato.format(mFecha);
	}

	public static String ConvertirFechaHHmmss(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_HHmmss);
		return mFormato.format(mFecha);
	}
	
	public static String ConvertirFechaDDMMYYYYHHmm(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_ddMMyyyyHHmm);
		return mFormato.format(mFecha);
	}
	
	public static String ConvertirFechaHHmm(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_HHmm);
		return mFormato.format(mFecha);
	}
	
	public static String ConvertirFechaMinuto(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat("mm");
		return mFormato.format(mFecha);
	}

	public static String ConvertirFechaYYYY(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat("yyyy");
		return mFormato.format(mFecha);
	}
	
	public static String ConvertirFechaYY(Date mFecha)
	{
		SimpleDateFormat mFormato = new SimpleDateFormat("yy");
		return mFormato.format(mFecha);
	}

	public static Date numeroYYYYMMDDToDate(int anio, int mes, int dia)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(anio, mes - 1, dia);
		return calendar.getTime();
	}

	public static Date ConvertirCadenaFechaDDMMYYYY(String mFecha)
	{
		Date mResultado = null;
		if (!CadenaUtil.EsCadenaNulaVacia(mFecha))
		{
			try
			{
				SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_ddMMyyyy);
				mResultado = mFormato.parse(mFecha);
			}
			catch (Exception e)
			{
			}
		}
		return mResultado;
	}
	
	public static Date ConvertirCadenaFechaDDMMYYYYHHmmss(String mFecha)
	{
		Date mResultado = null;
		if (!CadenaUtil.EsCadenaNulaVacia(mFecha))
		{
			try
			{
				SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_ddMMyyyyHHmmss);
				mResultado = mFormato.parse(mFecha);
			}
			catch (Exception e)
			{
			}
		}
		return mResultado;
	}
	
	public static Date ConvertirCadenaFechaDDMMYYYYHHmmToDate(String mFecha)
	{
		Date mResultado = null;
		if (!CadenaUtil.EsCadenaNulaVacia(mFecha))
		{
			try
			{
				SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_ddMMyyyyHHmm);
				mResultado = mFormato.parse(mFecha);
			}
			catch (Exception e)
			{
			}
		}
		return mResultado;
	}
	
	public static Date ConvertirCadenaFechaHHmm(String mFecha)
	{
		Date mResultado = null;
		if (!CadenaUtil.EsCadenaNulaVacia(mFecha))
		{
			try
			{
				SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_HHmm);
				mResultado = mFormato.parse(mFecha);
			}
			catch (Exception e)
			{
			}
		}
		return mResultado;
	}
	
	public static Date ConvertirCadenaYYYYMMDD(String mFecha)
	{
		Date mResultado = null;
		if (!CadenaUtil.EsCadenaNulaVacia(mFecha))
		{
			try
			{
				SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_yyyyMMdd);
				mResultado = mFormato.parse(mFecha);
			}
			catch (Exception e)
			{

			}
		}
		return mResultado;
	}
	
	public static Date ConvertirCadenaYYYY_MM_DD(String mFecha)
	{
		Date mResultado = null;
		if (!CadenaUtil.EsCadenaNulaVacia(mFecha))
		{
			try
			{
				SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_yyyy_MM_dd);
				mResultado = mFormato.parse(mFecha);
			}
			catch (Exception e)
			{

			}
		}
		return mResultado;
	}
	
	public static Date ConvertirCadenadd_MM_yyyy(String mFecha)
	{
		Date mResultado = null;
		if (!CadenaUtil.EsCadenaNulaVacia(mFecha))
		{
			try
			{
				SimpleDateFormat mFormato = new SimpleDateFormat(FORMATO_dd_MM_yyyy);
				mResultado = mFormato.parse(mFecha);
			}
			catch (Exception e)
			{

			}
		}
		return mResultado;
	}
	
	public static String obtenerFechaActualDDMMYYYY(){
		int dia  = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);		
		int mes  = Calendar.getInstance().get(Calendar.MONTH)+1;
		int anio = Calendar.getInstance().get(Calendar.YEAR);		
		String strDia = dia<10 ? "0" + dia : dia + "";
		String strMes = mes<10 ? "0" + (mes) : (mes) + "";
		
		String fecha = strDia + "/" + strMes + "/" + anio;
		return fecha;
	}
	
	public static String obtenerAnioActual(){
		Calendar cal= Calendar.getInstance(); 
		Integer anio= cal.get(Calendar.YEAR); 
		return anio.toString();
	}
	
	public static String obtenerMesActual(){
		Calendar cal= Calendar.getInstance(); 
		Integer mes= cal.get(Calendar.MONTH) + 1;
		String mesRetornar="";
			mesRetornar=mes<10?"0"+mes.toString():mes.toString();
		return mesRetornar;
	}
		
	public static int obtenerMesActualEntero(){
		Calendar cal= Calendar.getInstance(); 
		Integer mes= cal.get(Calendar.MONTH) + 1;
		
		return mes;
	}
		
	public static String obtenerFechaActualCorrido(){
		
		Calendar calendar=Calendar.getInstance();
		// Recuperamos día, mes y año de vigencia.
		String dia=Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String mes=String.format("%02d", calendar.get(Calendar.MONTH)+1);
		String anio=Integer.toString(calendar.get(Calendar.YEAR));
		String hora=String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY));
		String minutos=String.format("%02d", calendar.get(Calendar.MINUTE));
		String segundos=String.format("%02d", calendar.get(Calendar.SECOND));
		
		String fechaReturn = anio+mes+dia+hora+minutos+segundos;
		
		return fechaReturn;
	}
	
	public static String obtenerFechaActualDDMMYYYYHHMMSS(){
	//  Date mResultadofinal = null;
		Calendar calendar=Calendar.getInstance();
		// Recuperamos día, mes y año de vigencia.
		String dia=Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		String mes=String.format("%02d", calendar.get(Calendar.MONTH)+1);
		String anio=Integer.toString(calendar.get(Calendar.YEAR));
		String hora=String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY));
		String minutos=String.format("%02d", calendar.get(Calendar.MINUTE));
		String segundos=String.format("%02d", calendar.get(Calendar.SECOND));
		
		String fechaReturn = dia+"/"+mes+"/"+anio+" "+hora+":"+minutos+":"+segundos;
		//FORMATO_ddMMyyyyHHmmss  dd/MM/yyyy HH:mm:ss  yyyy-MM-dd HH:mm:ss  ConvertirCadenaFechaDDMMYYYYHHmmss
		return fechaReturn;
	}
		
	public static int obtenerDiasDiferenciaEntreFechas(Date fechaInicial, Date fechaFinal){
		
		 long startTime = fechaInicial.getTime();
	     long endTime = fechaFinal.getTime();
	     long diffTime = endTime - startTime;
	     int dias = (int)TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
	
		
		return dias;
	}
	
	public static int obtenerEdadFechaDDMMYYYY(String fecha){
		
		int anios = 0;
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fecha, fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		
		anios = periodo.getYears();
//		System.out.printf("Tu edad es: %s años, %s meses y %s días",
//		                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
		
		return anios;
	} 
	
	public static int obtenerEdadFechaYYYYMMDD(String fecha){
		
		int anios = 0;
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaNac = LocalDate.parse(fecha, fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		
		anios = periodo.getYears();
//		System.out.printf("Tu edad es: %s años, %s meses y %s días",
//		                    periodo.getYears(), periodo.getMonths(), periodo.getDays());
		
		return anios;
	} 
	
	public static String fechaActualFormateado(){
		Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'de' yyyy");
		date = cal.getTime();
		return df.format(date); 
		
	}
	
	
}
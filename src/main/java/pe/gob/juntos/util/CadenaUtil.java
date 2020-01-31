package pe.gob.juntos.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadenaUtil
{

	public static String ConvertirCadenaNulaToVacia(String mCadena)
	{
		if (mCadena == null)
		{
			return "";
		}
		return mCadena;
	}

	public static boolean EsCadenaNulaVacia(String mCadena)
	{
		boolean valor = false;

		if (mCadena == null)
		{
			return true;
		}
		if (mCadena.trim().isEmpty())
		{
			return true;
		}
		return valor;
	}
	
	public static boolean validarCorreo(String correo){
		boolean correoValido=false;
		
		 String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
			      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
			  Pattern pattern = Pattern.compile(emailPattern);
			    String email = correo;
			  if (email != null) {
			      Matcher matcher = pattern.matcher(email);
			      if (matcher.matches()) {
			    	  correoValido=true;
			    }
			   }
			  
			  return correoValido;
		
	}

	private CadenaUtil()
	{
	}
	
	public static String getMD5(String input) {
		 try {
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 byte[] messageDigest = md.digest(input.getBytes());
		 BigInteger number = new BigInteger(1, messageDigest);
		 String hashtext = number.toString(16);

		 while (hashtext.length() < 32) {
		 hashtext = "0" + hashtext;
		 }
		 return hashtext;
		 }
		 catch (NoSuchAlgorithmException e) {
		 throw new RuntimeException(e);
		 }
		 }

}
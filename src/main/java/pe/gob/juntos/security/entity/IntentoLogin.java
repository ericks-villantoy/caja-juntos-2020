package pe.gob.juntos.security.entity;

import java.io.Serializable;
import java.util.Date;

public class IntentoLogin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String sessionId;
	private int intento;
	private Date lastModified;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public int getIntento() {
		return intento;
	}
	public void setIntento(int intento) {
		this.intento = intento;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	
	
	
}

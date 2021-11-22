package co.edu.uniquindio.programaAdministrarCursos.model;

import java.io.Serializable;

public  abstract class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String iD;
	private String email;
	private String pasword;
	/**
	 * constructor de la super clase
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor usando fields
	 * @param name
	 * @param iD
	 * @param email
	 * @param pasword
	 */
	public User(String name, String iD, String email, String pasword) {
		super();
		this.name = name;
		this.iD = iD;
		this.email = email;
		this.pasword = pasword;
	}
	/**
	 * metodo get del atributo name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * metodo set del atributo name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * metodo get del atributo iD 
	 * @return iD
	 */
	public String getiD() {
		return iD;
	}
	/** 
	 * metodo set del atributo iD
	 * @param iD
	 */
	public void setiD(String iD) {
		this.iD = iD;
	}
	/**
	 * metodo get del atributo email 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * metodo set del atributo email 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * metodo get del atributo pasword
	 * @return
	 */
	public String getPasword() {
		return pasword;
	}
	/**
	 *  metodo set del atributo name
	 * @param pasword
	 */
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	/**
	 * metodo toString de la clase
	 */
	@Override
	public String toString() {
		return "User [name=" + name + ", iD=" + iD + ", email=" + email + ", pasword=" + pasword + "]";
	}
	public abstract String getDatosTXT();
}

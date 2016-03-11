package com.resources;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class Error implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3783187784577947027L;
		@XmlElement
		private int codigo;
		@XmlElement
		private String mensaje;

		public Error() {}
		

		public Error(int codigo, String mensaje) {
			this.codigo = codigo;
			this.mensaje = mensaje;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setNombre(String mensaje) {
			this.mensaje = mensaje;
		}
		
}

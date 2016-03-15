package test.java;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonaTest {

		@XmlElement
		public Long id;
		@XmlElement
		public String dni;
		@XmlElement
		public String nombre;
		@XmlElement
		public String primerApellido;
		@XmlElement
		public String segundoApellido;			
			
}


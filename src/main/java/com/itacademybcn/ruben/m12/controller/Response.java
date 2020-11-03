/**
 * 
 */
package com.itacademybcn.ruben.m12.controller;

import com.itacademybcn.ruben.m12.dto.Empleado;

import org.springframework.http.HttpHeaders;
/**
 * @author admin
 *
 */

    import java.util.Map;
	
/**
 * NO SE UTILIZA PERO LA MANTENGO POR TEMAS DIDÁCTICOS
 *
 */
	public class Response {

	    private Empleado empleado;
	    private String mensaje;
	    private HttpHeaders headers = new HttpHeaders();
        private Map<String,String> errores;

	    public Response(Empleado empleado, String cabecera, String mensaje) {
	    	this.empleado = empleado;
	    	this.mensaje = mensaje;
	        headers.add("Resposta", cabecera);

	    
	    }

	    public Response(Empleado empleado, String cabecera, Map<String,String> errors) {
	    	this.empleado = empleado;
	    	this.errores = errors;
	        headers.add("Resposta", cabecera);

	    }
	

	    	
		public Map<String, String> getErrores() {
			return errores;
		}

		public void setErrores(Map<String, String> errores) {
			this.errores = errores;
		}

		public Empleado getEmpleado() {
			return empleado;
		}

		public void setEmpleado(Empleado empleado) {
			this.empleado = empleado;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public HttpHeaders getHeaders() {
			return headers;
		}

		public void setHeaders(HttpHeaders headers) {
			this.headers = headers;
		}
	    
	    

	    

	}


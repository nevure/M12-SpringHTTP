package com.itacademybcn.ruben.m12.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * Clase entidad
 * 
 * 
 * @author Rubén Rodríguez
 *
 */

@Component
public class Empleado {
	
	
    @NotNull(message = "Id obligatorio")	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	
    @NotNull(message = "Introduce un  nombre del empleado")
    @Size(min=4, max=25, message="minimo 4 y max 25")
    private String userName; 
   
    @NotBlank(message = "Debe pertenecer a un departamento")	
	private String departamento;
	
    @Range(min=1300, max=4500, message="Rango de sueldo entre 1300 y 4500 euros")
	private double sueldo;
    
    @NotNull
    @StringEnEnum(enumClass = TipoEmpleado.class, message ="Tipo empleado no existe en el organigrama")
	private String cargoEmpleado;

    
    //SETTERS Y GETTERS
  	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", userName=" + userName + ", Departamento=" + departamento + ", sueldo=" + sueldo
				+ ", cargoEmpleado=" + cargoEmpleado + "]";
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the sueldo
	 */
	public double getSueldo() {
		return sueldo;
	}

	/**
	 * @param sueldo the sueldo to set
	 */
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	/**
	 * @return the cargoEmpleado
	 */
	public String getCargoEmpleado() {
		return cargoEmpleado;
	}

	/**
	 * @param cargoEmpleado the cargoEmpleado to set
	 */
	public void setCargoEmpleado(String cargoEmpleado) {
		this.cargoEmpleado = cargoEmpleado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	

}

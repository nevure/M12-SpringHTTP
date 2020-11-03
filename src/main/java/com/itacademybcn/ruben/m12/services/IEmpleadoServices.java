/**
 * 
 */
package com.itacademybcn.ruben.m12.services;

import java.util.Map;

import com.itacademybcn.ruben.m12.dto.Empleado;

/**
 * Interfaz de Servicio
 * @author Rubén Rodríguez
 *
 */
public interface IEmpleadoServices {
	
	Empleado addEmpleado( Empleado e);
	String lista();
	boolean empleadoById(long id);
	public boolean idExiste(long id);
	public Empleado modifyEmpelado (Empleado e);
	public Map<Long,Empleado> listaMapa();
	public Empleado getEmpleado(long id);
	public void eliminarEmpleado(long id);
	public Map<Long,Empleado> getEmpleadosRol(String rol);

}

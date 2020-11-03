package com.itacademybcn.ruben.m12.services;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademybcn.ruben.m12.dto.Empleado;
import com.itacademybcn.ruben.m12.repository.EmpleadoRepository;

/**
 * 
 * Clase Service .
 * Nuestra clase de servicio. 
 * En la mayoría de casos es una llamada transparente al repositiorio.
 * @author admin
 *
 */
@Service
public class EmpleadoServices implements IEmpleadoServices {
	
	
	private final EmpleadoRepository repositorio;

	/**
	 * Constructor que inyecta el reposuitorio.
	 * 
	 * @param repositorio
	 */
	@Autowired
	public EmpleadoServices(EmpleadoRepository repositorio) {
		this.repositorio = repositorio;
	}
	
	public Empleado addEmpleado( Empleado e){
		
		return repositorio.save(e);
	}
	
	public Empleado modifyEmpelado (Empleado e) {
		return repositorio.modificarEmpleado(e);
	}
	
	/**
	 * Devuelve un string con todos los empleados
	 */
	public String lista() {
		
		 StringBuilder b = new StringBuilder();
		 repositorio.findAll().forEach(b::append);
		return b.toString();
		
	}
	/**
	 * elimina empleado llamando al método del repositorio.
	 */
	public void eliminarEmpleado(long id) {
		repositorio.deleteById(id);
	}
	
	/**
	 * Devuelve un mapa de empleados que cumplen un rol determinado.
	 * Hacemos uso de lambdas, filtramos el stream para que nos devuelta un stream de solo aquellos que cumplen la condición de filtro.
	 * luego los recogemos en un collector que los pasa a un map con key el id y clave un objeto de tipo Empleado.
	 * 
	 */
	public Map<Long,Empleado> getEmpleadosRol(String rol){
		Map<Long, Empleado> mapaEmpleados;
		//repositorio.findAll().stream().forEach(p->System.out.println("usuario-> rol:"+p.getCargoEmpleado()));

		mapaEmpleados = repositorio.findAll().stream().filter(e-> e.getCargoEmpleado().equalsIgnoreCase(rol)).collect(Collectors.toMap(Empleado::getId,Function.identity()));
		return mapaEmpleados;
	}
	
	/**
	 * Método que devuelve un mapa de todos los empleados. Utilizamos como en el anterior caso lambdas.
	 */
	
	public Map<Long,Empleado> listaMapa() {
		
		Map<Long, Empleado> mapaEmpleados;
		mapaEmpleados = repositorio.findAll().stream().collect(Collectors.toMap(Empleado::getId,Function.identity()));
		
		return mapaEmpleados;
	}
	
	public Empleado getEmpleado(long id) {
		return repositorio.getOne(id);
	}
	
	public boolean idExiste(long id) {
		
		return repositorio.existsById(id);
	}
	
	@Override
	public boolean empleadoById(long id) {
		return repositorio.existsById(id);
	}

	
	

}


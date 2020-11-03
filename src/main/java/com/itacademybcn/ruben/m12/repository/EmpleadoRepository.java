/**
 * 
 */
package com.itacademybcn.ruben.m12.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.itacademybcn.ruben.m12.dto.Empleado;


/**
 * 
 * Clase repositorio.
 * implementamos la interfaz JPA
 * Trabajaremos con una lista en lugar de trabajar con una BBDD
 * 
 * @author Rubén Rodríguez 
 * 
 * 
 *
 */


@Repository
public class EmpleadoRepository implements IEmpleadoRepository {
	
	@Autowired
    List<Empleado> empleados;
	boolean yaExiste=false;

	/**
	 * Método propio para modificar empleados. 
	 * obtenemos el índice del empleado y luego lo sobreescribimos con los cambios.
	 * 
	 * @param e
	 * @return
	 */
	public Empleado modificarEmpleado (Empleado e) {
		if (empleados.indexOf(e) >= 0)
			empleados.set(empleados.indexOf(e), e);
		return empleados.get(empleados.indexOf(e));
	}
    
    @Override
	public <S extends Empleado> S save(S entity) {
		empleados.add(entity);
		return entity;
	}
    
	@Override
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return empleados;
	}

	/**
	 * Verificamos si existge el empleado por id
	 */
	@Override
	public boolean existsById(Object id) {

		yaExiste = empleados.stream().anyMatch(p-> p.getId() == (long)id);
		return yaExiste;
	}
	
	/**
	 * Borramos empleado con el id facilitado.
	 */
	@Override
	public void deleteById(Object id) {
		empleados.removeIf(e -> e.getId() == (long)id);
	}
	
	@Override
	public Empleado getOne(Object id) {
			
		System.out.println("el indice del id "+id+" es:  "+empleados.indexOf(id));
		return empleados.stream().filter(e -> e.getId() == (long) id).findFirst().get();	
	}

	
	// RESTO QUE NO SE IMPLEMENTAN
	
	@Override
	public void delete(Empleado entity) {
		// TODO Auto-generated method stub
	}


	@Override
	public List<Empleado> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> findAllById(Iterable<Object> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Empleado> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Empleado> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Empleado> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public <S extends Empleado> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Empleado> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Empleado> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Empleado> findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
	@Override
	public void deleteAll(Iterable<? extends Empleado> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Empleado> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Empleado> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Empleado> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Empleado> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
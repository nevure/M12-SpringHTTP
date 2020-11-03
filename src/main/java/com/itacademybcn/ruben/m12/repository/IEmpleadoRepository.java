/**
 * 
 */
package com.itacademybcn.ruben.m12.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itacademybcn.ruben.m12.dto.Empleado;
/**
 * 
 * Interfaz de nuestro repositiorio. Extendemos de JpaRepositorio.
 * @author rubén Rodríguez
 *
 */
public interface IEmpleadoRepository extends JpaRepository<Empleado, Object> {

	 
}

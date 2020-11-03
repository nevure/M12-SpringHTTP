package com.itacademybcn.ruben.m12.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.itacademybcn.ruben.m12.dto.Empleado;
import com.itacademybcn.ruben.m12.services.IEmpleadoServices;


/**
 * Clase controladora
 * 
 * @author Rubén Rodríguez Fernández
 *
 */

@RestController
public class Controlador {
	
	Response result;
	List<String> errors = new ArrayList<String>();
	Map<String, String> erroresMap;
	private final IEmpleadoServices empleadoServicio;
	private Response respuesta;

	@Autowired
	public Controlador(IEmpleadoServices userService){
	    this.empleadoServicio = userService;
	}


	/**
	 * 
	 * Método Create de CRUD para empleados. Recibe un body que validará antes de realizar ninguna otra acción.
	 * Si el body contiene campos válidos devuelve el mismo objeto.
	 * Si el body contiene errores en sus campos, se devuelve un body con los errores.
	 * 
	 * Ámbas respuestas tienen un campo en la cabecera que informa si hay o no error.
	 * 
	 * @param e Empleado
	 * @param resultadoValidaciones 
	 * @return Objeto Empleado o listado de errores de validación
	 * @throws InvalidFormatException
	 */
	
	@PostMapping("/empleados")
	public ResponseEntity<Object> agregar(@Valid @RequestBody Empleado e, BindingResult  resultadoValidaciones) throws InvalidFormatException{	
		
		//Si el id existe no se hace nada y se devuelve un código personalizado en el header
		if (empleadoServicio.idExiste(e.getId()))
			return ResponseEntity.ok().header("Resposta", "ID_EXISTE").body(e);
		
		
		// Sino hay errores de validación por parte de JPA ->
		if (!resultadoValidaciones.hasErrors()) {
			System.out.println("aqui  ES correcto  " );
			respuesta = new Response(e,"OK", "");
			return new ResponseEntity<>(empleadoServicio.addEmpleado(e), respuesta.getHeaders(),HttpStatus.OK);
			}

		else 
			//Si hay errores de validación
		{
			//Con los errores creamos un mapa siendo la clave el campo con error y el valor el mensaje de error configurado en la entidad.
			erroresMap = resultadoValidaciones.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)); //Converts List items to Map
			
			//Creamos el objeto respuesta con el error.
			respuesta = new Response(e,"ERROR", erroresMap);
		}
		
		return ResponseEntity.ok().headers(respuesta.getHeaders()).body(respuesta.getErrores()) ;
			
	}
	
	/**
	 * Método para modificar un empleado. Aunque en el body pongamos un id diferente. el Id válido es el de la url. por lo que se reescribe si es
	 * necesario.
	 * 
	 * @param e
	 * @param id  identificador del empleado a modificar.
	 * @return
	 */
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Object> modificar(@PathVariable long id, @Valid @RequestBody Empleado e, BindingResult  resultadoValidaciones) throws InvalidFormatException{	
		
		//Si el id no existe no se hace nada y se devuelve un código personalizado en el header
		if (!empleadoServicio.idExiste(id))
			return ResponseEntity.ok().header("Resposta", "ID_NOEXISTE").body(e);

		//sino hay errores de validación, modificamos el empleado y devolvemos un response perosnalizado.
		if (!resultadoValidaciones.hasErrors()) {
			respuesta = new Response(e,"OK", "");
			e.setId(id);
			return new ResponseEntity<>(empleadoServicio.modifyEmpelado(e), respuesta.getHeaders(),HttpStatus.OK);
			}

		else 
			//Si hay errores de validación
		{
			//Con los errores creamos un mapa siendo la clave el campo con error y el valor el mensaje de error configurado en la entidad.
			erroresMap = resultadoValidaciones.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)); //Converts List items to Map
			
			//Creamos el objeto respuesta con el error.
			respuesta = new Response(e,"ERROR", erroresMap);
		}
		
		return ResponseEntity.ok().headers(respuesta.getHeaders()).body(respuesta.getErrores()) ;
	
	}
	
	/**
	 * Método que devuelve un body con los datos del empleado con id igual al falicitado por url. 
	 * Sino existe se devuelve un header y body personalizados
	 * 
	 * @param id
	 * @return Datos del empleado solicitado si existe el id
	 */
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Object> getOne(@PathVariable long id) {
		if (!empleadoServicio.idExiste(id))
			return ResponseEntity.ok().header("Resposta", "ID_NOEXISTE").body("ID no Existe");
		return ResponseEntity.ok().header("Resposta", "OK").body(empleadoServicio.getEmpleado(id));
		
	}
	
	/**
	 * Método que devuelte un listado (Map) de los empleados que cumplen un rol.
	 * @param rol
	 * @return
	 */
	@GetMapping("/empleados/roles/{rol}")
	public ResponseEntity<Object> getRol(@PathVariable String rol) {
		
		return ResponseEntity.ok().header("Resposta", "OK").body(empleadoServicio.getEmpleadosRol(rol));
		
	}
	
	/**
	 * Método que elimina un empleado con id igual al facilitado por url. 
	 * sino existe se informa por header ty body personalizado.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Object> borrar(@PathVariable long id){
		
		//Si el id no existe no se hace nada y se devuelve un código personalizado en el header
		if (!empleadoServicio.idExiste(id))
			return ResponseEntity.ok().header("Resposta", "ID_NOEXISTE").body("ID no Existe");
		
		//Eliminar usuario y devolvemos un ok en el header y body
		empleadoServicio.eliminarEmpleado(id);
		return ResponseEntity.ok().header("Resposta", "OK").body("Usuario Eliminado");
		
	}
	
	/**
	 * Método que retorna un body con un listado de empleados.
	 * @return
	 */
	@GetMapping("/empleados")
	public ResponseEntity<Object> getAll() {

		return ResponseEntity.ok().header("Resposta","OK").body(empleadoServicio.listaMapa()) ;

	}
	
}
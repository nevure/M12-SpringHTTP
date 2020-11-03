/**
 * 
 */
package com.itacademybcn.ruben.m12.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Clase que implement un ConstaintValidator para validar los enum, los roles (cargoEmpleado)
 * Realmente tengo que acabar de pulir como funciona la implementación de un constraint personalizado. En Diagonal 
 * lo comprendo, en detalle tengo que acabar de absorberlo todo.
 * 
 * @author Rubén Rodríguez 
 * StringEnumerationValidator
 */
public class StringEnumValidador implements ConstraintValidator<StringEnEnum, Object> {
	private Set<String> ENUM_DISPONIBLES;

	  /**
	   * Devolvemos un hashset con los enums configurados en TipoEmpleado
	   */
	  public static Set<String> getEnumsSet(Class<? extends Enum<?>> e) {
	     Enum<?>[] enums = e.getEnumConstants();
	     String[] nombreTipo = new String[enums.length];
	     for (int i = 0; i < enums.length; i++) {
	         nombreTipo[i] = enums[i].name();
	     }
	     Set<String> enumSet = new HashSet<String>(Arrays.asList(nombreTipo));
	     return enumSet;
	  }

	  /**
	   * al inicializar recogemos los enumerados configurados en TipoEmpleado a través del método getEnumsSet. A través de enumClass recibimos 
	   * la colección de enumerado de TipoEmpleado.
	   * 
	   */
	  @Override
	  public void initialize(StringEnEnum stringEnEnum) {
	    Class<? extends Enum<?>> enumSeleccionado = stringEnEnum.enumClass();
	    ENUM_DISPONIBLES = getEnumsSet(enumSeleccionado);
	  }

	  /**
	   * Sobreescribimos el método que realiza la validación. comprobamos que el objeto valor se encuentre dentro de los valores
	   * posible de nuestro enumerado.
	   */
	  @Override
	  public boolean isValid(Object valor, ConstraintValidatorContext context) {
	    if ( valor == null ) {
	      return true;
	    } else {
	    	for (String s: ENUM_DISPONIBLES ) {
	    		if (s.equalsIgnoreCase((String)valor))
	    			return true;
	    	}
	    	return false;
	    }
	  }
}



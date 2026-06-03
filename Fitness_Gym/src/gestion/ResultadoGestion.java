package gestion;

/**
 * Enumeración que representa los posibles resultados de las operaciones
 * realizadas en la capa de gestión.
 * 
 * Se utiliza para comunicar el resultado de una acción (alta, modificación,
 * eliminación, comprobación, etc.) desde la capa de gestión hacia la capa
 * de presentación (Inicio o GUI).
 * 
 * Valores posibles:
 * - OK: operación realizada correctamente
 * - YA_EXISTE: el elemento ya existe en la base de datos
 * - ERROR: se ha producido un error en la operación
 * - DISPONIBLE: el elemento no existe y está disponible
 */
public enum ResultadoGestion {
	OK, YA_EXISTE, ERROR, DISPONIBLE

}

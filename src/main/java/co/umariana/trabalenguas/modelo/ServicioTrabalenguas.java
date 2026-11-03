package co.umariana.trabalenguas.modelo;

import java.util.List;
import java.util.Map;

/**
 * Lo que la aplicación web necesita del modelo. NO se modifica.
 * {@link GestorTrabalenguas} es la implementación (tarea de MODELO).
 */
public interface ServicioTrabalenguas {

    /** Valida el trabalengua, rechaza códigos repetidos y guarda el cambio en el archivo. */
    void agregar(Trabalengua trabalengua);

    /** Devuelve el trabalengua con ese código, o {@code null} si no existe. */
    Trabalengua buscar(String codigo);

    /** Elimina el trabalengua con ese código y guarda el cambio. Devuelve {@code false} si no existía. */
    boolean eliminar(String codigo);

    /** Todos los trabalenguas, en el orden de la lista. */
    List<Trabalengua> listar();

    /** Lista NUEVA ordenada de mayor a menor enredo (empate: por código). No altera el orden de la lista. */
    List<Trabalengua> ranking();

    /** Promedio de enredo por categoría, ordenado alfabéticamente por categoría. */
    Map<String, Double> promedioPorCategoria();

    /** Cantidad de trabalenguas guardados. */
    int total();

    /** Trabalengua donde está el cursor (el visor), o {@code null} si no hay trabalenguas. */
    Trabalengua actual();

    /** Mueve el cursor al siguiente trabalengua y lo devuelve ({@code null} si no hay trabalenguas). */
    Trabalengua siguiente();

    /** Mueve el cursor al trabalengua anterior y lo devuelve ({@code null} si no hay trabalenguas). */
    Trabalengua anterior();
}

package co.umariana.trabalenguas.modelo;

import co.umariana.trabalenguas.estructura.Lista;
import co.umariana.trabalenguas.persistencia.Repositorio;

import java.util.List;
import java.util.Map;

/**
 * Reglas de negocio del Trabalenguario (tarea de MODELO).
 *
 * <p>Guarda los trabalenguas en una {@link Lista} (la estructura de datos del equipo) y, después de
 * cada cambio, los guarda con el {@link Repositorio}. No debe usar {@code ArrayList} ni otra
 * colección de Java para guardar los datos: eso lo hace la {@code Lista}.
 */
public class GestorTrabalenguas implements ServicioTrabalenguas {

    private final Lista<Trabalengua> lista;
    private final Repositorio repositorio;

    /**
     * Al crearse, carga con el repositorio los trabalenguas ya guardados y los agrega a la lista.
     * Si el repositorio lanza {@link java.io.IOException}, se relanza como {@link java.io.UncheckedIOException}.
     */
    public GestorTrabalenguas(Lista<Trabalengua> lista, Repositorio repositorio) {
        this.lista = lista;
        this.repositorio = repositorio;
        // TODO (MODELO): cargar los trabalenguas del repositorio y agregarlos a la lista.
    }

    @Override
    public void agregar(Trabalengua trabalengua) {
        // TODO (MODELO): validar (trabalengua.validar()), rechazar código repetido
        //  (IllegalArgumentException), agregar al final de la lista y guardar con el repositorio.
        throw new UnsupportedOperationException("TODO: agregar()");
    }

    @Override
    public Trabalengua buscar(String codigo) {
        // TODO (MODELO)
        throw new UnsupportedOperationException("TODO: buscar()");
    }

    @Override
    public boolean eliminar(String codigo) {
        // TODO (MODELO): si existe, eliminarlo de la lista, guardar y devolver true.
        throw new UnsupportedOperationException("TODO: eliminar()");
    }

    @Override
    public List<Trabalengua> listar() {
        // TODO (MODELO): devolver los trabalenguas en el orden de la lista.
        throw new UnsupportedOperationException("TODO: listar()");
    }

    @Override
    public List<Trabalengua> ranking() {
        // TODO (MODELO)
        throw new UnsupportedOperationException("TODO: ranking()");
    }

    @Override
    public Map<String, Double> promedioPorCategoria() {
        // TODO (MODELO)
        throw new UnsupportedOperationException("TODO: promedioPorCategoria()");
    }

    @Override
    public int total() {
        // TODO (MODELO)
        throw new UnsupportedOperationException("TODO: total()");
    }

    @Override
    public Trabalengua actual() {
        // TODO (MODELO): usar el cursor de la lista.
        throw new UnsupportedOperationException("TODO: actual()");
    }

    @Override
    public Trabalengua siguiente() {
        // TODO (MODELO)
        throw new UnsupportedOperationException("TODO: siguiente()");
    }

    @Override
    public Trabalengua anterior() {
        // TODO (MODELO)
        throw new UnsupportedOperationException("TODO: anterior()");
    }
}

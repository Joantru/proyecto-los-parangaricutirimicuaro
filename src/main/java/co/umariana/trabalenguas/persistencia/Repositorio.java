package co.umariana.trabalenguas.persistencia;

import co.umariana.trabalenguas.modelo.Trabalengua;

import java.io.IOException;
import java.util.List;

/** Contrato de la persistencia. NO se modifica. {@link RepositorioTxt} es la implementación. */
public interface Repositorio {

    /** Lee todos los trabalenguas guardados. Si todavía no hay nada guardado, devuelve una lista vacía. */
    List<Trabalengua> cargar() throws IOException;

    /** Guarda TODOS los trabalenguas, reemplazando lo que hubiera antes. */
    void guardar(List<Trabalengua> trabalenguas) throws IOException;
}

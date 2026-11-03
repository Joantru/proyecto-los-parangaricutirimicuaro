package co.umariana.trabalenguas.web;

import co.umariana.trabalenguas.modelo.ServicioTrabalenguas;

/**
 * Punto único de acceso al modelo desde los servlets. NO se modifica.
 * Los servlets hacen {@code Contexto.servicio().listar()}, etc.
 */
public final class Contexto {

    private static volatile ServicioTrabalenguas servicio;

    private Contexto() {
    }

    public static ServicioTrabalenguas servicio() {
        ServicioTrabalenguas s = servicio;
        if (s == null) {
            throw new IllegalStateException("El modelo no está disponible todavía. Revise la consola del servidor: "
                    + "probablemente falta implementar alguna clase (GestorTrabalenguas, ListaDoble o RepositorioTxt).");
        }
        return s;
    }

    /** Lo usa {@link AppListener} al arrancar y las pruebas para poner un servicio de mentira. */
    public static void usar(ServicioTrabalenguas nuevo) {
        servicio = nuevo;
    }
}

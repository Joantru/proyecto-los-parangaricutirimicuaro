package co.umariana.trabalenguas.web;

import co.umariana.trabalenguas.estructura.ListaDoble;
import co.umariana.trabalenguas.modelo.GestorTrabalenguas;
import co.umariana.trabalenguas.modelo.Trabalengua;
import co.umariana.trabalenguas.persistencia.RepositorioTxt;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.nio.file.Path;

/** Arma el modelo al arrancar el servidor (lista + archivo + gestor). NO se modifica. */
@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent evento) {
        Path archivo = Path.of(System.getProperty("trabalenguas.archivo", "trabalenguas.txt"));
        try {
            Contexto.usar(new GestorTrabalenguas(new ListaDoble<Trabalengua>(), new RepositorioTxt(archivo)));
            evento.getServletContext().log("Trabalenguario listo. Datos en: " + archivo.toAbsolutePath());
        } catch (Throwable e) {
            // Mientras el equipo no termine sus clases, la aplicación arranca igual pero sin modelo.
            evento.getServletContext().log("El modelo todavía no está disponible: " + e);
            Contexto.usar(null);
        }
    }
}

package co.umariana.trabalenguas.web;

import co.umariana.trabalenguas.modelo.Trabalengua;
import co.umariana.trabalenguas.modelo.ServicioTrabalenguas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Servicio de mentira que anota lo que le piden: permite probar los servlets sin el resto del equipo. */
class ServicioFalso implements ServicioTrabalenguas {

    final List<Trabalengua> datos = new ArrayList<>();
    final List<Trabalengua> ranking = new ArrayList<>();
    final Map<String, Double> promedios = new TreeMap<>();
    final List<String> llamadas = new ArrayList<>();
    RuntimeException fallarAlAgregar = null;
    Trabalengua cursorActual = null;
    Trabalengua cursorSiguiente = null;
    Trabalengua cursorAnterior = null;

    @Override public void agregar(Trabalengua l) {
        llamadas.add("agregar");
        if (fallarAlAgregar != null) throw fallarAlAgregar;
        datos.add(l);
    }
    @Override public Trabalengua buscar(String codigo) { llamadas.add("buscar"); return null; }
    @Override public boolean eliminar(String codigo) { llamadas.add("eliminar:" + codigo); return true; }
    @Override public List<Trabalengua> listar() { llamadas.add("listar"); return datos; }
    @Override public List<Trabalengua> ranking() { llamadas.add("ranking"); return ranking; }
    @Override public Map<String, Double> promedioPorCategoria() { llamadas.add("promedios"); return promedios; }
    @Override public int total() { return datos.size(); }
    @Override public Trabalengua actual() { llamadas.add("actual"); return cursorActual; }
    @Override public Trabalengua siguiente() { llamadas.add("siguiente"); return cursorSiguiente; }
    @Override public Trabalengua anterior() { llamadas.add("anterior"); return cursorAnterior; }
}

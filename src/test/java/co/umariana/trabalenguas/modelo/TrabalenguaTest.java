package co.umariana.trabalenguas.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrabalenguaTest {

    private static Trabalengua trabalengua(String codigo, String titulo, String categoria, int enredo,
                                   double tiempo, String descripcion, String archivo) {
        return new Trabalengua(codigo, titulo, categoria, enredo, tiempo, descripcion, archivo);
    }

    private static Trabalengua valido() {
        return trabalengua("L001", "Pablito clavó un clavito", "Lengua", 2, 1.32, "Trabalengua de ejemplo", "pablito.png");
    }

    @Test
    @DisplayName("un trabalengua correcto es válido")
    void valido_noLanza() {
        assertDoesNotThrow(() -> valido().validar());
    }

    @Test
    @DisplayName("la descripción puede estar vacía")
    void descripcionVaciaEsValida() {
        assertDoesNotThrow(() -> trabalengua("L1", "T", "C", 5, 1, "", "a.png").validar());
    }

    @Test
    @DisplayName("los límites del enredo (1 y 10) son válidos")
    void limitesValidos() {
        assertDoesNotThrow(() -> trabalengua("L1", "T", "C", 1, 1, "", "a.png").validar());
        assertDoesNotThrow(() -> trabalengua("L1", "T", "C", 10, 1, "", "a.png").validar());
    }

    @Test
    @DisplayName("el enredo fuera de 1..10 es inválida")
    void enredoFueraDeRango() {
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 0, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 11, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", -3, 1, "", "a.png").validar());
    }

    @Test
    @DisplayName("el tiempo debe ser mayor que 0")
    void tiempoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, 0, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, -1.5, "", "a.png").validar());
    }

    @Test
    @DisplayName("código, título, categoría y archivo no pueden ser nulos ni estar en blanco")
    void textosObligatorios() {
        assertThrows(IllegalArgumentException.class, () -> trabalengua(null, "T", "C", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("  ", "T", "C", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "", "C", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", null, "C", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", " ", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, 1, "", "").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, 1, "", null).validar());
    }

    @Test
    @DisplayName("la descripción no puede ser nula")
    void descripcionNula() {
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, 1, null, "a.png").validar());
    }

    @Test
    @DisplayName("ningún texto puede contener ';' (separa los campos del .txt)")
    void sinPuntoYComa() {
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L;1", "T", "C", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T;x", "C", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C;x", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, 1, "a;b", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, 1, "", "a;.png").validar());
    }

    @Test
    @DisplayName("ningún texto puede contener saltos de línea")
    void sinSaltosDeLinea() {
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T\nx", "C", 5, 1, "", "a.png").validar());
        assertThrows(IllegalArgumentException.class, () -> trabalengua("L1", "T", "C", 5, 1, "uno\r\ndos", "a.png").validar());
    }

    @Test
    @DisplayName("el mensaje de error dice qué está mal")
    void mensajeDelError() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> trabalengua("L1", "T", "C", 11, 1, "", "a.png").validar());
        assertNotNull(e.getMessage());
        assertFalse(e.getMessage().isBlank());
    }
}

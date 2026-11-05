package co.umariana.trabalenguas.estructura;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista DOBLEMENTE enlazada del equipo (tarea de ESTRUCTURA).
 */
public class ListaDoble<T> implements Lista<T> {

    private final class Nodo {
        private final T elemento;
        private Nodo anterior;
        private Nodo siguiente;

        private Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    private Nodo cabeza;
    private Nodo cola;
    private Nodo cursor;
    private int tamano;

    @Override
    public void agregar(T elemento) {
        insertar(tamano, elemento);
    }

    @Override
    public void insertar(int indice, T elemento) {
        if (indice < 0 || indice > tamano) {
            throw new IndexOutOfBoundsException("Índice: " + indice + ", tamaño: " + tamano);
        }

        Nodo nuevo = new Nodo(elemento);

        if (tamano == 0) {
            cabeza = cola = cursor = nuevo;
        } else if (indice == 0) { // Inserción al inicio explícita
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        } else if (indice == tamano) { // Inserción al final explícita
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
            cola = nuevo;
        } else { // Inserción intermedia
            Nodo siguiente = nodoEn(indice);
            nuevo.anterior = siguiente.anterior;
            nuevo.siguiente = siguiente;
            siguiente.anterior.siguiente = nuevo;
            siguiente.anterior = nuevo;
        }

        tamano++;
    }

    @Override
    public T obtener(int indice) {
        return nodoEn(indice).elemento;
    }

    @Override
    public T eliminar(int indice) {
        Nodo eliminado = nodoEn(indice);
        Nodo anterior = eliminado.anterior;
        Nodo siguiente = eliminado.siguiente;

        if (anterior == null) {
            cabeza = siguiente;
        } else {
            anterior.siguiente = siguiente;
        }

        if (siguiente == null) {
            cola = anterior;
        } else {
            siguiente.anterior = anterior;
        }

        if (cursor == eliminado) {
            cursor = siguiente != null ? siguiente : anterior;
        }

        tamano--;
        if (tamano == 0) {
            cabeza = cola = cursor = null;
        }

        eliminado.anterior = null;
        eliminado.siguiente = null;
        return eliminado.elemento;
    }

    @Override
    public int tamano() {
        return tamano;
    }

    @Override
    public boolean estaVacia() {
        return tamano == 0;
    }

    @Override
    public void vaciar() {
        cabeza = cola = cursor = null;
        tamano = 0;
    }

    @Override
    public T actual() {
        return cursor == null ? null : cursor.elemento;
    }

    @Override
    public boolean avanzar() {
        if (cursor == null || cursor.siguiente == null) {
            return false;
        }
        cursor = cursor.siguiente;
        return true;
    }

    @Override
    public boolean retroceder() {
        if (cursor == null || cursor.anterior == null) {
            return false;
        }
        cursor = cursor.anterior;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Nodo siguiente = cabeza;

            @Override
            public boolean hasNext() {
                return siguiente != null;
            }

            @Override
            public T next() {
                if (siguiente == null) {
                    throw new NoSuchElementException();
                }
                T elemento = siguiente.elemento;
                siguiente = siguiente.siguiente;
                return elemento;
            }
        };
    }

    private Nodo nodoEn(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice: " + indice + ", tamaño: " + tamano);
        }

        if (indice < tamano / 2) {
            Nodo nodo = cabeza;
            for (int i = 0; i < indice; i++) {
                nodo = nodo.siguiente;
            }
            return nodo;
        }
        Nodo nodo = cola;
        for (int i = tamano - 1; i > indice; i--) {
            nodo = nodo.anterior;
        }
        return nodo;
    }
}
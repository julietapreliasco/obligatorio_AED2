package tads.lista;

import java.util.Iterator;

public class ListaImp<T> implements ILista<T> {

    protected Nodo<T> inicio;
    protected int largo;

    public ListaImp() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public void insertar(T dato) {
        inicio = new Nodo<T>(dato, inicio);
        largo++;
    }

    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean esVacia() {
        return largo == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Nodo<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.getDato();
                aux = aux.getSig();
                return dato;
            }

            @Override
            public void remove(){
            }
            
        };
    }

    protected static class Nodo<T> {

    private T dato;
    private Nodo<T> sig;

    public Nodo(T dato) {
        this.dato = dato;
        this.sig = null;
    }

    public Nodo(T dato, Nodo<T> sig) {
        this.dato = dato;
        this.sig = sig;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSig() {
        return sig;
    }

    public void setSig(Nodo<T> sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return dato.toString();
    }

}


}

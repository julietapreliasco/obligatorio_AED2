package tads.pila;
import java.util.Iterator;

public class PilaImp<T> implements IPila<T>  {

    private Nodo<T> inicio;

    @Override
    public void push(T dato) {
        inicio = new Nodo<>(dato, inicio);
    }

    @Override
    public T top() {
        return inicio.getDato();
    }

    @Override
    public T pop() {
        T aux = inicio.getDato();
        inicio = inicio.getSig();
        return aux;
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public boolean estaLlena() {
        //#TODO ver sirve para pilas en arrays
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Nodo<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            //PRE me quedan elementos
            @Override
            public T next() {
                T dato = aux.getDato();
                aux = aux.getSig();
                return dato;
            }
        };
    }

    protected class Nodo<T> {

        private T dato;

        private Nodo<T> sig;

        public Nodo(T dato) {
            this.dato = dato;
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

    }
}


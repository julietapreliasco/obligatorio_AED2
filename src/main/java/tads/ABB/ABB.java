package tads.ABB;

import dominio.ResultadoBusqueda;
import tads.lista.ListaImp;

public class ABB<T extends Comparable<T>> implements IABB<T> {
    private Nodo<T> raiz;

    public ABB() {
    }

    @Override
    public void insertar(T valor) {
        if (this.raiz == null) {
            this.raiz = new Nodo<>(valor);
        } else {
            insertar(this.raiz, valor);
        }
    }

    private void insertar(Nodo<T> nodo, T valor) {
        if (nodo != null) {
            if (valor.compareTo(nodo.getDato()) > 0) {
                if (nodo.getDer() != null) {
                    insertar(nodo.getDer(), valor);
                } else {
                    nodo.setDer(new Nodo<>(valor));
                }
            } else {
                if (nodo.getIzq() != null) {
                    insertar(nodo.getIzq(), valor);
                } else {
                    nodo.setIzq(new Nodo<>(valor));
                }
            }
        }
    }

    @Override
    public boolean pertenece(T valor) {
        return pertenece(this.raiz, valor);
    }

    private boolean pertenece(Nodo<T> nodo, T valor) {
        if (nodo != null) {
            if (nodo.getDato().compareTo(valor) == 0) {
                return true;
            } else if (valor.compareTo(nodo.getDato()) > 0) {
                return pertenece(nodo.getDer(), valor);
            } else {
                return pertenece(nodo.getIzq(), valor);
            }
        }
        return false;
    }

    @Override
    public T borrarMinimo() {
        return null;
    }

    @Override
    public void listarAsc() {
        listarAsc(this.raiz);
    }

    private void listarAsc(Nodo<T> nodo) {
        if (nodo != null) {
            listarAsc(nodo.getIzq());
            System.out.print("- " + nodo.getDato().toString());
            listarAsc(nodo.getDer());
        }
    }

    @Override
    public void listarDesc() {

    }

    @Override
    public ListaImp<T> obtenerAsc() {
        return null;
    }

    @Override
    public ListaImp<T> obtenerDesc() {
        return null;
    }

    @Override
    public void imprimirElementosDeNivel(int nivel) {

    }

    public ResultadoBusqueda<T> obtenerConCantidad(T valor) {
        return obtenerConCantidad(this.raiz, valor, 0);
    }

    private ResultadoBusqueda<T> obtenerConCantidad(Nodo<T> nodo, T valor, int cant) {
        if (nodo != null) {
            cant++;
            if (nodo.getDato().compareTo(valor) == 0) {
                return new ResultadoBusqueda<>(nodo.getDato(), cant);
            } else if (valor.compareTo(nodo.getDato()) > 0) {
                return obtenerConCantidad(nodo.getDer(), valor, cant);
            } else {
                return obtenerConCantidad(nodo.getIzq(), valor, cant);
            }
        }

        return new ResultadoBusqueda<>(null, cant);
    }

    protected static class Nodo<T> {
        private T dato;
        private Nodo<T> der;
        private Nodo<T> izq;

        public Nodo(T dato) {
            this.dato = dato;
        }

        public Nodo(T dato, Nodo<T> der, Nodo<T> izq) {
            this.dato = dato;
            this.der = der;
            this.izq = izq;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getDer() {
            return der;
        }

        public void setDer(Nodo<T> der) {
            this.der = der;
        }

        public Nodo<T> getIzq() {
            return izq;
        }

        public void setIzq(Nodo<T> izq) {
            this.izq = izq;
        }
    }

}

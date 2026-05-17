package tads.ABB;

import tads.lista.ListaImp;

public interface IABB<T> {
    void insertar(T valor);

    boolean pertenece(T valor);

    T borrarMinimo();

    void listarAsc();

    void listarDesc();

    ListaImp<T> obtenerAsc();

    ListaImp<T> obtenerDesc();

    void imprimirElementosDeNivel(int nivel);

}

package tads.ABB;

import tads.lista.ListaImp;

public interface IABB<T> {
    void insertar(T valor);

    boolean pertenece(T valor);

    T borrarMinimo();

    ListaImp<T> listarAsc();

    ListaImp<T> listarDesc();

    void imprimirElementosDeNivel(int nivel);

}

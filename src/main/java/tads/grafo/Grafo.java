package tads.grafo;

import tads.lista.ListaImp;

public class Grafo<T> {

    private int tope;
    private int cantidad;
    private T[] vertices;
    private Arista[][] matAdy;

    // lo siguiente solo evita el warning del compilador por el casteo de Object[] a
    // T[]
    @SuppressWarnings("unchecked")
    public Grafo(int unTope, boolean esDir) {

        tope = unTope;
        cantidad = 0;

        // Acá creamos un array de Object y se castea a T[] porque Java no permite crear
        // arrays genéricos directamente
        vertices = (T[]) new Object[tope];
        matAdy = new Arista[tope][tope];

        if (esDir) {

            for (int i = 0; i < tope; i++) {
                for (int j = 0; j < tope; j++) {
                    matAdy[i][j] = new Arista();
                }
            }

        } else {

            for (int i = 0; i < tope; i++) {
                for (int j = i; j < tope; j++) {

                    Arista aux = new Arista();

                    matAdy[i][j] = aux;
                    matAdy[j][i] = aux;
                }
            }
        }
    }

    public boolean esLleno() {
        return cantidad == tope;
    }

    public boolean esVacio() {
        return cantidad == 0;
    }

    // PRE: !esLleno()
    private int obtenerPosLibre() {

        for (int i = 0; i < tope; i++) {

            if (vertices[i] == null) {
                return i;
            }
        }

        return -1;
    }

    private int obtenerPos(T vert) {

        for (int i = 0; i < tope; i++) {

            if (vert.equals(vertices[i])) {
                return i;
            }
        }

        return -1;
    }

    // PRE: !esLleno && !existeVertice
    public void agregarVertice(T vert) {

        int pos = obtenerPosLibre();

        vertices[pos] = vert;

        cantidad++;
    }

    // PRE: existeVertice
    public void borrarVertice(T vert) {

        int pos = obtenerPos(vert);

        vertices[pos] = null;

        for (int k = 0; k < tope; k++) {

            matAdy[pos][k].setExiste(false);
            matAdy[k][pos].setExiste(false);
        }

        cantidad--;
    }

    public boolean existeVertice(T vert) {
        return obtenerPos(vert) != -1;
    }

    // existeVertice(origen) && existeVertice(destino) && !existeArista
    public void agregarArista(T origen, T destino, int peso) {

        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].setPeso(peso);
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existeArista(T origen, T destino) {

        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        return matAdy[posOrigen][posDestino].isExiste();
    }

    // existeVertice(origen) && existeVertice(destino) && existeArista
    public void borrarArista(T origen, T destino) {

        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        matAdy[posOrigen][posDestino].setExiste(false);
    }

    public ListaImp<T> verticesAdyacentes(T vert) {

        int pos = obtenerPos(vert);

        ListaImp<T> retorno = new ListaImp<>();

        for (int j = 0; j < tope; j++) {

            if (matAdy[pos][j].isExiste()) {
                retorno.insertarAlInicio(vertices[j]);
            }
        }

        return retorno;
    }

    // PRE: existeVertice(vert)
    public ListaImp<T> verticesIncidentes(T vert) {

        int pos = obtenerPos(vert);

        ListaImp<T> retorno = new ListaImp<>();

        for (int i = 0; i < tope; i++) {

            if (matAdy[i][pos].isExiste()) {
                retorno.insertarAlInicio(vertices[i]);
            }
        }

        return retorno;
    }
}
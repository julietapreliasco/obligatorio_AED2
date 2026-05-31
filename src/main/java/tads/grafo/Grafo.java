package tads.grafo;

import tads.lista.ListaImp;

public class Grafo {

    private int tope;
    private int cantidad;
    private String[] vertices;
    private Arista[][] matAdy;

    public Grafo(int unTope, boolean esDir) {

        tope = unTope;
        cantidad = 0;

        vertices = new String[tope];
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

    private int obtenerPos(String vert) {

        for (int i = 0; i < tope; i++) {

            if (vertices[i] != null && vert.equals(vertices[i])) {
                return i;
            }
        }

        return -1;
    }

    // PRE: !esLleno && !existeVertice
    public void agregarVertice(String vert) {

        int pos = obtenerPosLibre();

        vertices[pos] = vert;

        cantidad++;
    }

    // PRE: existeVertice
    public void borrarVertice(String vert) {

        int pos = obtenerPos(vert);

        vertices[pos] = null;

        for (int k = 0; k < tope; k++) {

            matAdy[pos][k].setExiste(false);
            matAdy[k][pos].setExiste(false);
        }

        cantidad--;
    }

    public boolean existeVertice(String vert) {
        return obtenerPos(vert) != -1;
    }

    // PRE: existeVertice(origen) && existeVertice(destino) && !existeArista
    public void agregarArista(String origen, String destino, int peso) {

        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].setPeso(peso);
    }

    // PRE: existeVertice(origen) && existeVertice(destino)
    public boolean existeArista(String origen, String destino) {

        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        return matAdy[posOrigen][posDestino].isExiste();
    }

    // PRE: existeVertice(origen) && existeVertice(destino) && existeArista
    public void borrarArista(String origen, String destino) {

        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);

        matAdy[posOrigen][posDestino].setExiste(false);
    }

    public ListaImp<String> verticesAdyacentes(String vert) {

        int pos = obtenerPos(vert);

        ListaImp<String> retorno = new ListaImp<>();

        for (int j = 0; j < tope; j++) {

            if (matAdy[pos][j].isExiste()) {
                retorno.insertarAlInicio(vertices[j]);
            }
        }

        return retorno;
    }

    // PRE: existeVertice(vert)
    public ListaImp<String> verticesIncidentes(String vert) {

        int pos = obtenerPos(vert);

        ListaImp<String> retorno = new ListaImp<>();

        for (int i = 0; i < tope; i++) {

            if (matAdy[i][pos].isExiste()) {
                retorno.insertarAlInicio(vertices[i]);
            }
        }

        return retorno;
    }
}
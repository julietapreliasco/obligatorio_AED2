package tads.AB;

public class AB implements IAB {
    private final Nodo raiz;

    public AB(Nodo raiz) {
        this.raiz = raiz;
    }


    @Override
    public int cantNodos() {
        return cantNodos(this.raiz);
    }

    private int cantNodos(Nodo nodo) {
        if (nodo != null) {
            return 1 + cantNodos(nodo.getIzq()) + cantNodos(nodo.getDer());
        }
        return 0;
    }

    @Override
    public int cantHojas() {
        return cantHojas(this.raiz);
    }

    private int cantHojas(Nodo nodo) {
        if (nodo != null) {
            if (nodo.isHoja()) {
                return 1;
            }
            return cantHojas(nodo.getIzq()) + cantHojas(nodo.getDer());
        }
        return 0;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    private int altura(Nodo nodo) {
        if (nodo != null) {
            if (nodo.isHoja()) {
                return 0;
            }
            return 1 + Math.max(altura(nodo.getIzq()), altura(nodo.getDer()));
        }
        return -1;
    }

    @Override
    public boolean todosPares() {
        return false;
    }

    private boolean todosPares(Nodo nodo) {
        if (nodo != null) {
            if (nodo.getDato() % 2 == 0) {
                return todosPares(nodo.getIzq()) && todosPares(nodo.getDer());
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean iguales(AB a) {
        return false;
    }

    @Override
    public boolean equilibrado() {
        return false;
    }

    private boolean equilibrado(Nodo nodo) {
        if (nodo != null) {
            if (Math.abs(altura(nodo.getIzq()) - altura(nodo.getDer())) > 1) {
                return false;
            }
            return equilibrado(nodo.getIzq()) && equilibrado(nodo.getDer());
        }
        return true;
    }

    @Override
    public boolean pertenece(int x) {
        return false;
    }

    private boolean pertenece(Nodo nodo, int x) {
        if (nodo != null) {
            if (nodo.getDato() == x) {
                return true;
            }
            return pertenece(nodo.getIzq(), x) || pertenece(nodo.getDer(), x);
        }
        return false;
    }

    protected static class Nodo {
        private int dato;
        private Nodo izq;
        private Nodo der;

        public Nodo(int dato) {
            this.dato = dato;
            this.izq = null;
            this.der = null;
        }

        public Nodo(int dato, Nodo izq, Nodo der) {
            this.dato = dato;
            this.izq = izq;
            this.der = der;
        }

        public int getDato() {
            return dato;
        }

        public void setDato(int dato) {
            this.dato = dato;
        }

        public Nodo getIzq() {
            return izq;
        }

        public void setIzq(Nodo izq) {
            this.izq = izq;
        }

        public Nodo getDer() {
            return der;
        }

        public void setDer(Nodo der) {
            this.der = der;
        }

        public boolean isHoja() {
            return this.der == null && this.izq == null;
        }
    }
}

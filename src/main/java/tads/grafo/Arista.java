package tads.grafo;

public class Arista {

    private boolean existe;
    private int distancia;
    private int tiempo;

    public Arista() {
        this.existe = false;
        this.distancia = 0;
        this.tiempo = 0;
    }

    public Arista(int distancia, int tiempo) {
        this.existe = true;
        this.distancia = distancia;
        this.tiempo = tiempo;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
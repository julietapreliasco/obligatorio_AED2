package tads.grafo;

public class ResultadoDijkstra {
    private int distancia;
    private int tiempo;
    private String camino;
    private boolean existeCamino;

    public ResultadoDijkstra(int distancia, int tiempo, String camino, boolean existeCamino) {
        this.distancia = distancia;
        this.tiempo = tiempo;
        this.camino = camino;
        this.existeCamino = existeCamino;
    }

    public int getDistancia() {
        return distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public String getCamino() {
        return camino;
    }

    public boolean isExisteCamino() {
        return existeCamino;
    }
}
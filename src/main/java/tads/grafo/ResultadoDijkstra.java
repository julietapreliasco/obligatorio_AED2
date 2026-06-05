package tads.grafo;

public class ResultadoDijkstra {
    private int distancia;
    private String camino;
    private boolean existeCamino;

    public ResultadoDijkstra(int distancia, String camino, boolean existeCamino) {
        this.distancia = distancia;
        this.camino = camino;
        this.existeCamino = existeCamino;
    }

    public int getDistancia() {
        return distancia;
    }

    public String getCamino() {
        return camino;
    }

    public boolean isExisteCamino() {
        return existeCamino;
    }
}
package dominio;

public class ResultadoBusqueda<T> {
    private T dato;
    private int cantRecorridos;

    public ResultadoBusqueda(T dato, int cantRecorridos) {
        this.dato = dato;
        this.cantRecorridos = cantRecorridos;
    }

    public T getDato() {
        return dato;
    }

    public int getCantRecorridos() {
        return cantRecorridos;
    }

    public boolean encontrado() {
        return dato != null;
    }
}

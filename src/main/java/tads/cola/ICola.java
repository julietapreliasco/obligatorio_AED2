package tads.cola;

public interface ICola<T> {
    void encolar(T dato);

    T desencolar();

    T peek();

    boolean esVacia();

    String serializar(String separador);
}

package tads.pila;

public interface IPila<T> extends Iterable<T> {
    void push(T dato);

    T top();

    T pop();

    boolean esVacia();

    boolean estaLlena();
}

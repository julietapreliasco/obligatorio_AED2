package tads.lista;

public interface ILista<T> extends Iterable<T>{

    public void insertarAlInicio(T dato);

    public int largo();

    public boolean esVacia();

}

package tads.grafo;

import java.util.Objects;

public class Arista {

    private boolean existe;
    private int peso;

    public Arista() {
        this.existe = false;
        this.peso = 0;
    }

    public Arista(int unPeso) {
        this.existe = true;
        this.peso = unPeso;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Arista arista = (Arista) o;
        return existe == arista.existe && peso == arista.peso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(existe, peso);
    }
}

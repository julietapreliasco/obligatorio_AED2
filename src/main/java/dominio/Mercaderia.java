package dominio;

import interfaz.Categoria;

public class Mercaderia implements Comparable<Mercaderia> {
    private String id;
    private String codigo;
    private String descripcion;
    private boolean fragil;
    private Categoria categoria;

    public Mercaderia(String id) {
        this.id = id;
    }

    public Mercaderia(String id, String codigo, String descripcion, boolean fragil, Categoria categoria) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fragil = fragil;
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int compareTo(Mercaderia o) {
        return this.id.compareTo(o.id);
    }
}

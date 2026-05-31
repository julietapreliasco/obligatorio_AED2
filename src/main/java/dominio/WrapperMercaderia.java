package dominio;

public class WrapperMercaderia implements Comparable<WrapperMercaderia> {
    // Wrapper de Mercaderia
    private Mercaderia mercaderia;

    public WrapperMercaderia(Mercaderia mercaderia) {
        this.mercaderia = mercaderia;
    }

    public Mercaderia getMercaderia() {
        return mercaderia;
    }

    public void setMercaderia(Mercaderia mercaderia) {
        this.mercaderia = mercaderia;
    }

    @Override
    public int compareTo(WrapperMercaderia o) {
        return this.mercaderia.getCodigo().compareTo(o.getMercaderia().getCodigo());
    }

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder();
        aux.append(mercaderia.getId());
        aux.append(";");
        aux.append(mercaderia.getCodigo());
        aux.append(";");
        aux.append(mercaderia.getDescripcion());
        aux.append(";");
        aux.append(mercaderia.isFragil());
        aux.append(";");
        aux.append(mercaderia.getCategoria().getTexto());

        return aux.toString();
    }
}

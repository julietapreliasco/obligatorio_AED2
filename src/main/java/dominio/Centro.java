package dominio;

public class Centro implements Comparable<Centro> {
    private String codigo;
    private String nombre;
    private String departamento;
    private String direccion;

    public Centro(String codigo) {
        this.codigo = codigo;
    }

    public Centro(String codigo, String nombre, String departamento, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public int compareTo(Centro o) {
        return this.codigo.compareTo(o.codigo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(codigo);
        sb.append(";");
        sb.append(nombre);
        sb.append(";");
        sb.append(departamento);
        sb.append(";");
        sb.append(direccion);

        return sb.toString();
    }
}

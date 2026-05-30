package sistema;

import dominio.Centro;
import dominio.Mercaderia;
import dominio.WrapperMercaderia;
import interfaz.*;
import tads.ABB.ABB;
import tads.grafo.Grafo;
import tads.lista.ListaImp;

public class ImplementacionSistema implements Sistema {

    private Grafo<Centro> grafoCentros;
    private int maxCentros;

    private ABB<Mercaderia> abbPorId;
    private ABB<WrapperMercaderia> abbPorCodigo;
    private ABB<Mercaderia>[] abbCategorias;

    @Override
    public Retorno inicializarSistema(int maxCentros) {
        if (maxCentros <= 3) {
            return Retorno.error1("maxCentros debe ser mayor a 3");
        }

        grafoCentros = new Grafo<>(maxCentros, false);

        abbPorId = new ABB<>();
        abbPorCodigo = new ABB<>();
        abbCategorias = new ABB[Categoria.values().length];

        for (Categoria cat : Categoria.values()) {
            abbCategorias[cat.getIndice()] = new ABB<>();
        }

        return Retorno.ok();
    }

    private boolean parametrosValidos(String... datos) {
        for (String dato : datos) {
            if (dato == null || dato.isEmpty() || dato.isBlank()) {
                return false;
            }
        }
        return true;
    }

    private boolean codigoMercaderiaValido(String codigo) {
        return codigo.matches("^[a-zA-Z]{2}-[0-9]{3}-[a-zA-Z0-9]{6}$");
    }

    @Override
    public Retorno registrarMercaderia(String id, String codigo, String descripcion, boolean fragil,
            Categoria categoria) {
        // Si alguno de los parámetros es vacío o null.
        if (categoria == null || !parametrosValidos(id, codigo, descripcion)) {
            return Retorno.error1("Todos los campos son requeridos");
        }
        // Si codigo no tiene el formato válido.
        if (!codigoMercaderiaValido(codigo)) {
            return Retorno.error2("El código no tiene el formato válido");
        }
        // Si ya existe una mercadería registrada con ese id.
        if (abbPorId.pertenece(new Mercaderia(id))) {
            return Retorno.error3("Ya existe una mercadería registrada con ese id");
        }
        // Si ya existe una mercadería registrada con ese codigo.
        if (abbPorCodigo.pertenece(new WrapperMercaderia(new Mercaderia(null, codigo, null, false, null)))) {
            return Retorno.error4("Ya existe una mercadería registrada con ese código");
        }

        Mercaderia mercaderia = new Mercaderia(id, codigo, descripcion, fragil, categoria);
        abbPorId.insertar(mercaderia);
        WrapperMercaderia wrapperMercaderia = new WrapperMercaderia(mercaderia);
        abbPorCodigo.insertar(wrapperMercaderia);

        abbCategorias[categoria.getIndice()].insertar(mercaderia);
        return Retorno.ok();
    }

    @Override
    public Retorno buscarMercaderiaPorId(String id) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMercaderiasPorIdAscendente() {
        ListaImp<Mercaderia> lista = abbPorId.listarAsc();
        StringBuilder aux = new StringBuilder();
        for (Mercaderia m : lista) {
            if (aux.length() > 0) {
                aux.append("|");
            }
            aux.append(m.toString());
        }
        return Retorno.ok(aux.toString());
    }

    @Override
    public Retorno listarMercaderiasPorIdDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarMercaderiaPorCodigo(String codigo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMercaderiasPorCodigoAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMercaderiasPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCentroLogistico(String codigo, String nombre, String departamento, String direccion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoOrigen, String codigoDestino, int distancia, int tiempo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno redCentrosPorCantidadDeConexiones(String codigoOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoDistancia(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoTiempo(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }
}

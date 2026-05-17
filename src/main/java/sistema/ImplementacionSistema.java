package sistema;

import dominio.Centro;
import interfaz.*;
import tads.grafo.Grafo;

public class ImplementacionSistema implements Sistema  {

    private Grafo<Centro> grafoCentros;
    private int maxCentros;

    @Override
    public Retorno inicializarSistema(int maxCentros) {
        if (maxCentros <= 3) {
            return Retorno.error1("maxCentros debe ser mayor a 3");
        }

        grafoCentros = new Grafo<>(maxCentros, false);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarMercaderia(String id, String codigo, String descripcion, boolean fragil, Categoria categoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarMercaderiaPorId(String id) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarMercaderiasPorIdAscendente() {
        return Retorno.noImplementada();
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

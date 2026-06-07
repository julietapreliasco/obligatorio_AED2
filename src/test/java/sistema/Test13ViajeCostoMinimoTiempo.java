package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test13ViajeCostoMinimoTiempo {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);

        s.registrarCentroLogistico("C01", "Centro Montevideo", "Montevideo", "Av. 18 de Julio");
        s.registrarCentroLogistico("C02", "Centro Canelones", "Canelones", "Ruta 5");
        s.registrarCentroLogistico("C03", "Centro Maldonado", "Maldonado", "Av. Roosevelt");
        s.registrarCentroLogistico("C04", "Centro Salto", "Salto", "Av. Uruguay");
        s.registrarCentroLogistico("C05", "Centro Colonia", "Colonia", "Rambla");
        s.registrarCentroLogistico("C06", "Centro Rivera", "Rivera", "Sarandi");

        /*
            Grafo armado:
            Conexiones (Distancia, Tiempo en minutos):
            C01 -> C02 = Dist: 100, Tiempo: 60
            C01 -> C03 = Dist: 50,  Tiempo: 90
            C03 -> C02 = Dist: 20,  Tiempo: 30
            C02 -> C04 = Dist: 100, Tiempo: 120
            C03 -> C05 = Dist: 200, Tiempo: 100
            C04 -> C06 = Dist: 80,  Tiempo: 45
            C05 -> C06 = Dist: 20,  Tiempo: 20

            Caminos posibles de C01 a C06:
            1) C01 -> C02 -> C04 -> C06
               Tiempo: 60 + 120 + 45 = 225 min
               Distancia: 100 + 100 + 80 = 280
            2) C01 -> C03 -> C02 -> C04 -> C06
               Tiempo: 90 + 30 + 120 + 45 = 285 min
               Distancia: 50 + 20 + 100 + 80 = 250 (Más corto en distancia, pero NO en tiempo)
            3) C01 -> C03 -> C05 -> C06
               Tiempo: 90 + 100 + 20 = 210 min
               Distancia: 50 + 200 + 20 = 270

            Para costo mínimo en tiempo, debe seleccionar: C01 -> C03 -> C05 -> C06 (210 minutos).
        */

        s.registrarConexion("C01", "C02", 100, 60);
        s.registrarConexion("C01", "C03", 50, 90);
        s.registrarConexion("C03", "C02", 20, 30);
        s.registrarConexion("C02", "C04", 100, 120);
        s.registrarConexion("C03", "C05", 200, 100);
        s.registrarConexion("C04", "C06", 80, 45);
        s.registrarConexion("C05", "C06", 20, 20);
    }

    @Test
    void viajeCostoMinimoTiempoOk() {
        retorno = s.viajeCostoMinimoTiempo("C01", "C06");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(210, retorno.getValorInteger());
        assertEquals(
                "C01;Centro Montevideo;Montevideo;Av. 18 de Julio|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C05;Centro Colonia;Colonia;Rambla|C06;Centro Rivera;Rivera;Sarandi",
                retorno.getValorString()
        );
    }

    @Test
    void viajeCostoMinimoTiempoOkCaminoDirectoNoEsElMasRapido() {
        /*
            Agrego conexión directa C01 -> C06 con distancia muy baja pero tiempo muy alto (500 minutos).
            Dijkstra de tiempo debe seguir eligiendo el camino de 210 minutos.
        */
        s.registrarConexion("C01", "C06", 10, 500);

        retorno = s.viajeCostoMinimoTiempo("C01", "C06");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(210, retorno.getValorInteger());
        assertEquals(
                "C01;Centro Montevideo;Montevideo;Av. 18 de Julio|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C05;Centro Colonia;Colonia;Rambla|C06;Centro Rivera;Rivera;Sarandi",
                retorno.getValorString()
        );
    }

    @Test
    void viajeCostoMinimoTiempoError1CodigoVacioONull() {
        retorno = s.viajeCostoMinimoTiempo("", "C06");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoTiempo(null, "C06");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoTiempo("   ", "C06");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoTiempo("C01", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoTiempo("C01", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoTiempo("C01", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void viajeCostoMinimoTiempoError2NoExisteCentroOrigen() {
        retorno = s.viajeCostoMinimoTiempo("C99", "C06");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void viajeCostoMinimoTiempoError3NoExisteCentroDestino() {
        retorno = s.viajeCostoMinimoTiempo("C01", "C99");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void viajeCostoMinimoTiempoError4NoExisteCamino() {
        /*
            C06 existe, pero no hay camino desde C06 hacia C01
            porque el grafo es dirigido y todas las conexiones van en otro sentido.
        */
        retorno = s.viajeCostoMinimoTiempo("C06", "C01");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }
}

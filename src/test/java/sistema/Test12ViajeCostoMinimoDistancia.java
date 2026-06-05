package sistema;

import interfaz.Retorno;

import interfaz.Sistema;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test12ViajeCostoMinimoDistancia {

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

            C01 -> C02 = 100

            C01 -> C03 = 50

            C03 -> C02 = 20

            C02 -> C04 = 100

            C03 -> C05 = 200

            C04 -> C06 = 80

            C05 -> C06 = 20

            Camino mínimo de C01 a C06:

            C01 -> C03 -> C02 -> C04 -> C06

            Distancia total:

            50 + 20 + 100 + 80 = 250

            Hay otro camino:

            C01 -> C03 -> C05 -> C06

            50 + 200 + 20 = 270

            Por eso debería elegir el de 250.

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

    void viajeCostoMinimoDistanciaOk() {

        retorno = s.viajeCostoMinimoDistancia("C01", "C06");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(250, retorno.getValorInteger());

        assertEquals(

                "C01;Centro Montevideo;Montevideo;Av. 18 de Julio|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C02;Centro Canelones;Canelones;Ruta 5|C04;Centro Salto;Salto;Av. Uruguay|C06;Centro Rivera;Rivera;Sarandi",

                retorno.getValorString()

        );

    }

    @Test

    void viajeCostoMinimoDistanciaOkCaminoDirectoNoEsElMasCorto() {

        /*

            Agrego conexión directa C01 -> C06, pero más cara.

            Dijkstra debería seguir eligiendo el camino de costo 250.

        */

        s.registrarConexion("C01", "C06", 500, 10);

        retorno = s.viajeCostoMinimoDistancia("C01", "C06");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(250, retorno.getValorInteger());

        assertEquals(

                "C01;Centro Montevideo;Montevideo;Av. 18 de Julio|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C02;Centro Canelones;Canelones;Ruta 5|C04;Centro Salto;Salto;Av. Uruguay|C06;Centro Rivera;Rivera;Sarandi",

                retorno.getValorString()

        );

    }

    @Test

    void viajeCostoMinimoDistanciaError1CodigoVacioONull() {

        retorno = s.viajeCostoMinimoDistancia("", "C06");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDistancia(null, "C06");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDistancia("   ", "C06");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDistancia("C01", "");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDistancia("C01", null);

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.viajeCostoMinimoDistancia("C01", "   ");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test

    void viajeCostoMinimoDistanciaError2NoExisteCentroOrigen() {

        retorno = s.viajeCostoMinimoDistancia("C99", "C06");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    @Test

    void viajeCostoMinimoDistanciaError3NoExisteCentroDestino() {

        retorno = s.viajeCostoMinimoDistancia("C01", "C99");

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

    }

    @Test

    void viajeCostoMinimoDistanciaError4NoExisteCamino() {

        /*

            C06 existe, pero no hay camino desde C06 hacia C01

            porque el grafo es dirigido y todas las conexiones van en otro sentido.

        */

        retorno = s.viajeCostoMinimoDistancia("C06", "C01");

        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

    }

}

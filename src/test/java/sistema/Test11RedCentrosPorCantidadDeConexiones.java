package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11RedCentrosPorCantidadDeConexiones {

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

            C01 -> C02
            C01 -> C03
            C02 -> C04
            C03 -> C05
            C05 -> C06

            Niveles desde C01:
            C01 nivel 0
            C02 y C03 nivel 1
            C04 y C05 nivel 2
            C06 nivel 3
        */

        s.registrarConexion("C01", "C02", 100, 60);
        s.registrarConexion("C01", "C03", 150, 90);
        s.registrarConexion("C02", "C04", 200, 120);
        s.registrarConexion("C03", "C05", 180, 100);
        s.registrarConexion("C05", "C06", 90, 45);
    }

    @Test
    void redCentrosOkCantidadUno() {
        retorno = s.redCentrosPorCantidadDeConexiones("C01", 1);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "C02;Centro Canelones;Canelones;Ruta 5|C03;Centro Maldonado;Maldonado;Av. Roosevelt",
                retorno.getValorString()
        );
    }

    @Test
    void redCentrosOkCantidadDos() {
        retorno = s.redCentrosPorCantidadDeConexiones("C01", 2);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "C02;Centro Canelones;Canelones;Ruta 5|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C04;Centro Salto;Salto;Av. Uruguay|C05;Centro Colonia;Colonia;Rambla",
                retorno.getValorString()
        );
    }

    @Test
    void redCentrosOkCantidadTres() {
        retorno = s.redCentrosPorCantidadDeConexiones("C01", 3);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "C02;Centro Canelones;Canelones;Ruta 5|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C04;Centro Salto;Salto;Av. Uruguay|C05;Centro Colonia;Colonia;Rambla|C06;Centro Rivera;Rivera;Sarandi",
                retorno.getValorString()
        );
    }

    @Test
    void redCentrosOkCantidadCero() {
        retorno = s.redCentrosPorCantidadDeConexiones("C01", 0);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void redCentrosOkSinRepetidos() {
        /*
            Agrego otro camino hacia C04:

            C01 -> C03 -> C04

            C04 ya era alcanzable por:
            C01 -> C02 -> C04

            Igual debe aparecer una sola vez.
        */

        s.registrarConexion("C03", "C04", 50, 30);

        retorno = s.redCentrosPorCantidadDeConexiones("C01", 2);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "C02;Centro Canelones;Canelones;Ruta 5|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C04;Centro Salto;Salto;Av. Uruguay|C05;Centro Colonia;Colonia;Rambla",
                retorno.getValorString()
        );
    }

    @Test
    void redCentrosOkConCicloNoIncluyeOrigen() {
        /*
            Agrego una conexión que vuelve al origen:

            C02 -> C01

            No debería aparecer C01 en el resultado.
        */

        s.registrarConexion("C02", "C01", 100, 60);

        retorno = s.redCentrosPorCantidadDeConexiones("C01", 2);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "C02;Centro Canelones;Canelones;Ruta 5|C03;Centro Maldonado;Maldonado;Av. Roosevelt|C04;Centro Salto;Salto;Av. Uruguay|C05;Centro Colonia;Colonia;Rambla",
                retorno.getValorString()
        );
    }

    @Test
    void redCentrosError1CantidadNegativa() {
        retorno = s.redCentrosPorCantidadDeConexiones("C01", -1);

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void redCentrosError2CodigoOrigenVacioONull() {
        retorno = s.redCentrosPorCantidadDeConexiones("", 2);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.redCentrosPorCantidadDeConexiones(null, 2);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.redCentrosPorCantidadDeConexiones("   ", 2);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void redCentrosError3NoExisteCentroOrigen() {
        retorno = s.redCentrosPorCantidadDeConexiones("C99", 2);

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
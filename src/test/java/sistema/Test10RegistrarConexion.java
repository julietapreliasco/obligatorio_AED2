package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10RegistrarConexion {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);

        s.registrarCentroLogistico("C01", "Centro Montevideo", "Montevideo", "Av. 18 de Julio");
        s.registrarCentroLogistico("C02", "Centro Canelones", "Canelones", "Ruta 5");
        s.registrarCentroLogistico("C03", "Centro Maldonado", "Maldonado", "Av. Roosevelt");
        s.registrarCentroLogistico("C04", "Centro Salto", "Salto", "Av. Uruguay");
    }

    @Test
    void registrarConexionOk() {
        retorno = s.registrarConexion("C01", "C02", 120, 90);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarConexionOkSentidoInverso() {
        s.registrarConexion("C01", "C02", 120, 90);

        retorno = s.registrarConexion("C02", "C01", 120, 95);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarConexionError1ParametrosVaciosONull() {
        retorno = s.registrarConexion("", "C02", 120, 90);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("C01", "", 120, 90);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion(null, "C02", 120, 90);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("C01", null, 120, 90);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("   ", "C02", 120, 90);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("C01", "   ", 120, 90);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarConexionError2NoExisteOrigen() {
        retorno = s.registrarConexion("C99", "C02", 120, 90);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarConexionError3NoExisteDestino() {
        retorno = s.registrarConexion("C01", "C99", 120, 90);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void registrarConexionError4DistanciaMenorOIgualACero() {
        retorno = s.registrarConexion("C01", "C02", 0, 90);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarConexion("C01", "C02", -10, 90);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void registrarConexionError5TiempoMenorOIgualACero() {
        retorno = s.registrarConexion("C01", "C02", 120, 0);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());

        retorno = s.registrarConexion("C01", "C02", 120, -15);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void registrarConexionError6YaExisteConexion() {
        s.registrarConexion("C01", "C02", 120, 90);

        retorno = s.registrarConexion("C01", "C02", 130, 100);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }
}
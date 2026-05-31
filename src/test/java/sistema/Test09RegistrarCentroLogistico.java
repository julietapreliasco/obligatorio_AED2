package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09RegistrarCentroLogistico {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarCentroLogisticoOk() {
        retorno = s.registrarCentroLogistico("C01", "Centro Montevideo", "Montevideo", "Av. 18 de Julio");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarCentroLogisticoError1() {
        Sistema s2 = new ImplementacionSistema();
        s2.inicializarSistema(4);

        s2.registrarCentroLogistico("C01", "Centro 1", "Montevideo", "Dirección 1");
        s2.registrarCentroLogistico("C02", "Centro 2", "Canelones", "Dirección 2");
        s2.registrarCentroLogistico("C03", "Centro 3", "Maldonado", "Dirección 3");
        s2.registrarCentroLogistico("C04", "Centro 4", "Colonia", "Dirección 4");

        retorno = s2.registrarCentroLogistico("C05", "Centro 5", "Salto", "Dirección 5");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarCentroLogisticoError2() {
        retorno = s.registrarCentroLogistico("", "Centro 1", "Montevideo", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "", "Montevideo", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "Centro 1", "", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "Centro 1", "Montevideo", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico(null, "Centro 1", "Montevideo", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", null, "Montevideo", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "Centro 1", null, "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "Centro 1", "Montevideo", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("   ", "Centro 1", "Montevideo", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "   ", "Montevideo", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "Centro 1", "   ", "Dirección 1");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarCentroLogistico("C01", "Centro 1", "Montevideo", "   ");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarCentroLogisticoError3() {
        s.registrarCentroLogistico("C01", "Centro Montevideo", "Montevideo", "Dirección 1");
        retorno = s.registrarCentroLogistico("C01", "Otro nombre", "Canelones", "Otra dirección");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
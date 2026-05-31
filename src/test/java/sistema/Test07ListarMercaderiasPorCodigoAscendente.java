package sistema;

import dominio.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test07ListarMercaderiasPorCodigoAscendente {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarMercaderiasPorCodigoAscendenteOk() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);
        s.registrarMercaderia("COD08", "XX-008-XXX123", "Descripción 8", false, Categoria.OTROS);
        s.registrarMercaderia("COD02", "XX-002-XXX123", "Descripción 2", false, Categoria.OTROS);
        s.registrarMercaderia("COD04", "XX-004-XXX123", "Descripción 4", true, Categoria.OTROS);
        s.registrarMercaderia("COD07", "XX-007-XXX123", "Descripción 7", false, Categoria.OTROS);
        s.registrarMercaderia("COD09", "XX-009-XXX123", "Descripción 9", true, Categoria.OTROS);

        retorno = s.listarMercaderiasPorCodigoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD02;XX-002-XXX123;Descripción 2;false;Otros"
                        + "|COD03;XX-003-XXX123;Descripción 3;true;Otros"
                        + "|COD04;XX-004-XXX123;Descripción 4;true;Otros"
                        + "|COD05;XX-005-XXX123;Descripción 5;false;Otros"
                        + "|COD07;XX-007-XXX123;Descripción 7;false;Otros"
                        + "|COD08;XX-008-XXX123;Descripción 8;false;Otros"
                        + "|COD09;XX-009-XXX123;Descripción 9;true;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorCodigoAscendenteConUnaSolaMercaderia() {
        s.registrarMercaderia("COD01", "XX-001-XXX123", "Descripción 1", false, Categoria.OTROS);

        retorno = s.listarMercaderiasPorCodigoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "COD01;XX-001-XXX123;Descripción 1;false;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorCodigoAscendenteSinMercaderias() {
        retorno = s.listarMercaderiasPorCodigoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listarMercaderiasPorCodigoAscendenteNoOrdenaPorId() {
        s.registrarMercaderia("COD05", "BB-002-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD10", "AA-001-XXX123", "Descripción 1", true, Categoria.OTROS);
        s.registrarMercaderia("COD09", "CC-003-XXX123", "Descripción 9", false, Categoria.OTROS);

        retorno = s.listarMercaderiasPorCodigoAscendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD10;AA-001-XXX123;Descripción 1;true;Otros"
                        + "|COD05;BB-002-XXX123;Descripción 5;false;Otros"
                        + "|COD09;CC-003-XXX123;Descripción 9;false;Otros",
                retorno.getValorString()
        );
    }
}
package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05ListarMercaderiasPorIdDescendente {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarMercaderiasPorIdDescendenteOk() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);
        s.registrarMercaderia("COD08", "XX-008-XXX123", "Descripción 8", false, Categoria.OTROS);
        s.registrarMercaderia("COD02", "XX-002-XXX123", "Descripción 2", false, Categoria.OTROS);
        s.registrarMercaderia("COD04", "XX-004-XXX123", "Descripción 4", true, Categoria.OTROS);
        s.registrarMercaderia("COD07", "XX-007-XXX123", "Descripción 7", false, Categoria.OTROS);
        s.registrarMercaderia("COD09", "XX-009-XXX123", "Descripción 9", true, Categoria.OTROS);

        retorno = s.listarMercaderiasPorIdDescendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD09;XX-009-XXX123;Descripción 9;true;Otros"
                        + "|COD08;XX-008-XXX123;Descripción 8;false;Otros"
                        + "|COD07;XX-007-XXX123;Descripción 7;false;Otros"
                        + "|COD05;XX-005-XXX123;Descripción 5;false;Otros"
                        + "|COD04;XX-004-XXX123;Descripción 4;true;Otros"
                        + "|COD03;XX-003-XXX123;Descripción 3;true;Otros"
                        + "|COD02;XX-002-XXX123;Descripción 2;false;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorIdDescendenteConUnaSolaMercaderia() {
        s.registrarMercaderia("COD01", "XX-001-XXX123", "Descripción 1", false, Categoria.OTROS);

        retorno = s.listarMercaderiasPorIdDescendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "COD01;XX-001-XXX123;Descripción 1;false;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorIdDescendenteSinMercaderias() {
        retorno = s.listarMercaderiasPorIdDescendente();

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }
}
package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test08ListarMercaderiasPorCategoria {

    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarMercaderiasPorCategoriaOk() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);
        s.registrarMercaderia("COD08", "XX-008-XXX123", "Descripción 8", false, Categoria.OTROS);
        s.registrarMercaderia("COD02", "XX-002-XXX123", "Descripción 2", false, Categoria.OTROS);

        // Estas no deberían aparecer porque son de otra categoría
        s.registrarMercaderia("COD01", "AA-001-AAA123", "Descripción 1", true, Categoria.TEXTIL);
        s.registrarMercaderia("COD04", "AA-004-AAA123", "Descripción 4", false, Categoria.TEXTIL);

        retorno = s.listarMercaderiasPorCategoria(Categoria.OTROS);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD02;XX-002-XXX123;Descripción 2;false;Otros"
                        + "|COD03;XX-003-XXX123;Descripción 3;true;Otros"
                        + "|COD05;XX-005-XXX123;Descripción 5;false;Otros"
                        + "|COD08;XX-008-XXX123;Descripción 8;false;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorCategoriaConUnaSolaMercaderia() {
        s.registrarMercaderia("COD01", "XX-001-XXX123", "Descripción 1", false, Categoria.OTROS);

        retorno = s.listarMercaderiasPorCategoria(Categoria.OTROS);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD01;XX-001-XXX123;Descripción 1;false;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorCategoriaSinMercaderiasDeEsaCategoria() {
        s.registrarMercaderia("COD01", "AA-001-AAA123", "Descripción 1", true, Categoria.TEXTIL);
        s.registrarMercaderia("COD02", "AA-002-AAA123", "Descripción 2", false, Categoria.TEXTIL);

        retorno = s.listarMercaderiasPorCategoria(Categoria.OTROS);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listarMercaderiasPorCategoriaSinMercaderiasEnElSistema() {
        retorno = s.listarMercaderiasPorCategoria(Categoria.ALIMENTOS);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listarMercaderiasPorCategoriaOrdenaPorIdNoPorCodigo() {
        s.registrarMercaderia("COD05", "AA-001-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD02", "ZZ-999-XXX123", "Descripción 2", true, Categoria.OTROS);
        s.registrarMercaderia("COD09", "BB-002-XXX123", "Descripción 9", false, Categoria.OTROS);

        retorno = s.listarMercaderiasPorCategoria(Categoria.OTROS);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD02;ZZ-999-XXX123;Descripción 2;true;Otros"
                        + "|COD05;AA-001-XXX123;Descripción 5;false;Otros"
                        + "|COD09;BB-002-XXX123;Descripción 9;false;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorCategoriaNoIncluyeOtrasCategoriasAunqueElIdEsteIntercalado() {
        s.registrarMercaderia("COD01", "AA-001-AAA123", "Descripción 1", true, Categoria.TEXTIL);
        s.registrarMercaderia("COD02", "XX-002-XXX123", "Descripción 2", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "AA-003-AAA123", "Descripción 3", false, Categoria.TEXTIL);
        s.registrarMercaderia("COD04", "XX-004-XXX123", "Descripción 4", true, Categoria.OTROS);
        s.registrarMercaderia("COD05", "AA-005-AAA123", "Descripción 5", true, Categoria.TEXTIL);

        retorno = s.listarMercaderiasPorCategoria(Categoria.OTROS);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD02;XX-002-XXX123;Descripción 2;false;Otros"
                        + "|COD04;XX-004-XXX123;Descripción 4;true;Otros",
                retorno.getValorString()
        );
    }

    @Test
    void listarMercaderiasPorCategoriaTextil() {
        s.registrarMercaderia("COD03", "TX-003-AAA123", "Campera", false, Categoria.TEXTIL);
        s.registrarMercaderia("COD01", "TX-001-AAA123", "Pantalón", true, Categoria.TEXTIL);
        s.registrarMercaderia("COD02", "AL-002-AAA123", "Arroz", false, Categoria.ALIMENTOS);

        retorno = s.listarMercaderiasPorCategoria(Categoria.TEXTIL);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        assertEquals(
                "COD01;TX-001-AAA123;Pantalón;true;Textil"
                        + "|COD03;TX-003-AAA123;Campera;false;Textil",
                retorno.getValorString()
        );
    }
}
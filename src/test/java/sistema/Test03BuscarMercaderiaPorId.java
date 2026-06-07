
package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test03BuscarMercaderiaPorId {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarMedicamentoOk() {
        s.registrarMercaderia("COD01", "XX-001-XXX123", "Descripción 1", false, Categoria.OTROS);
        retorno = s.buscarMercaderiaPorId("COD01");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorInteger());
        assertEquals("COD01;XX-001-XXX123;Descripción 1;false;Otros", retorno.getValorString());
    }

    @Test
    void buscarMercaderiaOkEnRaiz() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);
        s.registrarMercaderia("COD08", "XX-008-XXX123", "Descripción 8", false, Categoria.OTROS);

        retorno = s.buscarMercaderiaPorId("COD05");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(1, retorno.getValorInteger());
        assertEquals("COD05;XX-005-XXX123;Descripción 5;false;Otros", retorno.getValorString());
    }

    @Test
    void buscarMercaderiaOkEnSubarbolIzquierdo() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);
        s.registrarMercaderia("COD08", "XX-008-XXX123", "Descripción 8", false, Categoria.OTROS);

        retorno = s.buscarMercaderiaPorId("COD03");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("COD03;XX-003-XXX123;Descripción 3;true;Otros", retorno.getValorString());
    }

    @Test
    void buscarMercaderiaOkEnSubarbolDerecho() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);
        s.registrarMercaderia("COD08", "XX-008-XXX123", "Descripción 8", false, Categoria.OTROS);

        retorno = s.buscarMercaderiaPorId("COD08");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("COD08;XX-008-XXX123;Descripción 8;false;Otros", retorno.getValorString());
    }

    @Test
    void buscarMercaderiaOkEnNivelMasProfundo() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);
        s.registrarMercaderia("COD08", "XX-008-XXX123", "Descripción 8", false, Categoria.OTROS);
        s.registrarMercaderia("COD02", "XX-002-XXX123", "Descripción 2", false, Categoria.OTROS);

        retorno = s.buscarMercaderiaPorId("COD02");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3, retorno.getValorInteger());
        assertEquals("COD02;XX-002-XXX123;Descripción 2;false;Otros", retorno.getValorString());
    }

    @Test
    void buscarMercaderiaNoExiste() {
        s.registrarMercaderia("COD05", "XX-005-XXX123", "Descripción 5", false, Categoria.OTROS);
        s.registrarMercaderia("COD03", "XX-003-XXX123", "Descripción 3", true, Categoria.OTROS);

        retorno = s.buscarMercaderiaPorId("COD99");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void buscarMercaderiaConIdNullRetornaError1() {
        retorno = s.buscarMercaderiaPorId(null);

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarMercaderiaConIdVacioRetornaError1() {
        retorno = s.buscarMercaderiaPorId("");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void buscarMercaderiaConIdEnBlancoRetornaError1() {
        retorno = s.buscarMercaderiaPorId("   ");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
}

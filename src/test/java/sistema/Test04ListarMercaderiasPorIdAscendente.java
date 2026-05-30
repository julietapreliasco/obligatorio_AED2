package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Test04ListarMercaderiasPorIdAscendente {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarMercaderiasPorIdAscendenteVacio() {
        retorno = s.listarMercaderiasPorIdAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
        assertNull(retorno.getValorInteger());
    }

    @Test
    void listarMercaderiasPorIdAscendenteOk() {
        s.registrarMercaderia("12345", "MN-001-ABC123", "Batería de cocina", false, Categoria.OTROS);
        s.registrarMercaderia("23456", "SJ-003-CDE345", "Ropa de invierno", false, Categoria.TEXTIL);

        retorno = s.listarMercaderiasPorIdAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "12345;MN-001-ABC123;Batería de cocina;false;Otros|23456;SJ-003-CDE345;Ropa de invierno;false;Textil",
                retorno.getValorString());
        assertNull(retorno.getValorInteger());
    }

    @Test
    void listarMercaderiasPorIdAscendenteOrdenLexicografico() {
        s.registrarMercaderia("9", "XX-001-XXX111", "Nueve", false, Categoria.OTROS);
        s.registrarMercaderia("10", "XX-001-XXX222", "Diez", false, Categoria.OTROS);
        s.registrarMercaderia("2", "XX-001-XXX333", "Dos", false, Categoria.OTROS);

        retorno = s.listarMercaderiasPorIdAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "10;XX-001-XXX222;Diez;false;Otros|2;XX-001-XXX333;Dos;false;Otros|9;XX-001-XXX111;Nueve;false;Otros",
                retorno.getValorString());
    }

    @Test
    void listarMercaderiasPorIdAscendenteIndependienteDelOrdenDeRegistro() {
        s.registrarMercaderia("300", "XX-001-XXX300", "Tercera", false, Categoria.TEXTIL);
        s.registrarMercaderia("100", "XX-001-XXX100", "Primera", true, Categoria.OTROS);
        s.registrarMercaderia("200", "XX-001-XXX200", "Segunda", false, Categoria.ALIMENTOS);

        retorno = s.listarMercaderiasPorIdAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(
                "100;XX-001-XXX100;Primera;true;Otros|200;XX-001-XXX200;Segunda;false;Alimentos|300;XX-001-XXX300;Tercera;false;Textil",
                retorno.getValorString());
    }
}

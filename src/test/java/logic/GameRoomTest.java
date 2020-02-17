package logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/*******************************************************************************
 * Testovací třída GameRoomTest slouží ke komplexnímu otestování
 * třídy GameRoom
 *
 * @author    Dmitrii Savinykh
 * @version   1.0
 */


public class GameRoomTest {

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */

    @Before
    public void setUp() throws Exception {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */

    @After
    public void tearDown() throws Exception {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře,
     */

    @Test
    public  void testLzeProjit() {
        GameRoom prostor1 = new GameRoom("hala", "vstupní hala budovy VŠE na Jižním městě");
        GameRoom prostor2 = new GameRoom("bufet", "bufet, kam si můžete zajít na svačinku");
        prostor1.setEnters(prostor2);
        prostor2.setEnters(prostor1);
        assertEquals(prostor2, prostor1.getNextRoom("bufet"));
        assertEquals(null, prostor2.getNextRoom("pokoj"));
    }

    @Test
    public void testVeci()
    {
        GameRoom prostor1 = new GameRoom("a", null);
        Thing vec1 = new Thing("a", true);
        Thing vec2 = new Thing("b", true);
        assertEquals(true, prostor1.addThing(vec1));
        assertEquals(true, prostor1.addThing(vec2));
        assertSame(vec1, prostor1.searchThing("a"));
        assertNull(prostor1.searchThing("c"));
        assertEquals(true, prostor1.removeThing(vec1));
        assertNull(prostor1.searchThing("a"));
    }
}
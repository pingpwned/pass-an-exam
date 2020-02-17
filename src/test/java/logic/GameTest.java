package logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Game hra;

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
    public void setUp() {
        hra = new Game();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     *
     */
    @Test
    public void testPrubehHry() {
        assertEquals("rajská_budova", hra.getPlan().getActual().getName());
        hra.commandProcess("jdi knihovna");
        assertEquals(false, hra.isTheEnd());
        assertEquals("Věc byla přidána. Celkový počet věcí je teď: 1", hra.commandProcess("seber bod_k_učení"));
        assertEquals("Knihovna", hra.getPlan().getActual().getName());
        hra.commandProcess("jdi jídelna");
        assertEquals(false, hra.isTheEnd());
        assertEquals("Jídelna", hra.getPlan().getActual().getName());
        hra.commandProcess("konec");
        assertEquals(true, hra.isTheEnd());
    }
}
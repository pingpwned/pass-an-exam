package logic;
/*******************************************************************************
 * Testovací třída CommandListTest slouží ke komplexnímu otestování třídy
 * CommandList
 *
 * @author    Dmitrii Savinykh
 * @version   1.0
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandListTest {

    private Game hra;
    private CommandEnd prKonec;
    private CommandGo prJdi;

    @Before
    public void setUp() {
        hra = new Game();
        prKonec = new CommandEnd(hra);
        prJdi = new CommandGo(hra.getPlan(), hra);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testVlozeniVybrani() {
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(prKonec);
        seznPrikazu.addCommand(prJdi);
        assertEquals(prKonec, seznPrikazu.getCommand("konec"));
        assertEquals(prJdi, seznPrikazu.getCommand("jdi"));
        assertEquals(null, seznPrikazu.getCommand("nápověda"));
    }

    @Test
    public void testJePlatnyPrikaz() {
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(prKonec);
        seznPrikazu.addCommand(prJdi);
        assertEquals(true, seznPrikazu.isValidCommand("konec"));
        assertEquals(true, seznPrikazu.isValidCommand("jdi"));
        assertEquals(false, seznPrikazu.isValidCommand("nápověda"));
        assertEquals(false, seznPrikazu.isValidCommand("Konec"));
    }

    @Test
    public void testNazvyPrikazu() {
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(prKonec);
        seznPrikazu.addCommand(prJdi);
        String nazvy = seznPrikazu.getCommandNames();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
}
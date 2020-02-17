/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package main;

import logic.*;
import guiText.TextGui;

/*******************************************************************************
 * Main je hlavní třídou aplikace,
 * která představuje jednoduchou textovou hru
 *
 * @author    Dmitrii Savinykh
 * @version   1.0
 */

public class Main {
    /***************************************************************************
     * Metoda, spouští celou aplikaci.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {
        IGame game = new Game();
        TextGui gui = new TextGui(game);
        gui.play();
    }
    // constructor
    public Main() {}
}

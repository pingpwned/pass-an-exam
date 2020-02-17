package guiText;

import java.util.Scanner;
import logic.IGame;

/**
 *  Class TextGui
 *
 *  Toto je uživatelské rozhraní aplikace
 *  Třída vytváří instanci třídy Game, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 */

public class TextGui {
    private IGame game;

    /**
     *  Vytváří hru.
     */

    public TextGui(IGame game) {
        this.game = game;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda isTheEnd() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */

    public void play() {
        System.out.println(game.getHello());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!game.isTheEnd()) {
            String command = readComment();
            System.out.println(game.commandProcess(command));
        }

        System.out.println(game.getEpilog());
    }

    /**
     *  Metoda přečte input z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */

    private String readComment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Hráč> ");
        return sc.nextLine();
    }

}

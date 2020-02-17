package logic;
/**
 *  Třída CommandEnd implementuje pro hru příkaz konec.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 *
 */
public class CommandEnd implements ICommand {
    private static final String NAME = "konec";
    private Game game;
    /**
     *  Konstruktor třídy
     *
     *  @param game odkaz na hru, která má být příkazem konec ukončena
     */
    public CommandEnd(Game game) {
        this.game = game;
    }
    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String doCommand(String... params) {
        if(params.length > 0) {
            return "Ukončit co? Druhé slovo je tu navíc.";
        } else {
            game.setGameEnd(true);
            return "Hra byla ukončena příkazem konec";
        }
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getName() {
        return NAME;
    }
}

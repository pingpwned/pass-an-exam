package logic;
/**
 *  Třída CommandHelp implementuje pro hru příkaz nápověda.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 *
 */
public class CommandHelp implements ICommand {
    private static final String NAME = "help";
    private CommandList cl;
    /**
     *  Konstruktor třídy
     *
     *  @param cl seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli.
     */
    public CommandHelp(CommandList cl) {
        this.cl = cl;
    }
    /**
     *  Vrací základní nápovědu po zadání příkazu "help". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     *  @return napoveda ke hre
     */
    @Override
    public String doCommand(String ... params) {
        return "Už brzy budeme chtít jen jedno – dostat zápočet u zkoušky. Už brzy seznámí studenty s základními techniky přípravy ke zkouškám. Úkolem hráče je splnit jednu ze základních technik přípravy a dostat dobrý výsledek u zkoušky. Teď si představíme náš obyčejný den, náměstí W. CH. a starou budovu.. Už začni!\n" +
                "\n" +
                "Cílem je sebrat všechny body, které se nabízí za správný postup. Za chyby se mohou body odečíst. \n" +
                "\n" +
                "Body slouží k úspěšnému závěru u zkoušky.\n" +
                "\n" +
                "Prostory: Rajská budova, Knihovna, Nejbližší kozlovna, Jídelna, Bar XXX, Kolej, Areál JM\n" +
                "Příkazy: " + cl.getCommandNames();
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

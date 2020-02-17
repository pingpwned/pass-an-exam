package logic;

/**
 *  Třída Game - třída představující logiku adventury.
 *
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy GamePlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 */
public class Game implements IGame {
    private CommandList cl; // obsahuje seznam přípustných příkazů
    private GamePlan gp;
    private boolean gameEnd = false;
    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy GamePlan) a seznam platných příkazů.
     */
    public Game() {
        gp = new GamePlan();
        cl = new CommandList();
        cl.addCommand(new CommandGo(gp, this));
        cl.addCommand(new CommandHelp(cl));
        cl.addCommand(new CommandEnd(this));
        cl.addCommand(new CommandTake(gp));
        cl.addCommand(new CommandVratit(gp));
    }
    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    @Override
    public String getHello() {
        return  "Už brzy budeme chtít jen jedno – dostat zápočet u zkoušky.\n" +
                "«Už brzy» seznámí studenty s základními techniky přípravy ke zkouškám.\n" +
                "Úkolem hráče je splnit jednu ze základních technik přípravy a dostat dobrý výsledek u zkoušky.\n" +
                "Teď si představíme náš obyčejný den, náměstí W. CH. a starou budovu.. Už začni!\n" +
                "\n" +
                "\n" +
                "Mimochodem, hra umi rozlisovat velka a mala pismenka." +
                "\n" +
                "\n" +
                gp.getActual().longDesc();
    }
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    @Override
    public String getEpilog() {
        return "Dík, že jste si zahráli. Ahoj!";
    }
    /**
     * Vrací true, pokud hra skončila.
     */
    @Override
    public boolean isTheEnd() {
        return gameEnd;
    }
    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  str  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    @Override
    public String commandProcess(String str) {
        String string;
        String [] words = str.split("[ \t]+");
        String command = words[0].toLowerCase();
        String [] params = new String[words.length - 1];

        for (int i = 0; i < params.length; i++) {
            params[i] = words[i+1];
        }
        ICommand cmd = cl.getCommand(command);
        if (cl.isValidCommand(command)) {
            string = cmd.doCommand(params);
            if (gp.isWinningRoom()) {
                Cart cart = gp.getCart();
                int count = 4;
                count -= cart.getPointsCount();
                string = "Dnes končíš s výsledkem " + (count < 1 ? 1 : count);
                gameEnd = true;
            }

        } else {
            string = "To neznam.";
        }
        return string;
    }
    /**
     *  Nastaví, že je konec hry, metodu využívá třída CommandEnd,
     *  mohou ji použít i další implementace rozhraní Command.
     *
     *  @param  gameEnd  hodnota false = konec hry, true = hra pokračuje
     */
    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }
    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     *  @return odkaz na herní plán
     */
    @Override
    public GamePlan getPlan() {
        return gp;
    }
}

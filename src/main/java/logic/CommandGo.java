package logic;
/**
 *  Třída CommandGo implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 */
public class CommandGo implements ICommand {
    private static final String NAME = "jdi";
    private GamePlan plan;
    private Game game;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public CommandGo(GamePlan plan, Game game) {
        this.plan = plan;
        this.game = game;
    }
    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param params - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String doCommand(String... params) {
        if (params.length == 0) {
            // pokud chybí druhé slovo (sousední prostor)
            return "Kam mam jít?";
        }

        String way = params[0].toLowerCase();
        GameRoom next = plan.getActual().getNextRoom(way);
        String nameOfActual = plan.getActual().getName();

        if (next == null) {
            // pokud se odsud jít nedá
            return "Tam se odsud jít nedá!";
        } else if ( way.equals("kolej") && nameOfActual.equals("rajská_budova") ) {
            // pokud hráč jde špatným směrem
            Thing pts = next.searchThing("bod_k_učení");
            System.out.println(pts.getName());
            next.removeThing(pts);
            next.setNewDescription("Rušný soused je doma. Asi budeš potřebovat zpravit rozbitý budík abys ráno nezaspal/a.");
        }  else if ( way.equals("kolej") && nameOfActual.equals("knihovna") ) {
            // pokud hráč jde špatným směrem
            next.setNewDescription("Škoda že doma žádné jídlo není.. Třeba káva a čokoláda tě zahraní u zkoušky.");
        } else if ( way.equals("jídelna") && nameOfActual.equals("rajská_budova") ) {
            // pokud hráč jde špatným směrem
            game.setGameEnd(true);
            return "Na plný žaludek učení nepůjde.";
        } else if ( way.equals("kozlovna")) {
            // pokud hráč jde špatným směrem
            game.setGameEnd(true);
            return "Po dobrém jídle a pár pivech jsi zůstal/a s kamarádem vsázet se na sportovní zápasy až do rána. \n" +
                    "Třeba se bude dařit na opravném termínu.";
        } else if ( way.equals("bar_xxx") ) {
            // pokud hráč jde špatným směrem
            plan.getCart().removePoints();
        } else if (way.equals("kamarád")) {
            // pokud hráč jde špatným směrem
            game.setGameEnd(true);
            return "Achjo.. \n" +
                    "Končíš se znamkou 4+";
        }
        plan.setActual(next);

        return next.longDesc();
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

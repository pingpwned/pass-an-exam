package logic;
/**
 *  Třída CommandTake implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 *
 */
public class CommandTake implements ICommand {
    private static final String NAME = "seber";
    private GamePlan gp;
    private Cart cart;
    /**
     *  Konstruktor třídy
     *
     *  @param gp herní plan
     *
     */
    public CommandTake(GamePlan gp) {
        this.gp = gp;
        cart = gp.getCart();
    }

    @Override
    public String doCommand(String... params) {
        if (params.length == 0) {
            return "Nevím co mám sebrat.";
        } else {
            String str = "";
            for (String param: params) {
                Thing th = gp.getActual().searchThing(param);
                if (th == null || !th.isPortability()) {
                    str = "Taková věc neexistuje nebo jí nemůžeš odnést. Celkový počet věcí je: ";
                    break;
                } else if (param.equals("budík") && !cart.hasItem("šroubovák")) {
                    str = "Nemáš všechny potřebné věci pro použití budíku. Celkový počet věcí je: ";
                    break;
                } else {
                    cart.takeThing(th);
                    str = "Věc byla přidána. Celkový počet věcí je teď: ";
                }
            }
            return str  + cart.getCartSize();
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

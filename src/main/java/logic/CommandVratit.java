package logic;

public class CommandVratit implements ICommand {
    private static final String NAME = "vrat";
    private GamePlan gp;
    private Cart cart;

    public CommandVratit(GamePlan gp) {
        this.gp = gp;
        cart = gp.getCart();
    }

    @Override
    public String doCommand(String... params) {
        if (params.length == 0) {
            return "Nevím co vrácet";
        } else {
            for (String param : params) {
                Thing th = cart.getItem(param);
                gp.getActual().addThing(th);
            }
            return "Věc byla vracena do místnosti.";
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}

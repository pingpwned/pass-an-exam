package logic;
/**
 *  Thing je třída představující logiku  věcí v adventuře.
 *
 *  Tato třída definuje základní datové atributy instance a umožňuje pomocí gettru tyto atributy získat
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 */
public class Thing {
    private String name;
    private boolean portability;
    /**
     * Konstruktor
     */
    public Thing(String name, boolean portability) {
        this.name = name;
        this.portability = portability;
    }

    public String getName() {
        return name;
    }

    public boolean isPortability() {
        return portability;
    }
}

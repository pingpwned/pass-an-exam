package logic;
/**
 *  Třída implementující košík.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    private List<Thing> seznam;
    private GamePlan gp;

    // constructor
    public Cart(GamePlan gp) {
        this.gp = gp;
        this.seznam = new ArrayList<>();
    }

    public boolean takeThing(Thing th) {
//        return th != null && seznam.add(th.getName());
        seznam.add(th);
        return gp.getActual().removeThing(th);
    }

    public int getCartSize() {
        return seznam.size();
    }

    public int getPointsCount() {
        return Collections.frequency(seznam, "bod_k_učení") + Collections.frequency(seznam, "čokoláda");
    }

    public boolean hasItem(String str) {
        return seznam.stream()
                .anyMatch(thing -> thing.equals(str));
    }

    public void removePoints() {
        seznam = seznam.stream()
                .filter(bod -> !bod.equals("bod_k_učení"))
                .collect(Collectors.toList());
    }

    public Thing getItem(String param) {
       for (int i = 0; i < seznam.size(); i++) {
           Thing th = seznam.get(i);
           if (th.getName().equals(param)) {
               return seznam.get(i);
           }
       }
        return null;
    }
}

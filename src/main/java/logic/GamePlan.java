package logic;

/**
 *  GamePlan - třída představující mapu a stav adventury.
 *
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 */

public class GamePlan {
    private GameRoom actual;
    private GameRoom winning;
    private GameRoom rb = new GameRoom("rajská_budova", "Máš poslední večer před zkouškou.. Achjo, proč ten čas tak letí?...");
    private GameRoom knihovna = new GameRoom("Knihovna", "Skvěle, objevil se první bod, který ti pomůže získat zítra lepší známku!" +
            "Teď ho seber a bež dál.");
    private GameRoom kozlovna = new GameRoom("Kozlovna", "");
    private GameRoom jidelna = new GameRoom("Jídelna", "Tady jsme správne, akorát je čas občerstvit se. Počkej, co tam je v rohu? Šroubovák?");
    private GameRoom bar = new GameRoom("Bar_XXX", "Nejvetsi mejdany vždy byly tady!! Po jednom z nich teď musíš rozhodnout \n" +
            "jestli pojedeš přespat na kolej nebo ke kamarádovi?");
    private GameRoom kolej = new GameRoom("Kolej", "Tohle je kolej.");
    private GameRoom jm = new GameRoom("Areál_JM", "Dorazil/a jsi na zkoušku včas.");
    private GameRoom kamarad = new GameRoom("Kamarád", "Končíš s výdledkem 3");
    private Thing pointToStudy = new Thing("bod_k_učení", true);
    private Thing sroubovak = new Thing("šroubovák", true);
    private Thing stul = new Thing("stůl", false);
    private Thing zidle = new Thing("židle", false);
    private Thing kava = new Thing("káva", true);
    private Thing cokolada = new Thing("čokoláda", true);
    private Thing budik = new Thing("budík", true);
    private Cart cart;

    public GamePlan() {
        // init entrances
        rb.setEnters(knihovna);
        rb.setEnters(kolej);
        rb.setEnters(jidelna);
        knihovna.setEnters(kolej);
        knihovna.setEnters(jidelna);
        knihovna.setEnters(kozlovna);
        jidelna.setEnters(kolej);
        jidelna.setEnters(bar);
        bar.setEnters(kolej);
        bar.setEnters(kamarad);
        kolej.setEnters(jm);

        // replace things
        jidelna.addThing(pointToStudy);
        jidelna.addThing(sroubovak);
        jidelna.addThing(stul);
        jidelna.addThing(zidle);
        knihovna.addThing(pointToStudy);
        kolej.addThing(pointToStudy);
        kolej.addThing(kava);
        kolej.addThing(cokolada);
        kolej.addThing(budik);

        // init actial
        actual=rb;
        winning=jm;

        // init cart
        cart = new Cart(this);

    }

    public boolean isWinningRoom() {
        return actual == winning;
    }

    public GameRoom getActual() {
        return actual;
    }

    public void setActual(GameRoom actual) {
        this.actual = actual;
    }

    public Cart getCart() {
        return cart;
    }
}

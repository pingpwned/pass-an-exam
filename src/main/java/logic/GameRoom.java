package logic;
/**
 * Trida GameRoom - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Dmitrii Savinykh
 * @version 1.0
 */
import java.util.*;
import java.util.stream.Collectors;

public class GameRoom {
    private String name;
    private String description;
    private List<GameRoom> enters; // obsahuje východy
    private Set<Thing> things;
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param name nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param description Popis prostoru.
     */
    public GameRoom(String name, String description) {
        this.name = name;
        this.description = description;
        enters = new ArrayList<>();
        things = new HashSet<>();
    }
    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param enter prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setEnters(GameRoom enter) {
        enters.add(enter);
    }
    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameRoom gameRoom = (GameRoom) o;

        if (!name.equals(gameRoom.name)) {
            return false;
        }
        if (!description.equals(gameRoom.description)) {
            return false;
        }
        if (!enters.equals(gameRoom.enters)) {
            return false;
        }
        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů.
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.
        return things.equals(gameRoom.things);
    }
    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + enters.hashCode();
        result = 31 * result + things.hashCode();
        return result;
    }
    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getName() {
        return name;
    }
    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String longDesc() {
        return "Jsi v místnosti " + name + ".\n"
                + description + "\n"
                + entersDesc() + "\n"
                + thingsDesc();
    }
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String thingsDesc() {
        if (!things.isEmpty()) {
            String text = "Věci: ";
            for (Thing thing: things) {
                text += " " + thing.getName();
            }
            return text;
        } else {
            return "V této místnosti nejsou žádné věci.";
        }

    }
    private String entersDesc() {
        String text = "Východy: ";
        for (GameRoom enter: enters) {
            text += " " + enter.getName();
        }
        return text;
    }
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nextRoomName Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public GameRoom getNextRoom(String nextRoomName) {
        List<GameRoom> filteredRoom = enters.stream()
                .filter(enter -> enter.getName().toLowerCase().equals(nextRoomName))
                .collect(Collectors.toList());
        return filteredRoom.isEmpty() ? null : filteredRoom.get(0);
    }
    public boolean addThing(Thing thing) {
        return things.add(thing);
    }
    public Thing searchThing(String thingName) {
        Thing th = null;
        for (Thing thing: things) {
            if(thing.getName().equals(thingName)) {
                th = thing;
                break;
            }
        }
        return th;
    }
    public boolean removeThing(Thing thing) {
        return things.remove(thing);
    }

    public void setNewDescription(String description) {
        this.description = description;
    }
}

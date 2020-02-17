package logic;
/**
 *  Class CommandList eviduje seznam přípustných příkazů adventury.
 *  Používá se pro rozpoznávání příkazů
 *  a vrácení odkazu na třídu implementující konkrétní příkaz.
 *  Každý nový příkaz (instance implementující rozhraní ICommand) se
 *  musí do seznamu přidat metodou addCommand.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Dmitrii Savinykh
 *@version    1.0
 */
import java.util.HashMap;
import java.util.Map;

public class CommandList {
    private Map<String, ICommand> commandMap;
    /**
     * Konstruktor
     */
    public CommandList() {
        commandMap = new HashMap<>();
    }
    /**
     * Vkládá nový příkaz.
     *
     *@param  command  Instance třídy implementující rozhraní IPrikaz
     */
    public void addCommand(ICommand command) {
        commandMap.put(command.getName(),command);
    }
    public ICommand getCommand(String str) {
        return commandMap.containsKey(str) ? commandMap.get(str) : null;
    }
    public boolean isValidCommand(String str) {
        return commandMap.containsKey(str);
    }
    public String getCommandNames() {
        String str = "";
        for (String name : commandMap.keySet()) {
            str += name + " ";
        }
        return str;
    }
}

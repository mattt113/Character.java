package org.example.journeytosammich;
import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    private Map<String, String> validCommands;
    Printer printer=new Printer();
    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "Move to another room");
        validCommands.put("quit", "End the game");
        validCommands.put("help", "Show help");
        validCommands.put("look", "Look around");
        validCommands.put("eat", "Eat something");
        validCommands.put("inspect","Look a little harder at something");
        validCommands.put("drop","Cast and item from thine inventory unto the soil beneath");
        validCommands.put("cheats","Toggle cheats");
        validCommands.put("take","pick ip an item");
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public void addNefariousCommands(){
       // validCommands.put("wordspeed","sets word print speed for (most) prints");
        validCommands.put("tp","teleports player");
        validCommands.put("make","summons item");
        validCommands.put("alterer","activates given alterer");
        validCommands.put("list","lists available second words of a cheat");
        validCommands.put("door","creates doorway to a room");
    }

    public String showAll() {
        StringBuilder stringBuilder=new StringBuilder("Valid commands are: ");
        for (String command : validCommands.keySet()) {
            if(!command.equals("cheats")) {
                stringBuilder.append(command).append(" ");
            }
        }
        return stringBuilder.toString();
    }
}

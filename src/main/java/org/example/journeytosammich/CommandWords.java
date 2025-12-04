package org.example.journeytosammich;
import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    //list of all active command words
    private Map<String, String> validCommands;
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
        validCommands.put("use","uses the item");
    }
    //checks if command is allowed
    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

//    adds cheats to valid commands
    public void addNefariousCommands(){
        validCommands.put("tp","teleports player");
        validCommands.put("make","summons item");
        validCommands.put("alterer","activates given alterer");
        validCommands.put("list","lists available second words of a cheat");
        validCommands.put("door","creates doorway to a room");
    }
//   returns list of all valid commands. filters out "cheats"
    public String showAll() {
        StringBuilder stringBuilder=new StringBuilder("Valid commands are: ");
        for (String command : validCommands.keySet()) {
            if(!command.equals("cheats")) {//dont show them this one
                stringBuilder.append(command).append(" ");
            }
        }
        return stringBuilder.toString();
    }
}

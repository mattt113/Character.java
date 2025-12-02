package org.example.journeytosammich;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher {
    private WorldMaker world;
    private Parser parser;
    private Character player;

    private PlayerActions action;



    public Launcher(Stage stage) {
        createRooms(stage);
        parser = new Parser();

        action=new PlayerActions(player,parser,world);
    }

    private void createRooms(Stage stage) {
        RestoreSave restoreSave=new RestoreSave();
        world= restoreSave.restoreWorld();
        if(world==null) {
            world = new WorldMaker(stage);
        }


        player =world.getPlayer();
        //player.pickUp(note);
        //player=restoreSave.restoreWorld();
    }
    public String textInput(String words){
        Command command = parser.getCommand(words);
        return processCommand(command);
    }

    public String play() {
        return printWelcome();
    }

    private String printWelcome() {
        System.out.println();
        String welcome= ("You awake in the middle of the night.\nAn overwhelming hunger claws at your mind.\nYou must assemble the one true sandwich if you are ever to sleep again\n");
        welcome+= ("Type 'help' if you need help.");

        return welcome+(player.getCurrentRoom().getLongDescription());

    }

    private String processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            return ("I don't understand your command...");
//            return false;
        }

        switch (commandWord) {
            case "help":
                return action.printHelp();
            case "go":
                return action.goRoom(command);
            case "inspect":
                return action.inspect(command);
            case "drop":
                return action.dropItem(command);
            case "quit":
               return ("not implemented-quit the normal way");
            case "take":
                return action.getItem(command);
            case "eat":
                return action.eatItem(command);
            case "cheats":
                parser.nefariousCommandsActivator();
                return ("evil mode active");
            case "tp":
                return action.teleport(command);
            case "make":
                return action.getItem(command);
            case "alterer":
                return action.altererActivate(command);
            case "list":
                return action.listAvailableCommands(command);
            case "door":
                return action.unlockDoor(command);
            //case "":

            default:
                return("I don't know what you mean...");


        }
    }

    public String save(){
        Obituary save=new Obituary();
        save.save(world);
        return "progress saved";
    }
    public String deleteSave(){
        Obituary save=new Obituary();
        save.dontSave();
        return "progress unsaved";
    }
    public PlayerActions getActions(){
        return action;
    }

    public Character getPlayer(){
        return player;
    }

    public static void main(String[] args){
        Application.launch(GuiMaker.class, args);
    }

}

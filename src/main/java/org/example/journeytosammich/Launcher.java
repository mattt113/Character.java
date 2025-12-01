package org.example.journeytosammich;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher {
    private WorldMaker world;
    private Parser parser;
    private Character player;
    private Printer printer;
    private PlayerActions action;
    private Item itemInUse;


    public Launcher(Stage stage) {
        createRooms(stage);
        parser = new Parser();
        printer = new Printer();
        action=new PlayerActions(printer,player,parser,world);
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

    public void play() {
        printWelcome();

//
//        boolean finished = false;
//        while (!finished) {
//           // Command command = parser.getCommand();
//            //finished = processCommand(command);
//        }
//        System.out.println("Thank you for playing. Goodbye.");
    }

    private void printWelcome() {
        System.out.println();
        printer.print("You awake in the middle of the night.\nAn overwhelming hunger claws at your mind.\nYou must assemble the one true sandwich if you are ever to sleep again\n");
        printer.print("Type 'help' if you need help.");
        System.out.println();
        printer.print(player.getCurrentRoom().getLongDescription());

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
                return getItem(command);
            case "eat":
                return action.eatItem(command);
            case "cheats":
                parser.nefariousCommandsActivator();
                return ("evil mode active");
            case "tp":
                return action.teleport(command);
            case "make":
                return action.addItem(command);
            case "alterer":
                return action.altererActivate(command);
            case "list":
                return action.listAvailableCommands(command);
            case "door":
                return action.unlockDoor(command);
            case "":

            default:
                printer.print("I don't know what you mean...");
                break;

        }
//        return false;
        return null;
    }

    private void printHelp() {
        printer.print("The sandwich calls");
        printer.print("Your command words are: ");
        parser.showCommands();
    }

    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            printer.print("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            printer.print("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            printer.print(player.getCurrentRoom().getLongDescription());
        }
    }
    public String goRoom(String direction) {

        Room nextRoom = player.getCurrentRoom().getExit(direction);
        player.setCurrentRoom(nextRoom);
        return player.getCurrentRoom().getLongDescription();

    }

    public String getItem(String food) {


        for (int i = 0; i < player.getCurrentRoom().getContents().size(); i++){

            ArrayList<ItemHoldable> holdables=player.getCurrentRoom().getItems();
            ArrayList<Item> contents= player.getCurrentRoom().getContents();
            if (holdables.contains(contents.get(i))) {
                ItemHoldable item = player.getCurrentRoom().getItems().get(i);
                if (item.getName().equals(food)) {
                     player.getCurrentRoom().removeItem(item);
                     player.pickUp(item);
                     if (item instanceof ItemTakeActivate){
                         return item.activate();


                     }
                     return "It is consumed";

                }
            }
            else{
                return "cant be held";
            }
        }
        return "You can eat no such thing";
    }
    public String getItem(Command command) {
        if(!command.hasSecondWord()){
            return ("eat what?");

        }
        String food = command.getSecondWord();

        for (int i = 0; i < player.getCurrentRoom().getItems().size(); i++){
            ItemHoldable item=player.getCurrentRoom().getItems().get(i);
            if (item.getName().equals(food)){
                player.getCurrentRoom().removeItem(item);
                player.pickUp(item);
                return ("It is consumed");

            }
        }
        return ("You can eat no such thing");
    }

    public String dropItem(String food){
        ItemHoldable maybeFoodItem=player.checkInventory(food);
        //System.out.println(maybeFoodItem.getName());
        if(food.equals("self")){
            return "you collapse upon the ground";
        }

        else if(maybeFoodItem==null){
            return "You possess no such thing";
        }
        else if(maybeFoodItem instanceof ItemEatable){
            return maybeFoodItem.activate();
        }

        else {

            player.getCurrentRoom().addItem(maybeFoodItem);
            player.removeItem(maybeFoodItem);



            return "it is expelled";

        }
    }
    public void dropItem(Command command){
        if (!command.hasSecondWord()) {
            printer.print("drop what?");
            return;
        }

        String food = command.getSecondWord();
        ItemHoldable maybeFoodItem=player.checkInventory(food);
        //System.out.println(maybeFoodItem.getName());
        if(maybeFoodItem==null){
            printer.print("You possess no such thing");
            return;
        }

        else {

            player.getCurrentRoom().addItem(maybeFoodItem);
            player.removeItem(maybeFoodItem);



            printer.print("it is expelled");

        }
    }
    public String eatItem(String itemToEat){
        if(itemToEat.equals("self")){
            return "tastes like persom";
        }
        else if(itemToEat.equals("room")){
            return "it won't fit in your mouth";
        }
        else{
            for(int i=0;i<player.checkAccess().size();i++){
                Item item=player.checkAccess().get(i);
                if (itemToEat.equals(item.getName())){
                    return item.getName()+": "+item.getEatDescription();
                }
            }
            return "That cannot be eaten";
        }
    }
    public String useItem(String itemUsed){
        Item item;
        for(int i=0;i<player.checkAccess().size();i++){
            item=player.checkAccess().get(i);
            if (itemUsed.equals(item.getName())){
                Item tempItemInUse=itemInUse;
                if((!player.getInventory().contains(itemInUse))&&(itemInUse instanceof ItemUseActivate)){
                    return "Item must be in inventory to be used";
                }
                //if ((item instanceof ItemUseActivate)) {
                itemInUse=item;

                   // return item.useItem(null);
                //}
                if ((item instanceof ItemUseSubject)&&!(player.getCurrentRoom().getContents().contains(itemInUse))){
                    itemInUse=null;
                    //return item.useItem(tempItemInUse);
                }
                //return item.useItem(null);

                return item.useItem(tempItemInUse);
           }

        }


        return null;
    }


    private void inspect(Command command){
        if (!command.hasSecondWord()) {
            printer.print("look where?");
            return;
        }
        String inspectable = command.getSecondWord();
        if (inspectable.equals("self")){
            String contents="";
            for(int i=0;i<player.getInventory().size();i++){
                contents=(contents+" a "+(player.getInventory().get(i).getName()));
            }
            if(contents.isEmpty()){
                printer.print("you have NOTHING");
            }
            else{
                //parser.showCommands();
                printer.print("you have"+contents);
            }
        }
        else if(inspectable.equals("room")){
            printer.print(player.getCurrentRoom().getLongDescription());
        }


        else{
            for(int i=0;i<player.checkAccess().size();i++){
                Item item=player.checkAccess().get(i);
                if (inspectable.equals(item.getName())){
                    printer.print(item.getName()+": "+item.getDescription());
                    return;

                }
            }
            printer.print("That cannot be inspected");
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
        //Launcher game = new Launcher();
        //game.play();
         // Application.launch(GuiMaker.class, args);

    }

}

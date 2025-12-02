package org.example.journeytosammich;

import java.util.ArrayList;

public class PlayerActions {

    private Character player;
    private Parser parser;
    private WorldMaker world;
    private Item itemInUse;

    PlayerActions(Character replayer,Parser reparser,WorldMaker reWorld){

        player=replayer;
        parser=reparser;
        world=reWorld;
    }
    public String printHelp() {
        return ("The sandwich calls.\nYour command words are: "+parser.showCommands());
    }

    public String goRoom(Command command) {
        if (!command.hasSecondWord()) {
            return ("Go where?");
        }
        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            return ("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            return (player.getCurrentRoom().getLongDescription());
        }
    }
    public String goRoom(String direction) {

        Room nextRoom = player.getCurrentRoom().getExit(direction);
        player.setCurrentRoom(nextRoom);
        return player.getCurrentRoom().getLongDescription();

    }


    public String getItem(Command command) {
        if (!command.hasSecondWord()) {
            return ("eat what?");

        }
        String food = command.getSecondWord();
        for (int i = 0; i < player.getCurrentRoom().getContents().size(); i++) {

            ArrayList<ItemHoldable> holdables = player.getCurrentRoom().getItems();
            ArrayList<Item> contents = player.getCurrentRoom().getContents();
            if (holdables.contains(contents.get(i))) {
                ItemHoldable item = player.getCurrentRoom().getItems().get(i);
                if (item.getName().equals(food)) {
                    player.getCurrentRoom().removeItem(item);
                    player.pickUp(item);
                    if (item instanceof ItemTakeActivate) {
                        return item.activate();
                    }
                    return "It is consumed";
                }
            } else {
                return "cant be held";
            }
        }
        return "absent item";
    }
    public String getItem(String food) {
        for (int i = 0; i < player.getCurrentRoom().getContents().size(); i++) {

            ArrayList<ItemHoldable> holdables = player.getCurrentRoom().getItems();
            ArrayList<Item> contents = player.getCurrentRoom().getContents();
            if (holdables.contains(contents.get(i))) {
                ItemHoldable item = player.getCurrentRoom().getItems().get(i);
                if (item.getName().equals(food)) {
                    player.getCurrentRoom().removeItem(item);
                    player.pickUp(item);
                    if (item instanceof ItemTakeActivate) {
                        return item.activate();
                    }
                    return "It is consumed";
                }
            } else {
                return "cant be held";
            }
        }
        return "absent item";
    }

    public String dropItem(Command command) {
        if (!command.hasSecondWord()) {
            return ("drop what?");
        }
        String food = command.getSecondWord();
        ItemHoldable maybeFoodItem = player.checkInventory(food);
        if (food.equals("self")) {
            return "you collapse upon the ground";
        }
        if (food.equals("room")) {
            return "you release your grip on the house.\nItt does not fall.";
        } else if (maybeFoodItem == null) {
            return "You possess no such thing";
        } else if (maybeFoodItem instanceof ItemEatable) {
            return maybeFoodItem.activate();
        } else {
            player.getCurrentRoom().addItem(maybeFoodItem);
            player.removeItem(maybeFoodItem);
            return "it is expelled";
        }
    }

    public String dropItem(String food){
        ItemHoldable maybeFoodItem=player.checkInventory(food);
        if(food.equals("self")){
            return "you collapse upon the ground";
        }
        if(food.equals("room")){
            return "you release your grip on the house.\nItt does not fall.";
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
    public String eatItem(Command command){
        if (!command.hasSecondWord()) {
            return "look where?";
        }
        String itemToEat = command.getSecondWord();
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
    public String inspect(Command command){
        if (!command.hasSecondWord()) {
            return ("look where?");
        }
        String inspectable = command.getSecondWord();
        if (inspectable.equals("self")){
            String contents="";
            for(int i=0;i<player.getInventory().size();i++){
                contents=(contents+" a "+(player.getInventory().get(i).getName()));
            }
            if(contents.isEmpty()){
                return ("you have NOTHING");
            }
            else{
                //parser.showCommands();
                return ("you have"+contents);
            }
        }
        else if(inspectable.equals("room")){
            return (player.getCurrentRoom().getLongDescription());
        }
        else{
            for(int i=0;i<player.checkAccess().size();i++){
                Item item=player.checkAccess().get(i);
                if (inspectable.equals(item.getName())){
                   return (item.getName()+": "+item.getDescription());
                }
            }
            return ("That cannot be inspected");
        }
    }
    public String inspect(String inspectable){
        if (inspectable.equals("self")){
            String contents="";
            for(int i=0;i<player.getInventory().size();i++){
                contents=(contents+" a "+(player.getInventory().get(i).getName()));
            }

            if(!contents.isEmpty()) {
                return "you have" + contents;
            }
            else{
                return "You have NOTHING";
            }
        }
        else if(inspectable.equals("room")){
            return player.getCurrentRoom().getLongDescription();
        }


        else{
            for(int i=0;i<player.checkAccess().size();i++){
                Item item=player.checkAccess().get(i);
                if (inspectable.equals(item.getName())){
                    return item.getName()+": "+item.getDescription();


                }
            }
            return "That cannot be inspected";
        }

    }

    public String teleport(Command command) {
        if (!command.hasSecondWord()) {
            return ("teleport where?");
        }
        String roomName=command.getSecondWord();
        Room room= world.getRoom(roomName);
        if(room!=null){
            player.setCurrentRoom(room);
            return "player location set";
        }
        return "no such room";
    }
    public String altererActivate(Command command) {
        if (!command.hasSecondWord()) {
            return ("no alterer listed");
        }
        String altererName=command.getSecondWord();

        world.activateRoomAlter(altererName);

        return "";
    }

    public String addItem(Command command) {
        if (!command.hasSecondWord()) {
            return ("make what?");
        }
        String itemName=command.getSecondWord();

        world.makeItem(itemName);

        return "";
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
                itemInUse=item;
                if ((item instanceof ItemUseSubject)&&!(player.getCurrentRoom().getContents().contains(itemInUse))){
                    itemInUse=null;
                }
                return item.useItem(tempItemInUse);
            }
        }
        return null;
    }
    public String useItem(Command command){
        if (!command.hasSecondWord()) {
            return ("use what?");
        }
        String itemUsed=command.getSecondWord();

        Item item;
        for(int i=0;i<player.checkAccess().size();i++){
            item=player.checkAccess().get(i);
            if (itemUsed.equals(item.getName())){
                Item tempItemInUse=itemInUse;
                if((!player.getInventory().contains(itemInUse))&&(itemInUse instanceof ItemUseActivate)){
                    return "Item must be in inventory to be used";
                }
                itemInUse=item;
                if ((item instanceof ItemUseSubject)&&!(player.getCurrentRoom().getContents().contains(itemInUse))){
                    itemInUse=null;
                }
                return item.useItem(tempItemInUse);
            }
        }
        return null;
    }
    public String listAvailableCommands(Command command){
        if (!command.hasSecondWord()) {
            return ("make what?");
        }
        StringBuilder output=new StringBuilder();
        String input=command.getSecondWord();
        if (input.equals("rooms")){
            ArrayList<Room> rooms= world.getRooms();
            for (Room room:rooms){
                output.append(room.getName()).append(" ");
            }
            return output.toString();
        }
        if (input.equals("items")){
            ArrayList<ItemHoldable> itemHoldables= world.getItemHoldables();
            for (ItemHoldable itemHoldable:itemHoldables){
                output.append(itemHoldable.getName()).append(" ");
            }
            ArrayList<ItemNoHold> itemNoHolds= world.getItemNoHolds();
            for (ItemNoHold itemNoHold:itemNoHolds){
                output.append(itemNoHold.getName()).append(" ");
            }
            return output.toString();
        }
        if (input.equals("alterers")){
            ArrayList<AutoRoomAlterer> alterers= world.getAltererList();
            for (AutoRoomAlterer alterer:alterers){
                output.append(alterer.getName()).append(" ");
            }
            return output.toString();
        }
        return "Invalid second word \nvalid second words include: rooms, alterers and items";
    }

    public String unlockDoor(Command command) {
        if (!command.hasSecondWord()) {
            return ("make what?");
        }
        String input=command.getSecondWord();
        ArrayList<Room> rooms=world.getRooms();
        if (input.equals("all")){
            for (Room room:rooms){
                player.getCurrentRoom().setExit(room.getName(), room);
            }
        }
        for (Room room:rooms){
            if(room.getName().equals(input)) {
                player.getCurrentRoom().setExit(room.getName(), room);
            }
        }
        return "invalid second word";
    }
}

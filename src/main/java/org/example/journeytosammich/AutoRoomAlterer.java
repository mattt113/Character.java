package org.example.journeytosammich;

import javafx.stage.Stage;

import java.io.Serializable;
import java.util.HashMap;

public class AutoRoomAlterer implements Serializable, Cheatable {
    private String name;
    private HashMap<Room,HashMap<String,Room>> exitToAdd;
    private HashMap<Room,String> exitToRemove;
    private HashMap<String,Room> exits;
    private HashMap<ItemNoHold, Room> addItemsNoHold;
    private HashMap<ItemNoHold,Room> removeItemsNoHold;
    private HashMap<ItemHoldable, Room> addItemsHold;
    private HashMap<Room, String> descChanger;
    private HashMap<Room,String> imageChanger;
    private ItemHoldable removeFromPlayer;
    private ItemHoldable addToPlayer;
    private Room teleportLocation;
    private Character player;
    private Stage stage;

    public AutoRoomAlterer(String reName){
        name=reName;
//        exitToAdd =new HashMap<>();
//        exitToRemove =new HashMap<>();
//        addItemsNoHold =new HashMap<>();
//        removeItemsNoHold =new HashMap<>();
//        addItemsHold=new HashMap<>();
//        descChanger=new HashMap<>();
//        imageChanger=new HashMap<>();

    }
    public void addThing(Stage reStage){
        stage=reStage;
    }

    private void endGame(){
        stage.close();
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public void addDescChanger(Room room,String desc){
        if(descChanger==null) {
            descChanger = new HashMap<>();
        }
        descChanger.put(room,desc);
    }

    public void addThing(Room room, String name, Room newRoomExit){
        if(exitToAdd ==null) {
               exitToAdd =new HashMap<>();
        }
        exits = new HashMap<>();
        exits.put(name, newRoomExit);
        if(!exitToAdd.containsKey(room)) {
            exitToAdd.put(room, exits);
        }
        else{
            exitToAdd.get(room).putAll(exits);
        }
    }
    public void addThing(Room room, ItemNoHold item){
        if (addItemsNoHold==null) {
            addItemsNoHold = new HashMap<>();
        }
        addItemsNoHold.put(item,room);
    }
    public void addThing(Room room, ItemHoldable item){
        if (addItemsHold==null) {
            addItemsHold = new HashMap<>();
        }
        addItemsHold.put(item,room);
    }
    public void addImageChange(Room room,String image){
        if(imageChanger==null){
            imageChanger=new HashMap<>();
        }

        imageChanger.put(room,image);
    }


    public void removeThing(Room room, String name){
        if(exitToRemove==null) {
            exitToRemove = new HashMap<>();
        }
        exitToRemove.put(room,name);
    }
    public void removeThing(Room room,ItemNoHold item){
        if(removeItemsNoHold==null){
        removeItemsNoHold=new HashMap<>();
        }
        removeItemsNoHold.put(item,room);
    }
    public void removeThing(Character rePlayer,ItemHoldable item){
       player=rePlayer;
        removeFromPlayer=item;
    }
    public void addThing(Character rePlayer,ItemHoldable item){
        player=rePlayer;
        addToPlayer=item;
    }
    public void addTeleport(Character replayer,Room room){
        player=replayer;
        teleportLocation=room;
    }
    public void enactChange(){
        if(stage!=null){
            endGame();
        }
        if(exitToRemove !=null) {
            for (Room room : exitToRemove.keySet()) {
                room.removeExit(exitToRemove.get(room));
            }
        }
        if(exitToAdd !=null) {
            for (Room room : exitToAdd.keySet()) {
                room.setExit(exitToAdd.get(room));
            }
        }
        if(removeItemsNoHold!=null) {
            for (ItemNoHold item : removeItemsNoHold.keySet()) {

                removeItemsNoHold.get(item).removeItem((item));
            }
        }
        if(addItemsNoHold!=null) {
            for (ItemNoHold item : addItemsNoHold.keySet()) {
                addItemsNoHold.get(item).addItem(item);
            }
        }
        if(addItemsHold!=null) {
            for (ItemHoldable item : addItemsHold.keySet()) {
                addItemsHold.get(item).addItem((item));
            }
        }
        if (imageChanger!=null) {
            for (Room room : imageChanger.keySet()) {

                room.setImage(imageChanger.get(room));
            }
        }
        if (descChanger!=null) {
            for (Room room : descChanger.keySet()) {
                room.setDescription(descChanger.get(room));
            }
        }
        if(teleportLocation!=null) {
            player.setCurrentRoom(teleportLocation);
        }
        if (removeFromPlayer!=null){
            player.removeItem(removeFromPlayer);
        }
        if (addToPlayer!=null){
            player.pickUp(addToPlayer);
        }
    }
}

package org.example.journeytosammich;

import javafx.stage.Stage;

import java.io.Serializable;
import java.util.HashMap;

public class AutoRoomAlterer implements Serializable {
    private String name;
    private HashMap<Room,HashMap<String,Room>> thingToAdd;
    private HashMap<Room,String> thingToRemove;
    private HashMap<String,Room> exits;
    private HashMap<Room, ItemNoHold> addItemsNoHold;
    private HashMap<Room,ItemNoHold> removeItemsNoHold;
    private HashMap<Room, ItemHoldable> addItemsHold;
    private HashMap<Room, String> descChanger;
    private HashMap<Room,String> imageChanger;
    private ItemHoldable removeFromPlayer;
    private ItemHoldable addToPlayer;
    private Room teleportLocation;
    private Character player;
    private Stage stage;

    public AutoRoomAlterer(String reName){
        name=reName;
        thingToAdd=new HashMap<>();
        thingToRemove =new HashMap<>();
        addItemsNoHold =new HashMap<>();
        removeItemsNoHold =new HashMap<>();
        addItemsHold=new HashMap<>();
        descChanger=new HashMap<>();
        imageChanger=new HashMap<>();

    }
    public void addThing(Stage reStage){
        stage=reStage;
    }

    private void endGame(){
        stage.close();
    }
    public String getName(){
        return name;
    }
    public void addDescChanger(Room room,String desc){
   //     descChanger=new HashMap<>();
        descChanger.put(room,desc);
    }

    public void addThing(Room room, String name, Room newRoomExit){
     //   thingToAdd=new HashMap<>();
        exits = new HashMap<>();
        exits.put(name, newRoomExit);
        if(!thingToAdd.containsKey(room)) {
            thingToAdd.put(room, exits);
        }
        else{
            thingToAdd.get(room).putAll(exits);
        }
    }
    public void addThing(Room room, ItemNoHold item){
 //       addItemsNoHold=new HashMap<>();
        addItemsNoHold.put(room,item);
    }
    public void addThing(Room room, ItemHoldable item){
 //       addItemsHold=new HashMap<>();
        addItemsHold.put(room,item);
    }
    public void addImageChange(Room room,String image){


        imageChanger.put(room,image);
    }


    public void removeThing(Room room, String name){
 //       thingToRemove=new HashMap<>();
        thingToRemove.put(room,name);
    }
    public void removeThing(Room room,ItemNoHold item){
 //       removeItemsNoHold=new HashMap<>();
        removeItemsNoHold.put(room,item);
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
        if(thingToRemove!=null) {
            for (Room room : thingToRemove.keySet()) {
                room.removeExit(thingToRemove.get(room));
            }
        }
        if(thingToAdd!=null) {
            for (Room room : thingToAdd.keySet()) {
                room.setExit(thingToAdd.get(room));
            }
        }
        if(removeItemsNoHold!=null) {
            for (Room room : removeItemsNoHold.keySet()) {
                room.removeItem(removeItemsNoHold.get(room));
            }
        }
        if(addItemsNoHold!=null) {
            for (Room room : addItemsNoHold.keySet()) {
                room.addItem(addItemsNoHold.get(room));
            }
        }
        if(addItemsHold!=null) {
            for (Room room : addItemsHold.keySet()) {
                room.addItem(addItemsHold.get(room));
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

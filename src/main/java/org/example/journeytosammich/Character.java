package org.example.journeytosammich;
import java.io.Serializable;
import java.util.ArrayList;

//only used for player
public class Character implements Serializable {
    private String name;
    private Room currentRoom;
    private ArrayList<ItemHoldable> inventory = new ArrayList<>();//separate items into ItemHoldable, and ItemNoHold

    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    //changes player's room to specified exit of current room
    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You moved to: " + currentRoom.getDescription());
        } else {
            System.out.println("You can't go that way!");
        }
    }

    //adds item to player's inventory
    public void pickUp(ItemHoldable item) {
        inventory.add(item);
    }

    //takes string, checks if name of item in inventory, returns Item
    public ItemHoldable checkInventory(String itemWanted) {
        for (int i = 0; i < inventory.size(); i++) {
            ItemHoldable item=inventory.get(i);
            if ((item.getName()).equals(itemWanted)) {
                return item;
            }
        }
        return null;
    }
    //returns entire inventory
    public ArrayList<ItemHoldable> getInventory(){
        return inventory;
    }
    //returns arraylist of all items in inventory+room
    public ArrayList<Item> checkAccess(){
        ArrayList<Item> accessibleItems=new ArrayList<>();
        accessibleItems.addAll(inventory);
        if(currentRoom.getContents()!=null) {
            ArrayList<Item> roomItems = currentRoom.getContents();
            accessibleItems.addAll(roomItems);
        }
        return accessibleItems;


    }
//    removes ItemHoldable from inventory
    public void removeItem(ItemHoldable item) {
        inventory.remove(item);
    }
//    removes item from inventory
    public void removeItem(Item item) {
        inventory.remove(item);
    }



}

package org.example.journeytosammich;
import java.io.Serializable;
import java.util.ArrayList;


public class Character implements Serializable {
    private String name;
    private Room currentRoom;
    private ArrayList<ItemHoldable> inventory = new ArrayList<>();

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

    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You moved to: " + currentRoom.getDescription());
        } else {
            System.out.println("You can't go that way!");
        }
    }

    public void pickUp(ItemHoldable item) {
        inventory.add(item);


    }

    public ItemHoldable checkInventory(String itemWanted) {
        for (int i = 0; i < inventory.size(); i++) {
            ItemHoldable item=inventory.get(i);
            if ((item.getName()).equals(itemWanted)) {

                return item;
            }
        }
        return null;//inventory.get(0).getName();
    }
    public ArrayList<ItemHoldable> getInventory(){
        return inventory;
    }
    public ArrayList<Item> checkAccess(){
        ArrayList<Item> accessibleItems=new ArrayList<>();
        accessibleItems.addAll(inventory);
        if(currentRoom.getContents()!=null) {
            ArrayList<Item> roomItems = currentRoom.getContents();
            accessibleItems.addAll(roomItems);
        }
        return accessibleItems;


    }

    public void removeItem(ItemHoldable item) {
        inventory.remove(item);
    }
    public void removeItem(Item item) {
        inventory.remove(item);
    }


}

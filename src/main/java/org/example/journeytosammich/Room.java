package org.example.journeytosammich;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Room implements Serializable {
    protected String name;
    protected String description;
    protected Map<String, Room> exits; // Map direction to neighboring Room
    protected ArrayList<ItemHoldable> items=new ArrayList<ItemHoldable>();
    protected ArrayList<ItemNoHold> furniture=new ArrayList<ItemNoHold>();
    protected String image="NoPicture.png";


    public String getName(){
        return name;
    }
    public Room(String reName,String description,String reimage) {
        name=reName;
        this.description = description;
        image = reimage;
        exits = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String redesc){
        description=redesc;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
    public void setExit(HashMap<String,Room> exit) {
        exits.putAll(exit);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void removeExit(String exit){
        exits.remove(exit);
    }

    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(" ");
        }
        return sb.toString().trim();
    }
//    public void addItem(String newItem,String desc){
//
//      gun= new ItemHoldable(newItem,desc);
//      items.add(gun);
//    }
    public void addItem(ItemHoldable item){
//        if(item.getName().equals("sandwich")){
//            item.setLocation(null);
//        }
        items.add(item);
    }

   
    public void removeItem(String name){
       for(int i=0;i<items.size();i++){
         if(items.get(i).getName().equals(name)){
            items.remove(i);

         } 

       }
    }
    public void removeItem(ItemHoldable thing){
        thing.setLocation(null);
        items.remove(thing);
    }
    public void removeItem(ItemNoHold thing){
        furniture.remove(thing);
    }
    public void addItem(ItemNoHold item){
        item.setLocation(this);
        furniture.add(item);
    }

    public ArrayList<ItemHoldable> getItems(){
         return items;

    }
    public ArrayList<ItemNoHold> getFurniture(){
        if(furniture.isEmpty()==false){

            return furniture;
        }
//      else{
//         //return"naught";
//      }
        else {
            return null;
        }
    }
    public ArrayList<Item> getContents(){
        ArrayList<Item> contents = new ArrayList<Item>();
        contents.addAll(items);
        contents.addAll(furniture);
        return contents;
}


    public String getLongDescription() {
        StringBuilder stringBuilder=new StringBuilder(description);
        if(!getContents().isEmpty()){
            String itemList = "";
            for(int i=0;i<getContents().size();i++){
                itemList=(itemList+" a "+getContents().get(i).getName());
            }
            stringBuilder.append(".\nThis room contains").append(itemList).append(".");
        }
        if(getExitString()!=null){
            stringBuilder.append("\nExits: " + getExitString());
        }
        return stringBuilder.toString();
    }
    public String getImage(){
        return image;
    }
    public void setImage(String reimage){
        image=reimage;
    }



}

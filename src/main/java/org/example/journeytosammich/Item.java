package org.example.journeytosammich;

import java.io.Serializable;

public abstract class Item implements Serializable,ItemInterface, Cheatable {
    protected String description;
    protected String name;
    protected AutoRoomAlterer roomChanger;
    protected String soundEffect;


    public Item(String rename, String redescription) {
        name = rename;
        description = redescription;
    }

    public String useItem(Item item) {
        return "this item has no use";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void addAlterer(AutoRoomAlterer alterer){
        roomChanger=alterer;
    }
    public String activate(){
        roomChanger.enactChange();
        return null;
    }
    @Override
    public String getInfo() {
        return name + " " + description;
    }

    public String getEatDescription() {
        return "your teeth chip and shatter";
    }
    public String getSoundEffect(){
        return soundEffect;
    }
}

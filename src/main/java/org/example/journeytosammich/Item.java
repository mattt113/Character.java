package org.example.journeytosammich;

import java.io.Serializable;
import java.net.IDN;

public abstract class Item implements Serializable,ItemInterface {
    protected String description;
    protected String name;
    protected int id;
    protected static int defaultId;
    protected boolean isVisible = true;
    protected Room location;
    protected AutoRoomAlterer roomChanger;


    public Item(String rename, String redescription) {
        name = rename;
        description = redescription;
        id = defaultId;
        defaultId++;
    }

    public Item(String rename, String redescription, int reId) {
        name = rename;
        description = redescription;
        id = reId;
    }

    public Item(String rename, String redescription, boolean reVisible) {
        name = rename;
        description = redescription;
        isVisible = reVisible;
    }

    public Item(String rename, String redescription, int reId, boolean reVisible) {
        name = rename;
        description = redescription;
        id = reId;
        isVisible = reVisible;
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

    public void setLocation(Room reLocation) {
        location = reLocation;
    }

    public Room getLocation() {
        return location;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getEatDescription() {
        return "your teeth chip and shatter";
    }
}

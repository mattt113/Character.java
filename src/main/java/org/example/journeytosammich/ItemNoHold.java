package org.example.journeytosammich;

import java.io.Serializable;

public class ItemNoHold extends Item implements Serializable {
    public ItemNoHold(String name, String description) {
        super(name, description);
    }
    @Override
    public String useItem(Item item){
        return "It does nothing";
    }
}

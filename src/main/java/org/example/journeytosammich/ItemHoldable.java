package org.example.journeytosammich;

//Item that can be picked up.
public class ItemHoldable extends Item {


    public ItemHoldable(String name, String description) {
        super(name, description);
    }

    @Override
    public String useItem(Item item) {
        return "It fails";
    }
}
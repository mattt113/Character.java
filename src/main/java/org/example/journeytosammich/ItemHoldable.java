package org.example.journeytosammich;
public class ItemHoldable extends Item {


    protected boolean sandwichPart;

    public ItemHoldable(String name, String description) {
        super(name, description);
    }
    public ItemHoldable(String name, String description,boolean reSandwichPart) {
        super(name, description);
        sandwichPart=reSandwichPart;
    }



    @Override
    public String useItem(Item item) {
        return "It fails";
    }
}
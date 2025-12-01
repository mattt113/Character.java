package org.example.journeytosammich;

public class ItemUseActivate extends ItemHoldable{


    public ItemUseActivate(String name, String description) {
        super(name, description);

    }

    @Override
    public String useItem(Item item){
        if (roomChanger!=null) {
            roomChanger.enactChange();
        }
        return "use on what?";
    }
}

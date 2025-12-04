package org.example.journeytosammich;

public class Box<T extends ItemHoldable> extends ItemUseSubject {
    private T value;
    private Character player;


    public Box(String rename, String redescription, Character rePlayer,String reUseDescription) {
        super(rename, redescription, reUseDescription, null);
        player = rePlayer;
    }
    //box that comes with item
    public Box(String rename, String redescription, Character rePlayer, T reValue) {
        super(rename, redescription, null, null);
        player = rePlayer;
        value = reValue;
    }


    public void setValue(T revalue) {
        value = revalue;
    }

    public T getValue() {
        return value;
    }

    public String getDesc() {
        return value.getInfo();
    }

    @Override
    public String useItem(Item itemInUse) {
        if(value!=null) {//checks if box empty. if box is full; empty+ add contents to inventory
            player.pickUp(value);
            setValue(null);
            return "item withdrawn";
        }
        T inUse;
        try {
            inUse = (T) itemInUse;//ugly try-catch,if cant be cast return
        }catch (ClassCastException e) {
            return "item could not be stowed";
        }
        //box !=null --> put item now of type T in box (+ remove from player)
        setValue(inUse);
        player.removeItem(value);
        return "item stowed";
    }
}
package org.example.journeytosammich;

public class Box<T extends ItemHoldable> extends ItemUseSubject {
    private T value;
    private Character player;

    public Box(String rename, String redescription, Character rePlayer) {
        super(rename, redescription, null, null);
        player = rePlayer;
    }

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
        if(value!=null) {
            player.pickUp(value);
            setValue(null);
            return "item withdrawn";
        }
        System.out.println(1);
        T inUse;
            try {
                inUse = (T) itemInUse;
            }catch (ClassCastException e) {
                return "item could not be stowed";
            }
                if (value == null) {
                    setValue(inUse);
                    player.removeItem(value);
                    return "item stowed";
                }
        return "failed to stow object";
    }
}

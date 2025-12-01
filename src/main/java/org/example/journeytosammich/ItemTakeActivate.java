package org.example.journeytosammich;

public class ItemTakeActivate extends ItemHoldable{
    private String takeDesc;
    public ItemTakeActivate(String name, String description,String reTakeDesc) {
        super(name, description);
        takeDesc=reTakeDesc;
    }
    @Override
    public String activate(){
        roomChanger.enactChange();
        return takeDesc;
    }
}

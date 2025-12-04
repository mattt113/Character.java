package org.example.journeytosammich;

//Activates roomchanger on take. sorts for instanceof this class in pickup section of playerActions
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

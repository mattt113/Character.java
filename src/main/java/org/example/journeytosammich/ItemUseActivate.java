package org.example.journeytosammich;

// Activates room changer on iten used//can be used on other items
public class ItemUseActivate extends ItemHoldable{
    public ItemUseActivate(String name, String description) {
        super(name, description);
    }
    public ItemUseActivate(String name, String description,String reSoundEffect) {
        super(name, description);
        soundEffect=reSoundEffect;
    }
    @Override
    public String useItem(Item item){
        if (roomChanger!=null) {
            roomChanger.enactChange();
        }
        return "use on what?";
    }

}

package org.example.journeytosammich;

public class ItemMirrorTimer extends ItemUseSubject {
    Thread countDown;
    public ItemMirrorTimer(String name, String desc, String useDesc, AutoRoomAlterer breakMirror, Thread reCountDown, ItemHoldable rock) {
        super(name,desc,useDesc,breakMirror,rock);
        countDown=reCountDown;
    }
    @Override
    public String useItem(Item item){
        if((item==itemNeeded)&&(item!=null)) {
            if (roomChanger != null) {
                roomChanger.enactChange();
            }
            if(countDown!=null){
                countDown.start();
            }
            return useText;
        }
        return super.useItem(null);
    }
}

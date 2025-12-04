package org.example.journeytosammich;

import java.io.Serializable;

public class ItemMirrorTimer extends ItemUseSubject implements Serializable {
    //Thread countDown;
    private Character player;
    private AutoRoomAlterer resetMirror;
    public ItemMirrorTimer(String name, String desc, String useDesc, AutoRoomAlterer breakMirror, Character rePlayer, AutoRoomAlterer reResetMirror, ItemHoldable rock) {
        super(name,desc,useDesc,breakMirror,rock);
        player= rePlayer;
        resetMirror=reResetMirror;
       // countDown=reCountDown;
    }
    @Override
    public String useItem(Item item){
        if(item==itemNeeded) {
            if (roomChanger != null) {
                roomChanger.enactChange();
            }
            CountDown countDown=new CountDown(player,resetMirror);
            Thread countDownThread=new Thread(countDown);
            countDownThread.start();
            //        if(countDown!=null){
    //            countDown.start();
            // }
            return useText;
        }
        return super.useItem(null);
    }
}

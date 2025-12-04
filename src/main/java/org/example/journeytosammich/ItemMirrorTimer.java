package org.example.journeytosammich;

import java.io.Serializable;

//special class,starts countdown thread on correct item used on it. functions like parent. has sfx.
public class ItemMirrorTimer extends ItemUseSubject implements Serializable {
    private AutoRoomAlterer resetMirror;
    public ItemMirrorTimer(String name, String desc, String useDesc, AutoRoomAlterer breakMirror, Character rePlayer, AutoRoomAlterer reResetMirror, ItemHoldable rock) {
        super(name,desc,useDesc,breakMirror,rock);
        resetMirror=reResetMirror;
    }
    @Override
    public String useItem(Item item){
        if(item==itemNeeded) {//check if correct item
            if (roomChanger != null) {
                roomChanger.enactChange();
            }

            String soundEffectPlay=item.getSoundEffect();//plays sound effect on use( if it has one)
            if (soundEffectPlay!=null){
                NoiseMaker noiseMaker=new NoiseMaker(soundEffectPlay);
                System.out.println(1);
                noiseMaker.startAudio();
            }
            CountDown countDown=new CountDown(resetMirror);//makes and starts countdown on new thread
            Thread countDownThread=new Thread(countDown);
            countDownThread.start();
            return useText;
        }
        return super.useItem(null);
    }
}

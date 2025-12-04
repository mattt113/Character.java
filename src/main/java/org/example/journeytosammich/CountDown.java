package org.example.journeytosammich;

//for specific instance: break mirror, countdown before it fixes itself
public class CountDown extends Thread{
    private AutoRoomAlterer mirrorReset;    //what to do when time's up
    private long countFrom=30;

    public CountDown(AutoRoomAlterer reMirrorReset){
        mirrorReset=reMirrorReset;
    }
    public CountDown(AutoRoomAlterer reMirrorReset,long countDownTime){
        mirrorReset=reMirrorReset;
        countFrom=countDownTime;
    }

    @Override
    public void run(){//wait and enact change

        try {
            Thread.sleep(countFrom*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mirrorReset.enactChange();


    }


}

package org.example.journeytosammich;

public class CountDown extends Thread{
    Character player;
    AutoRoomAlterer mirrorReset;
    Room damned;
    long countFrom=10;


    public CountDown(Character rePlayer,AutoRoomAlterer reMirrorReset,Room reDamned){
        player=rePlayer;
        mirrorReset=reMirrorReset;
        damned=reDamned;
    }
    public CountDown(Character rePlayer,AutoRoomAlterer reMirrorReset,Room reDamned,long countDownTime){
        player=rePlayer;
        mirrorReset=reMirrorReset;
        damned=reDamned;
        countFrom=countDownTime;
    }

    @Override
    public void run(){

        try {
            Thread.sleep(countFrom*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        if(player.getCurrentRoom().getName().equals("bedmirror")||player.getCurrentRoom().getName().equals("hell")){
//            player.setCurrentRoom(damned);
//        }
        mirrorReset.enactChange();


    }


}

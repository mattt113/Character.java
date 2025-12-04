package org.example.journeytosammich;

public class CountDown extends Thread{
    Character player;
    AutoRoomAlterer mirrorReset;
    Room damned;
    long countFrom=30;


    public CountDown(Character rePlayer,AutoRoomAlterer reMirrorReset){
        player=rePlayer;
        mirrorReset=reMirrorReset;

    }
    public CountDown(Character rePlayer,AutoRoomAlterer reMirrorReset,Room reDamned,long countDownTime){
        player=rePlayer;
        mirrorReset=reMirrorReset;
        damned=reDamned;
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

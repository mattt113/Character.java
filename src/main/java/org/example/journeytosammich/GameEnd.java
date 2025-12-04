package org.example.journeytosammich;

import java.io.Serializable;

//enum example, teleports player to one of three final rooms
public class GameEnd implements Serializable {
    private Ending currentEnd;
    private Character player;
    private Room sandwich,sandwichDestroyed, dead;
    private enum Ending{        //token enum usage
        SANDWICH,
        NOSANDWICH,
        FUCKINGDEAD
    }

    public GameEnd(Character replayer,Room sandwichEaten,Room sandwichDropped, Room reDead){
        player=replayer;
        sandwich=sandwichEaten;   //rooms
        sandwichDestroyed=sandwichDropped;
        dead=reDead;
    }

    public void setEndSandwich(){
        currentEnd=Ending.SANDWICH;
    }
    public void setEndNoSandwich(){
        currentEnd=Ending.NOSANDWICH;
    }
    public void setEndFuckingDead(){
        currentEnd=Ending.FUCKINGDEAD;
    }

    //which end is active?
    public void endGame(){
        switch (currentEnd){
            case SANDWICH:
                player.setCurrentRoom(sandwich);
                break;
            case NOSANDWICH:
                player.setCurrentRoom(sandwichDestroyed);
                break;
            case FUCKINGDEAD:
                player.setCurrentRoom(dead);
                break;
        }
    }
}

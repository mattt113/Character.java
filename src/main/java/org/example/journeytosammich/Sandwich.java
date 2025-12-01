package org.example.journeytosammich;

public class Sandwich extends ItemEatable {

    public Sandwich(String name, String description, String reTakeDesc, GameEnd reGameEnd) {
        super(name, description,reTakeDesc,reGameEnd);
    }
    @Override
    public String activate(){
        gameEnd.setEndNoSandwich();
        gameEnd.endGame();
        roomChanger.enactChange();
        return takeDesc;
    }

    @Override
    public String getEatDescription() {
        gameEnd.setEndSandwich();
        gameEnd.endGame();
        roomChanger.enactChange();
        return "you consume the sandwich";
    }
}

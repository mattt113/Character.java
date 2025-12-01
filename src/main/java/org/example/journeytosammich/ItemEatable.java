package org.example.journeytosammich;

public class ItemEatable extends ItemHoldable{
    protected String takeDesc;
    protected GameEnd gameEnd;

    public ItemEatable(String name, String description, String reTakeDesc, GameEnd reGameEnd) {
        super(name, description);
        takeDesc=reTakeDesc;
        gameEnd=reGameEnd;
    }
    @Override
    public String getEatDescription() {
        gameEnd.setEndFuckingDead();
        gameEnd.endGame();
        roomChanger.enactChange();
        return "you consume it";
    }
}

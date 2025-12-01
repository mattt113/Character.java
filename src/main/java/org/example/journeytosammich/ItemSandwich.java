package org.example.journeytosammich;

public class ItemSandwich extends ItemHoldable{
    GameEnd end;
    Character player;
    ItemHoldable sandwich;
    public ItemSandwich(String name, String description,GameEnd ending,Character replayer) {
        super(name, description);
        end=ending;
        player=replayer;
    }

    @Override
    public String getEatDescription(){
        System.out.println("s");
        end.setEndSandwich();
        player.removeItem(this);
        end.endGame();
        return "nom";
    }
    @Override
    public void setLocation(Room room){
        end.setEndNoSandwich();
        end.endGame();
    }
}

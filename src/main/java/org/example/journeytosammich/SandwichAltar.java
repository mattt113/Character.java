package org.example.journeytosammich;

public class SandwichAltar extends ItemNoHold{
    private Character player;
    private int sandwichPieceCounter =0;
    private ItemHoldable sandwich;
    public SandwichAltar(String name, String description,Character rePlayer,ItemHoldable reSandwich) {
        super(name, description);
        player=rePlayer;
        sandwich=reSandwich;
    }

    @Override
    public String useItem(Item item){
        if (item instanceof SandwichComponent) {
            if (player.getInventory().contains(item)) {
                sandwichPieceCounter++;
                player.removeItem(item);
                if (sandwichPieceCounter == 4) {
                    player.pickUp(sandwich);
                    return "you are rewarded";
                }
                return "the altar accepts your offer";
            }
            return "you attempt to place the piece within the altar, but then remember it's on the groud.\nPick it the fuck up.";
        }

        return "the altar rejects your offering";
    }


}

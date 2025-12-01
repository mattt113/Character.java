package org.example.journeytosammich;

public class ItemUseSubject extends ItemNoHold{
    private Item itemNeeded;
    private String useText;


    public ItemUseSubject(String name, String description,String reUseDesc,AutoRoomAlterer reRoomChanger,Item item) {
        super(name, description);
        itemNeeded =item;
        roomChanger=reRoomChanger;
        useText=reUseDesc;
    }
    public ItemUseSubject(String name, String description,String reUseDesc, Item item) {
        super(name, description);
        itemNeeded =item;
        useText=reUseDesc;

    }



    @Override
    public String useItem(Item item){
        if((item==itemNeeded)&&(item!=null)) {
            if (roomChanger != null) {
                roomChanger.enactChange();
            }
            return useText;
        }
        return super.useItem(null);
    }
}

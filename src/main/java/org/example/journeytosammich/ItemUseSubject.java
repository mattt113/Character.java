package org.example.journeytosammich;

public class ItemUseSubject extends ItemNoHold{
    protected Item itemNeeded;
    protected String useText;


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

            String soundEffectPlay=item.getSoundEffect();
            if (soundEffect!=null){

                NoiseMaker noiseMaker=new NoiseMaker(soundEffectPlay);
                noiseMaker.startAudio();
            }
            return useText;
        }
        return super.useItem(null);
    }
}

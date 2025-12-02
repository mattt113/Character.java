package org.example.journeytosammich;

public class BoxOfBoxing<T extends ItemInterface> extends Item{
    private T value;
    private Character player;

    public BoxOfBoxing(String rename, String redescription,Character rePlayer) {
        super(rename, redescription);
        player=rePlayer;
    }


    public void setValue(T revalue) {
        value=revalue;
    }

    public T getValue(){
        return value;
    }
    public String getDesc(){
        return value.getInfo();
    }


    public String useItem(T input){
        if (value==null){
            value=  input;


        }
        else{

        }

        return "a";
    }

//    public void setContents(){
//
//    }
//    public T getContents(){
//        return null;
//    }
}

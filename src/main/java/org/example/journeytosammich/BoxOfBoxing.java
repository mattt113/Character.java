package org.example.journeytosammich;

public class BoxOfBoxing<T extends ItemInterface> extends Item{
    private T value;

    public BoxOfBoxing(String rename, String redescription) {
        super(rename, redescription);
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

//    public void setContents(){
//
//    }
//    public T getContents(){
//        return null;
//    }
}

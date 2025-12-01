package org.example.journeytosammich;

public class BoxOfBoxing<T extends ItemInterface>{
    private T value;


    public void setValue(T revalue){
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

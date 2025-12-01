package org.example.journeytosammich;

import java.util.HashMap;
import java.util.Map;

public class FurnitureMirror extends ItemNoHold{
    private int repeatedLook=0;


    FurnitureMirror(String rename,String description) {
        super(rename, description);
        id=03;
    }
    @Override
    public String getDescription() {
        repeatedLook++;
        return description;
    }
    public void exitsToMake(String roomName){

    }

    public void makeDoor(){

    }



}

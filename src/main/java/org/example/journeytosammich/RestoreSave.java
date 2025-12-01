package org.example.journeytosammich;

import java.io.*;


public class RestoreSave {
    public RestoreSave(){

    }
    public WorldMaker restoreWorld(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("world.ser"))) {
            WorldMaker player = (WorldMaker) in.readObject();
            //System.out.println("Object has been deserialized:"+player);
            return player;

        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            return null;
        }
    }
}

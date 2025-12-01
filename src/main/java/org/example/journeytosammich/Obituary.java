package org.example.journeytosammich;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//save
public class Obituary implements Serializable {

    public void save(WorldMaker thing) {
        //String saver= thing.toString() + ".ser";
        //System.out.println(saver);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("world.ser"))) {
            out.writeObject(thing);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("save error");
        }
    }
    public void dontSave(){
        String killSave=null;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("world.ser"))) {
            out.writeObject(killSave);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("save error");
        }
    }
}

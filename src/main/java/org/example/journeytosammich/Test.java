package org.example.journeytosammich;

public class Test {
    public static void main(String[] args){
        KeyPair<Integer,String> duo=new KeyPair<>(2,"2");
        System.out.println(duo.getKey());
        System.out.println(duo.getValue());
        duo.setKey(5);
        duo.setValue("agony");
        System.out.println(duo.getKey());
        System.out.println(duo.getValue());

//        ItemGun gun=new ItemGun("gun","a means to an end");
//        ItemStool stool=new ItemStool("stool","because you're short");
//        ItemSmallChild smallChild=new ItemSmallChild("child","why have you put this in a box?");
//        BoxOfBoxing<ItemGun> gunBox=new BoxOfBoxing<>();
//        BoxOfBoxing<ItemStool> stoolBox=new BoxOfBoxing<>();
//        BoxOfBoxing<ItemSmallChild> childBox=new BoxOfBoxing<>();
//        gunBox.setValue(gun);
//        stoolBox.setValue(stool);
//        childBox.setValue(smallChild);
//        System.out.println(gunBox.getDesc());
//        System.out.println(stoolBox.getDesc());
//        System.out.println(childBox.getDesc());

    }
}

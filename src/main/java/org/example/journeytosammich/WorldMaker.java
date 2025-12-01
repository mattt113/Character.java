package org.example.journeytosammich;

import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;

public class WorldMaker implements Serializable {
    private ArrayList<ItemHoldable> itemHoldables;
    private ArrayList<ItemNoHold> itemNoHolds;
    private ArrayList<AutoRoomAlterer> altererList;
    private ArrayList<Room> roomList;
    private Character player;
    private GameEnd ender;
    private Room hungyBed,emptyHall,hungyKitchen, kitchen, bed, hall, telly , fridge, livingRoom, diningRoom, wardrobeRoom, basement, abyss, balcony, space, sandwichEaten, sandwichDropped,perished, bedMirror, spookyBed;


    public WorldMaker(Stage stage) {

        roomList=new ArrayList<>();
        roomMakerStart();
        createIntroRooms();

        player=new Character("player", hungyBed);
        itemMaker(stage);
    }
    public Character getPlayer(){
        return player;
    }

    public void roomMakerStart() {
        altererList=new ArrayList<>();
        itemNoHolds=new ArrayList<>();

        roomList.add(bed = new Room("bedroom","your bed beckons, but you cannot rest, for your hunger remains","bedroomColour.png"));
        roomList.add(hall = new Room("hall","many doors line the hall, several of which are unexplored.","hall.png"));
        roomList.add(kitchen = new Room("kitchen","the kitchen is empty of food","kitchenclean.png"));
        roomList.add(fridge = new Room("fridge","in the fridge","wealthyfridge.png"));
        roomList.add(livingRoom = new Room("livingroom","the buss of static fills the room","tvRoom.png"));
        roomList.add(telly = new Room("telly","He seems very happy :)","theTelly.png"));
        roomList.add(diningRoom=new Room("diningroom","there is no food","DavesDiningRoom.png"));
        roomList.add(wardrobeRoom=new Room("wardrobe","the wardrobe is filled with stuff","wardrobe.png"));
        roomList.add(basement =new Room("basement","quite dark down here","basement.png"));
        roomList.add(abyss=new Room("abyss","the ceaseless void",""));
        roomList.add(balcony=new Room("balcony","the sky is beautiful tonight","balcony.png"));
        roomList.add(space=new Room("space","there are many stars.\n and butter!","spacebutter.png"));
        roomList.add(sandwichEaten=new Room("hooray","the sandwich is eaten!","glorybe.png"));
        roomList.add(sandwichDropped=new Room("tragedy","how could you?!","glorybegone.png"));
        roomList.add(perished=new Room("your dead!","you died",""));
        roomList.add(spookyBed=new Room("spookyBedroom","the air feels oppresive","bedroom-darkmirror.png"));
        roomList.add(bedMirror=new Room("bedmirror","a backwards land of backwords things","bedroommirrored.png"));

        fridge.setExit("kitchen", kitchen);
        bed.setExit("hall", hall);
        wardrobeRoom.setExit("bedroom",bed);
        hall.setExit("kitchen", kitchen);
        hall.setExit("bedroom",bed);
        hall.setExit("livingRoom",livingRoom);
        hall.setExit("diningRoom",diningRoom);
        hall.setExit("balcony",balcony);
        kitchen.setExit("diningRoom",diningRoom);
        kitchen.setExit("hall",hall);
        livingRoom.setExit("hall",hall);
        diningRoom.setExit("hall",hall);
        diningRoom.setExit("kitchen",kitchen);
        telly.setExit("livingRoom",livingRoom);
        basement.setExit("hall",hall);
        balcony.setExit("hall",hall);
        space.setExit("balcony",balcony);

    }

    public void itemMaker(Stage stage) {
        itemHoldables =new ArrayList<>();

        AutoRoomAlterer ender=new AutoRoomAlterer("ender");
        ender.addThing(stage);
        ItemHoldable endItem=new ItemTakeActivate("the-end","ends the game when taken","thanks for playing");
        endItem.addAlterer(ender);

        sandwichEaten.addItem(endItem);
        sandwichDropped.addItem(endItem);
        perished.addItem(endItem);

        ItemHoldable ham=new SandwichComponent("ham","the ham");
        ItemHoldable cheese=new SandwichComponent("cheese","the cheese");
        ItemHoldable butter=new SandwichComponent("butter","the Butter");
        ItemHoldable bread=new SandwichComponent("bread","the bread of breadening");

        //make bed items
        AutoRoomAlterer wardrobeInspect=new AutoRoomAlterer("wardrobetp");
        wardrobeInspect.addTeleport(player,wardrobeRoom);
        ItemNoHold wardrobe = new ItemInspectActivate(wardrobeInspect,"wardrobe", "heavy");
        ItemHoldable gun = new ItemUseActivate("gun", "a means to an end");

        ItemNoHold mirror = new ItemNoHold("mirror", "You stare into the mirror\nYour reflection stares back");



        ItemNoHold banana=new ItemNoHold("banana","banana");
        ItemNoHold banana2=new ItemNoHold("bananana","bananana");
        ItemNoHold banana3=new ItemNoHold("banananananana","banananananana");
        ItemNoHold banana4=new ItemNoHold("bananananananananananananana","bananananananananananan");
        ItemNoHold banana5=new ItemNoHold("bananananananananananananananananananananananananananananananananananananananananana",
                "bananananananananananananananananananananananananananananananananananananananananana");


        AutoRoomAlterer telescopeInspect=new AutoRoomAlterer("telescope");
        telescopeInspect.addTeleport(player,space);
        ItemNoHold telescopeInspectable=new ItemInspectActivate(telescopeInspect,"telescope","a telescope turned to the heavens");


        ItemHoldable butterNet=new ItemUseActivate("net","a net of grabbing distant things");
        ItemNoHold butterSpace=new ItemUseSubject("butter","the butter is out of reach.","the butter is yoinked",butterNet);
        AutoRoomAlterer butterTake=new AutoRoomAlterer("butterTake");
        butterTake.addThing(player,butter);
        butterTake.removeThing(space,butterSpace);
        butterTake.addImageChange(space,"space.png");
        butterTake.addDescChanger(space,"many stars.\n you try to count them but run out of fingers");
        butterSpace.addAlterer(butterTake);
        AutoRoomAlterer fridgeInspect =new AutoRoomAlterer("fridgetp");
        fridgeInspect.addTeleport(player,fridge);
        AutoRoomAlterer darkFridgeInspect=new AutoRoomAlterer("darkfirdgetp");
        darkFridgeInspect.addTeleport(player,kitchen);
        ItemNoHold darkFridgeObject = new ItemInspectActivate(darkFridgeInspect,"fridge", "there is no sandwich.");
        hungyKitchen.addItem(darkFridgeObject);



        ItemNoHold fridgeObject = new ItemNoHold("fridge", "It's empty");
        kitchen.addItem(fridgeObject);

        AutoRoomAlterer tellyInspect=new AutoRoomAlterer("tvtp");

        tellyInspect.addTeleport(player,telly);
        ItemNoHold tellyObject=new ItemInspectActivate(tellyInspect,"telly","static fills the screen");
        livingRoom.addItem(tellyObject);

        AutoRoomAlterer tvFaceUse=new AutoRoomAlterer("tvshoot");
        AutoRoomAlterer hamTake=new AutoRoomAlterer("hamtake");
        ItemHoldable tempHam =new ItemTakeActivate("Ham","sammich component","the Ham is yours!");
        hamTake.removeThing(player, tempHam);
        hamTake.removeThing(diningRoom,banana);
        hamTake.addThing(diningRoom,banana2);
        hamTake.addDescChanger(telly,"the ham is yours");
        hamTake.addImageChange(telly,"latehamless.png");


        hamTake.addThing(player,ham);
        hamTake.removeThing(kitchen,fridgeObject);
        hamTake.addDescChanger(kitchen,"the kitchen feels different");
        hamTake.addImageChange(kitchen,"kitchen.png");
        ItemNoHold fridgeInspectable=new ItemInspectActivate(fridgeInspect,"fridge","fridge");
        hamTake.addThing(kitchen,fridgeInspectable);
        tempHam.addAlterer(hamTake);

        ItemNoHold tvFace=new ItemUseSubject("face","face","the shotgun eviscerate the telly ghost " +
                "and the regular program resumes",gun);
        tvFaceUse.removeThing(telly,tvFace);
        tvFaceUse.addDescChanger(telly,"he possesses the Ham!");
        tvFaceUse.addThing(telly, tempHam);
        tvFaceUse.addImageChange(telly,"lateham.png");
        tvFace.addAlterer(tvFaceUse);

        telly.addItem(tvFace);

        ItemHoldable lifeSavingsUsable=new ItemUseActivate("life savings","literally all of your money");
        AutoRoomAlterer savingsTake=new AutoRoomAlterer("savingsTake");
        savingsTake.addThing(player,lifeSavingsUsable);
        ItemHoldable lifeSavings=new ItemTakeActivate("life savings","literally all of your money","money");
        savingsTake.removeThing(player,lifeSavings);
        savingsTake.addImageChange(fridge,"fridge.png");
        lifeSavings.addAlterer(savingsTake);

        AutoRoomAlterer jimSwitch=new AutoRoomAlterer("jimswitch");
        AutoRoomAlterer jimSwitch2=new AutoRoomAlterer("jimSwitch2");
        ItemNoHold safeCracker=new ItemUseSubject("Jim the despiser of safes ","He fucking hates safes","alright",lifeSavingsUsable);
        ItemHoldable safeCrackerTakable =new ItemTakeActivate("Jim the despiser of safes","take him to the safe","you hoist jim onto your back");
        ItemHoldable safeCrackerUsable=new ItemUseActivate("Jim the despiser of safes","take him to the safe");
        jimSwitch2.addImageChange(diningRoom,"DiningRoom.png");
        jimSwitch2.addThing(player,safeCrackerUsable);
        jimSwitch2.removeThing(player, safeCrackerTakable);

        jimSwitch.removeThing(diningRoom,safeCracker);
        jimSwitch.addThing(diningRoom, safeCrackerTakable);
        jimSwitch.removeThing(player,lifeSavingsUsable);

        safeCracker.addAlterer(jimSwitch);
        safeCrackerTakable.addAlterer(jimSwitch2);
        diningRoom.addItem(safeCracker);



        ItemNoHold safe=new ItemUseSubject("safe","the bread safe contains the emergency bread supply","the safe is cracked",safeCrackerUsable);

        GameEnd gameEnd=new GameEnd(player,sandwichEaten,sandwichDropped,perished);
        Sandwich sandwich=new Sandwich("sandwich","sandwich","you monster",gameEnd);
        AutoRoomAlterer sandwichDelete=new AutoRoomAlterer("sandwichKiller");
        sandwichDelete.removeThing(player,sandwich);
        sandwich.addAlterer(sandwichDelete);

        ItemNoHold sandwichAltar=new SandwichAltar("sandwich-altar","a holy place for the construction of sandwiches",player,sandwich);

        AutoRoomAlterer mirrorInspect=new AutoRoomAlterer("mirror-inspect");
        mirrorInspect.addTeleport(player,spookyBed);
        ItemNoHold mirrorInspectable=new ItemInspectActivate(mirrorInspect,"mirror","You stare at the mirror.\nYour reflection stares back.");



        AutoRoomAlterer safeCracked=new AutoRoomAlterer("safe");
        ItemNoHold borkedSafe=new ItemNoHold("borked safe","its really bronked");
        safeCracked.removeThing(player,safeCrackerUsable);
        safeCracked.removeThing(bed,safe);
        safeCracked.addThing(bed,borkedSafe);
        safeCracked.removeThing(diningRoom,banana2);
        safeCracked.addThing(diningRoom,banana3);
        safeCracked.addThing(wardrobeRoom,bread);
        safeCracked.addImageChange(wardrobeRoom,"wardrobesafecracked.png");
        safeCracked.removeThing(wardrobeRoom,safe);
        safeCracked.addThing(wardrobeRoom,borkedSafe);
        safeCracked.addThing(kitchen,sandwichAltar);
        safeCracked.addThing(hall,"basement",basement);
        safeCracked.removeThing(bed,mirror);
        safeCracked.addThing(bed,mirrorInspectable);
        safe.addAlterer(safeCracked);
        wardrobeRoom.addItem(safe);

        ItemNoHold table=new ItemNoHold("table","very long");
        diningRoom.addItem(table);
        diningRoom.addItem(banana);

        ItemNoHold basementMan=new ItemNoHold("basement man","he's not worth your time.");
        AutoRoomAlterer boxInspect=new AutoRoomAlterer("box");
        boxInspect.addThing(basement,butterNet);
        ItemNoHold boxes=new ItemInspectActivate(boxInspect,"boxes","filled with stuff.");



        bed.addItem(wardrobe);
        bed.addItem(mirror);
        bed.addItem(gun);

        fridge.addItem(lifeSavings);

        basement.addItem(basementMan);
        basement.addItem(boxes);


        balcony.addItem(telescopeInspectable);
        space.addItem(butterSpace);

        itemHoldables.add(gun);
        itemHoldables.add(safeCrackerTakable);
        itemHoldables.add(lifeSavings);
        itemHoldables.add(bread);
        itemHoldables.add(ham);
        itemHoldables.add(cheese);
        itemHoldables.add(butter);
        itemHoldables.add(butterNet);
        itemHoldables.add(sandwich);
        itemHoldables.add(endItem);

        itemNoHolds.add(safe);
        itemNoHolds.add(wardrobe);
        itemNoHolds.add(mirror);
        itemNoHolds.add(fridgeInspectable);
        itemNoHolds.add(fridgeObject);
        itemNoHolds.add(darkFridgeObject);
        itemNoHolds.add(safe);
        itemNoHolds.add(safeCracker);
        itemNoHolds.add(borkedSafe);
        itemNoHolds.add(tvFace);
        itemNoHolds.add(telescopeInspectable);
        itemNoHolds.add(butterSpace);
        itemNoHolds.add(banana);
        itemNoHolds.add(banana5);
        itemNoHolds.add(banana2);
        itemNoHolds.add(banana3);
        itemNoHolds.add(banana4);
        itemNoHolds.add(sandwichAltar);

        //altererList.add(wardrobeInspect);
        altererList.add(hamTake);
        altererList.add(tvFaceUse);
        altererList.add(safeCracked);
        altererList.add(jimSwitch);
        altererList.add(tvFaceUse);
    }

    public void createIntroRooms(){
        roomList.add(hungyBed = new Room("introbedroom","you awake, hungry.\nyou recall the sandwich you stored in your kitchen the night before" +
                "\nyou arise to go in search of the sandwich.","bedroomstart.png"));
        roomList.add(emptyHall = new Room("introhall","the door to the kitchen lies ahead","oldhalldark.png"));
        roomList.add(hungyKitchen = new Room("introkitchen","behold the fridge, where the sandwich resides","kitchenfridge.png"));

        hungyBed.setExit("hall", emptyHall);
        emptyHall.setExit("kitchen", hungyKitchen);
    }
    public void makeItem(String name) {
        if(name.equals("all")){
            for(ItemHoldable item: itemHoldables){
                player.getCurrentRoom().addItem(item);
            }
            for (ItemNoHold item:itemNoHolds){
                player.getCurrentRoom().addItem(item);
            }
        }
        for(ItemHoldable item: itemHoldables){
            if(item.getName().equals(name)) {
                player.getCurrentRoom().addItem(item);
            }
        }
        for (ItemNoHold item:itemNoHolds){
            if(item.getName().equals(name)) {
                player.getCurrentRoom().addItem(item);
            }
        }

    }

    public Room getRoom(String name) {
        for (Room room:roomList){
            if(name.equals(room.getName())){
                return room;
            }
        }
        return null;
    }

    public void activateRoomAlter(String name){

        if(name.equals("all")){
            for(AutoRoomAlterer alterer: altererList){
                alterer.enactChange();
            }
        }
        for(AutoRoomAlterer alterer: altererList){
            if(alterer.getName().equals(name)) {
                alterer.enactChange();
            }
        }
    }
    public ArrayList<Room> getRooms(){
        return roomList;
    }
    public ArrayList<ItemHoldable> getItemHoldables(){
        return itemHoldables;
    }
    public ArrayList<ItemNoHold> getItemNoHolds(){
        return itemNoHolds;
    }
    public ArrayList<AutoRoomAlterer> getAltererList(){
        return altererList;
    }
}
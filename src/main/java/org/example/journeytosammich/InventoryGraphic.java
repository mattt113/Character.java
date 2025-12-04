package org.example.journeytosammich;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;

public class InventoryGraphic {
    private VBox inventoryContainer;
    private Button currentButton,self,room;
    private OptionButtons controls;
    private Launcher game;
    private PictureGraphic pictureMaker;
    private ArrayList<Button> selfInventory=new ArrayList<Button>();
    private ArrayList<Button> roomInventory=new ArrayList<Button>();

    //initialises above+makes self+room buttons
    public InventoryGraphic(OptionButtons reControls, Launcher regame, PictureGraphic rePictureMaker){
        game=regame;
        pictureMaker=rePictureMaker;
        inventoryContainer = new VBox();
        inventoryContainer.setPadding(new Insets(15, 12, 15, 12));
        inventoryContainer.setSpacing(10);

        //OptionButtons buttons=new OptionButtons();
        controls=reControls;
        self = new Button("self");
        self.setPrefSize(150, 30);
        selfInventory.add(self);
        self.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                controls.setButtonsText(selfInventory,roomInventory,self);
                currentButton=self;
            }
        });

        room = new Button("room");
        room.setPrefSize(150, 30);
        roomInventory.add(room);
        room.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                controls.setButtonsText(selfInventory,roomInventory,room);
                currentButton=room;
            }
        });
        inventoryContainer.getChildren().addAll(selfInventory);

        inventoryContainer.getChildren().addAll(roomInventory);

        updateGraphic();
    }

//    redoes the two inventories + updates graphics
    public void updateGraphic(){
        selfInventory.clear();
        roomInventory.clear();
        selfInventory.add(self);
        roomInventory.add(room);

        for(Item item:game.getPlayer().getCurrentRoom().getContents()){
            addButtonRoom(item.getName());

        }
        for(Item item:game.getPlayer().getInventory()){
            addButtonSelf(item.getName());

        }
        ObservableList<Node> buttons = inventoryContainer.getChildren();
        buttons.clear();
        buttons.addAll(selfInventory);
        buttons.addAll(roomInventory);
    }
//    puts inventory in a scroll pane
    public ScrollPane addInventory() {
        ScrollPane scroller=new ScrollPane(inventoryContainer);
        return scroller;
    }

//    Makes button and add it to roomInventory
    public void addButtonRoom(String item){
        Button newButton=new Button(item);
        roomInventory.add(newButton);

        newButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){      //  when pressed sets text (pick up/drop);
                controls.setButtonsText(selfInventory,roomInventory,newButton);
                currentButton=newButton;            //sets cyrrent button to this button
            }
        });
    }
//    Makes button and adds it to self
    public void addButtonSelf(String item){
        Button newButton=new Button(item);
        selfInventory.add(newButton);


        newButton.setOnAction(new EventHandler<ActionEvent>(){//same as other one
            @Override
            public void handle(ActionEvent event){
                controls.setButtonsText(selfInventory,roomInventory,newButton);
                currentButton=newButton;
            }
        });
    }
    public ArrayList<Button> getRoomInventory(){
        return roomInventory;
    }
    public ArrayList<Button> getSelfInventory(){
        return selfInventory;
    }
    //returns button currently in use
    public Button getCurrentButton(){
        return currentButton;
    }

    public HBox addSaveOptions(){
        Button save=new Button("save");
        Button unsave=new Button("unsave");
        Button quit=new Button("quit");
        save.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                pictureMaker.textSetter(game.save());
            }
        });
        unsave.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
               pictureMaker.textSetter( game.deleteSave());
            }
        });
        quit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                GuiMaker.endGame();
            }
        });


        HBox hBox=new HBox(save,unsave,quit);
        hBox.setPadding(new Insets(1, 15, 0, 150));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #339699;");//colour (:
        return hBox;
    }
}


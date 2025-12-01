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
import javafx.stage.Stage;

import java.util.ArrayList;

public class InventoryGraphic {
    private VBox vbox;
    private Button currentButton,self,room;
    private OptionButtons controls;
    private Launcher game;

    private ArrayList<Button> selfInventory=new ArrayList<Button>();
    private ArrayList<Button> roomInventory=new ArrayList<Button>();
    public InventoryGraphic(OptionButtons reControls,Launcher regame){
        game=regame;
        vbox = new VBox();
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);

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
        vbox.getChildren().addAll(selfInventory);

        vbox.getChildren().addAll(roomInventory);

        updateGraphic();
    }
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
        ObservableList<Node> children=vbox.getChildren();
        children.clear();
        children.addAll(selfInventory);
        children.addAll(roomInventory);
    }
    public ScrollPane addInventory() {
        ScrollPane scroller=new ScrollPane(vbox);


        return scroller;
    }


    public void addButtonRoom(String item){
        Button newButton=new Button(item);
        roomInventory.add(newButton);

        newButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                controls.setButtonsText(selfInventory,roomInventory,newButton);
                currentButton=newButton;
            }
        });
    }
    public void addButtonSelf(String item){
        Button newButton=new Button(item);
        selfInventory.add(newButton);


        newButton.setOnAction(new EventHandler<ActionEvent>(){
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
    public Button getCurrentButton(){
        return currentButton;
    }

    public HBox addSaveOptions(Stage stage){
        Button save=new Button("save");
        Button unsave=new Button("unsave");
        Button quit=new Button("quit");
        save.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                game.save();
            }
        });
        unsave.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                game.deleteSave();
            }
        });
        quit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                stage.close();
            }
        });


        HBox hBox=new HBox(save,unsave,quit);
        hBox.setPadding(new Insets(1, 15, 0, 150));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #339699;");
        return hBox;
    }
}


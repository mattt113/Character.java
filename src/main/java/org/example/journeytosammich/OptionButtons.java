package org.example.journeytosammich;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.util.Scanner;

import java.util.ArrayList;

public class OptionButtons {
    Launcher game;
    PictureGraphic pictureMaker;
    InventoryGraphic inventory;
    Button inspectButton, useButton, pickupButton, eatButton,moveButton;
    GridPane grid;
    AnchorPane anchor;
    FlowPane movementOptions;


    public OptionButtons(Launcher regame,PictureGraphic repictureMaker,double width,double height) {
        game=regame;
        pictureMaker=repictureMaker;
        inspectButton = new Button("inspect");
        useButton = new Button("Use");
        pickupButton = new Button("Pickup");
        eatButton = new Button("eat");
        moveButton=new Button("move");
        setButtonsSize(width,height);

        anchor=new AnchorPane();
        anchor.setPrefSize(((width/3)*2),((height/4)*2));

        moveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                makeMoveOptions();
                intializeAnchor();
                setAnchorMovement();
                pictureMaker.updateImage();
                pictureMaker.selectTab(0);
            }
        });
        inspectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(inventory.getCurrentButton()!=null) {
                    pictureMaker.textSetter(game.getActions().inspect(inventory.getCurrentButton().getText()));
                    inventory.updateGraphic();
                }
                pictureMaker.updateImage();
                pictureMaker.selectTab(1);
            }
        });
        useButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(inventory.getCurrentButton()!=null) {
                    if (inventory.getCurrentButton().getText().equals("self")) {
                        pictureMaker.textSetter("You have no use");
                    } else if (inventory.getCurrentButton().getText().equals("room")) {
                        pictureMaker.textSetter("cannot use room");
                    } else if (inventory.getCurrentButton() != null) {
                        pictureMaker.textSetter(game.useItem(inventory.getCurrentButton().getText()));
                    }
                }
                else{
                    pictureMaker.textSetter("select button");
                }
                inventory.updateGraphic();
                pictureMaker.updateImage();
                pictureMaker.selectTab(1);
            }
        });
        pickupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(inventory.getRoomInventory().contains(inventory.getCurrentButton())){
                    pictureMaker.textSetter(game.getItem(inventory.getCurrentButton().getText()));
                }
                else if(inventory.getSelfInventory().contains(inventory.getCurrentButton())){
                    pictureMaker.textSetter(game.dropItem(inventory.getCurrentButton().getText()));
                }
                else{
                    pictureMaker.textSetter("select button");
                }
                inventory.updateGraphic();
                pictureMaker.updateImage();
                pictureMaker.selectTab(1);
            }
        });
        eatButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(inventory.getCurrentButton()!=null) {
                    pictureMaker.textSetter(game.eatItem(inventory.getCurrentButton().getText()));
                }
                else{
                    pictureMaker.textSetter("select button");
                }
                inventory.updateGraphic();
                pictureMaker.updateImage();
                pictureMaker.selectTab(1);
            }
        });
    }


    public AnchorPane getButtons(){
        anchor.getChildren().add(grid);
        return anchor;
    }
    public void setAnchorOptions(){
        anchor.getChildren().clear();
        anchor.getChildren().add(grid);
    }
    public void setAnchorMovement(){
        anchor.getChildren().clear();
        anchor.getChildren().add(movementOptions);
    }
    public void setButtonsSize(double width,double height){
        grid=new GridPane();
        grid.add(moveButton,0,0,2,1);
        grid.add(inspectButton,0,1);
        grid.add(useButton,1,1);
        grid.add(pickupButton,0,2);
        grid.add(eatButton,1,2);

        setButtonSizes(width,height);
        grid.setPadding(new Insets(23, 15, 25, 15));
        grid.setStyle("-fx-background-color: #339699;");
    }

    public void setButtonsText(ArrayList<Button> selfInventory, ArrayList<Button> roomInventory, Button item) {

        if (selfInventory.contains(item)) {
            pickupButton.setText("Drop");
        }
        if (roomInventory.contains(item)) {
            pickupButton.setText("Pick up");
        }

    }
    public void initialize(InventoryGraphic reinventory){
        inventory=reinventory;
    }
    public void setButtonSizes(double width,double height){
        double buttonWidth=(((width/3)*2)-30)/2;
        double buttonHeight=(((height/3)*1)-30)/2;

        inspectButton.setPrefSize(buttonWidth,buttonHeight);
        useButton.setPrefSize(buttonWidth,buttonHeight);
        pickupButton.setPrefSize(buttonWidth,buttonHeight);
        eatButton.setPrefSize(buttonWidth,buttonHeight);
        moveButton.setPrefSize((buttonWidth*2),buttonHeight);
    }
    public void makeMoveOptions(){
        movementOptions=new FlowPane();
        movementOptions.setPadding(new Insets(15, 15, 30, 15));
        movementOptions.setVgap(8);
        movementOptions.setHgap(4);
        movementOptions.setStyle("-fx-background-color: #339699;");

        String rooms=game.getPlayer().getCurrentRoom().getExitString();
        Scanner seperator=new Scanner(rooms);
        while(true){
            if(!seperator.hasNext()) {
                break;
            }
            else{
                Button newButton=new Button(seperator.next());
                newButton.setPrefSize(100,40);
                movementOptions.getChildren().add(newButton);

                newButton.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        game.goRoom(newButton.getText());
                        setAnchorOptions();
                        inventory.updateGraphic();
                        pictureMaker.updateImage();
                        //inventory.updateGraphic();
                    }
                });
            }
        }
        Button back=new Button("Dont move");
        back.setPrefSize(100,40);
        movementOptions.getChildren().add(back);
        back.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                setAnchorOptions();
            }
        });

    }
    public void intializeAnchor(){
        AnchorPane.setBottomAnchor(movementOptions, 0.0);
        AnchorPane.setRightAnchor(movementOptions, 0.0);
        AnchorPane.setTopAnchor(movementOptions, 0.0);
        AnchorPane.setLeftAnchor(movementOptions,0.0);

        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid,0.0);
    }
}

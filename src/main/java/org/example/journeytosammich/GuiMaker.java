package org.example.journeytosammich;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GuiMaker extends Application {// implements EventHandler<ActionEvent> {
    OptionButtons buttons;
    ScrollPane inventoryScroll;
    InventoryGraphic inventory;
    PictureGraphic pictureMaker;
    AnchorPane anchor;
    HBox saveOptions;
    TabPane graphic;
    AnchorPane actions;
    double width = 1500;
    double height = 800;
    private static Stage staticStage;

    @Override
    public void start(Stage stage) throws IOException {
        staticStage = stage;
        Launcher game = new Launcher();
        pictureMaker = new PictureGraphic(game, width, height);
        buttons = new OptionButtons(game, pictureMaker, width, height);
        inventory = new InventoryGraphic(buttons, game,pictureMaker);
        pictureMaker.addInventoryGraphic(inventory);
        inventoryScroll = inventory.addInventory();

        saveOptions = inventory.addSaveOptions();
        graphic = pictureMaker.addImage();
        buttons.initialize(inventory);
        actions = buttons.getButtons();

        anchor = new AnchorPane(inventoryScroll, graphic, actions, saveOptions);
        setSizes();

        NoiseMaker noiseMaker = new NoiseMaker("adventureline.wav",true);
        noiseMaker.startAudio();

        Scene scene = new Scene(anchor, width, height);
        stage.setTitle("Hello World!");

        stage.setScene(scene);
        stage.show();


        width = scene.getWidth();

    }


    public void setSizes() {

        AnchorPane.setTopAnchor(graphic, 0.0);
        AnchorPane.setLeftAnchor(graphic, 0.0);
        AnchorPane.setRightAnchor(graphic, 0.0);

        AnchorPane.setBottomAnchor(actions, 0.0);
        AnchorPane.setLeftAnchor(actions, 0.0);

        //AnchorPane.setBottomAnchor(graphic,0.0);
        AnchorPane.setRightAnchor(graphic, (width / 3));

        AnchorPane.setBottomAnchor(inventoryScroll, 0.0);
        AnchorPane.setRightAnchor(inventoryScroll, 0.0);
        AnchorPane.setTopAnchor(inventoryScroll, 25.0);
        AnchorPane.setLeftAnchor(inventoryScroll, ((width / 3) * 2));

        AnchorPane.setBottomAnchor(saveOptions, (height - 30));
        AnchorPane.setRightAnchor(saveOptions, 0.0);
        AnchorPane.setTopAnchor(saveOptions, 0.0);
        AnchorPane.setLeftAnchor(saveOptions, ((width / 3) * 2));
    }

    public static void endGame() {
        staticStage.close();
    }
}

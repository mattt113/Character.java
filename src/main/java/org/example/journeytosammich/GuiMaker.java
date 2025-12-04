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
    private ScrollPane inventoryScroll;
    private HBox saveOptions;
    private TabPane graphic;
    private AnchorPane actions;
    private double width = 1500;
    private double height = 800;
    private static Stage staticStage;//for closing game


    // i reckon this is similar to the start in Thread
    @Override
    public void start(Stage stage) throws IOException {
        staticStage = stage;
        Launcher game = new Launcher();//start backend

        //make objects and make sure they contain each other as desired
        PictureGraphic pictureMaker = new PictureGraphic(game, width, height);
        //all the different bits. here largely for resizing anchor
        OptionButtons buttons = new OptionButtons(game, pictureMaker, width, height);
        InventoryGraphic inventory = new InventoryGraphic(buttons, game, pictureMaker);
        pictureMaker.addInventoryGraphic(inventory);
        inventoryScroll = inventory.addInventory();
        saveOptions = inventory.addSaveOptions();
        graphic = pictureMaker.addImage();
        buttons.initialize(inventory);
        actions = buttons.getButtons();

        //contains every other node
        AnchorPane anchor = new AnchorPane(inventoryScroll, graphic, actions, saveOptions);
        setSizes();//does as it says

        NoiseMaker noiseMaker = new NoiseMaker("adventureline.wav",true);
        noiseMaker.startAudio();//this music won't get annoying

        //goes in stage
        Scene scene = new Scene(anchor, width, height);
        stage.setTitle("Sammich");

        stage.setScene(scene);
        stage.show();//start game
    }

//resizes everything
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
    //does as described
    public static void endGame() {
        staticStage.close();
    }
}

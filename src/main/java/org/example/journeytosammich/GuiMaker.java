package org.example.journeytosammich;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiMaker extends Application{// implements EventHandler<ActionEvent> {
    OptionButtons buttons;
    ScrollPane inventoryScroll;
    InventoryGraphic inventory;
    PictureGraphic pictureMaker;
    AnchorPane anchor;
    HBox saveOptions;
    TabPane graphic;
    AnchorPane actions;
    double width=1500;
    double height=800;

    @Override
    public void start(Stage stage) throws IOException {
        Launcher game = new Launcher(stage);
        pictureMaker=new PictureGraphic(game,width,height);
        buttons=new OptionButtons(game,pictureMaker,width,height);
        inventory=new InventoryGraphic(buttons,game);
        pictureMaker.addInventoryGraphic(inventory);
        inventoryScroll = inventory.addInventory();

        saveOptions=inventory.addSaveOptions(stage);
        graphic=pictureMaker.addImage();
        buttons.initialize(inventory);
        actions=buttons.getButtons();

        anchor = new AnchorPane(inventoryScroll,graphic,actions,saveOptions);
        setSizes();
//        startAudio();
        Scene scene = new Scene(anchor, width, height);
        stage.setTitle("Hello World!");

        stage.setScene(scene);
        stage.show();


        width=scene.getWidth();

    }



    public void setSizes() {

        AnchorPane.setTopAnchor(graphic, 0.0);
        AnchorPane.setLeftAnchor(graphic, 0.0);
        AnchorPane.setRightAnchor(graphic, 0.0);

        AnchorPane.setBottomAnchor(actions, 0.0);
        AnchorPane.setLeftAnchor(actions, 0.0);

        //AnchorPane.setBottomAnchor(graphic,0.0);
        AnchorPane.setRightAnchor(graphic, (width/3));

        AnchorPane.setBottomAnchor(inventoryScroll, 0.0);
        AnchorPane.setRightAnchor(inventoryScroll, 0.0);
        AnchorPane.setTopAnchor(inventoryScroll, 25.0);
        AnchorPane.setLeftAnchor(inventoryScroll, ((width / 3) * 2));

        AnchorPane.setBottomAnchor(saveOptions, (height-30));
        AnchorPane.setRightAnchor(saveOptions, 0.0);
        AnchorPane.setTopAnchor(saveOptions, 0.0);
        AnchorPane.setLeftAnchor(saveOptions, ((width / 3) * 2));
    }
//    public void startAudio() { //resource: https://www.youtube.com/watch?v=P856ukheHeE
//        File audioFile = new File("src/main/resources/org/example/journeytosammich/adventureline.wav");
//        AudioInputStream audioStream = null;
//        try {
//            audioStream = AudioSystem.getAudioInputStream(audioFile);
//        } catch (UnsupportedAudioFileException e) {
//           // throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Clip clip = null;
//        try {
//            clip = AudioSystem.getClip();
//        } catch (LineUnavailableException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            clip.open(audioStream);
//        } catch (LineUnavailableException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        clip.start();
//    }
}

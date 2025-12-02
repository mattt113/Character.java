package org.example.journeytosammich;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PictureGraphic {
    private String picture;
    private String[] pictureList;
    private TabPane tabs;
    ImageView imagePrint;
    InventoryGraphic inventory;
    TextArea textArea;
    TextArea consoleText;
    Launcher game;
    double width;
    double height;
    long defaultTime=10;
    SingleSelectionModel<Tab> selectionModel;


    public PictureGraphic(Launcher regame,double rewidth,double reheight){
        game=regame;
        width=rewidth;
        height=reheight;

        imagePrint=new ImageView();

        Tab image=new Tab("picture",imagePrint);
        image.setClosable(false);
        Tab text=new Tab("text",descDisplay());
        text.setClosable(false);
        Tab console=new Tab("console",makeConsole());
        console.setClosable(false);
        tabs=new TabPane(image,text,console);
        selectionModel = tabs.getSelectionModel();

    }

    public void selectTab(int i){
        selectionModel.select(i);
    }
    public TextArea descDisplay(){

        textArea=new TextArea();
        textArea.setEditable(false);
//        Pane textArea=new Pane(text);
//        TextFlow textArea=new TextFlow();
//        textArea.setPadding(new Insets(20,20,20,20));
//        //textArea.setStyle("-fx-background-color: #339699;");
//        descPrinter = new Text();
//        textArea.getChildren().add(descPrinter);
        return textArea;
    }
    public BorderPane makeConsole(){
        consoleText=new TextArea();
        Button checkCommand=new Button("input command");
        checkCommand.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textSetter(game.textInput(consoleText.getText()));
              //  textArea.selectAll();
                //consoleText.setText("a");
                //resetConsole();
                inventory.updateGraphic();
                updateImage();

            }
        });

        BorderPane console=new BorderPane(consoleText,null,null,checkCommand,null);
        return console;
    }
    public void resetConsole(){
        consoleText.clear();
    }
    public void textSetter(String words){
        //Printer printer=new Printer();
//        for(int i=0;i<words.length();i++) {
//
//            textArea.appendText(String.valueOf(words.charAt(i)));
//            halt(defaultTime);
//            updateImage();
//        }
        textArea.appendText(words+"\n");
//        descPrinter.setText(words);
    }

    public TabPane addImage(){
        updateImage();
        return tabs;
    }
    public void updateImage(){
        picture=game.getPlayer().getCurrentRoom().getImage();
        Image image=new Image(getClass().getResourceAsStream(picture),((width/3)*2),323,false,false);
        imagePrint.setImage(image);

    }


    public void addInventoryGraphic(InventoryGraphic reInventory) {
        inventory=reInventory;
    }
}

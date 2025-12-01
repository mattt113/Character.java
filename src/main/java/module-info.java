module org.example.journeytosammich {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.base;
    //requires org.example.journeytosammich;


    opens org.example.journeytosammich to javafx.fxml;
    exports org.example.journeytosammich;
}
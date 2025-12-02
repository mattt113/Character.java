package org.example.journeytosammich;

public interface ItemInterface {
    String getInfo();
    String useItem(Item item);
    String getDescription();
    void setDescription(String description);
    void setName(String name);
    void addAlterer(AutoRoomAlterer alterer);
    String activate();
    String getEatDescription();
}

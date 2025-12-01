package org.example.journeytosammich;

import java.io.Serializable;

public class ItemInspectActivate extends ItemNoHold implements Serializable {
    AutoRoomAlterer roomChanger;

    ItemInspectActivate(AutoRoomAlterer reRoomChanger, String rename, String redescription) {
        super(rename, redescription);
        roomChanger=reRoomChanger;
    }
    @Override
    public String getDescription() {
        roomChanger.enactChange();
        return description;
    }
}

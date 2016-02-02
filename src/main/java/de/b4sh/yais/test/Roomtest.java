package de.b4sh.yais.test;

import de.b4sh.yais.mdl.InstanceHandler;
import de.b4sh.yais.mdl.Room;

public class Roomtest {

    public static void createTestRooms(InstanceHandler instanceHandler){
        instanceHandler.addRoom(new Room(instanceHandler.getNextRoomId(),"Raum 301"));
        instanceHandler.addRoom(new Room(instanceHandler.getNextRoomId(),"Raum 302"));
        instanceHandler.addRoom(new Room(instanceHandler.getNextRoomId(),"Raum 303"));
        instanceHandler.addRoom(new Room(instanceHandler.getNextRoomId(),"Raum 304"));
        instanceHandler.addRoom(new Room(instanceHandler.getNextRoomId(),"Raum 305"));
    }

}

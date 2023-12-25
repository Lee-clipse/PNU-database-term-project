package org.example;

import java.util.List;

public class ManagerClient {
    public void showOccupiedRooms() {
        HotelManagement management = new HotelManagement();
        List<String> occupiedRooms = management.getOccupiedRooms();
        for (String room : occupiedRooms) {
            System.out.println(room);
        }
    }
}
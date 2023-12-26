package org.example;

import java.util.List;

public class ManagerClient {
    public void showOccupiedRooms(String currentDate) {
        HotelManagement management = new HotelManagement();
        List<String> occupiedRooms = management.getOccupiedRooms(currentDate);
        for (String room : occupiedRooms) {
            System.out.println(room);
        }
    }
}
package org.example;

import java.util.List;

public class CustomerClient {
    public void showRoomsAvailable(String currentDate) {
        HotelManagement management = new HotelManagement();
        List<String> availableRooms = management.getAvailableRooms(currentDate);
        for (String room : availableRooms) {
            System.out.println(room);
        }
    }
}

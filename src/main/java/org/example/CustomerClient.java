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

    public void showCostAtCheckout (int customerId, String currentDate) {
        HotelManagement management = new HotelManagement();
        double totalCost = management.calculateCostAtCheckout(customerId, currentDate);
        System.out.println("(Customer ID " + customerId + ") Total cost at checkout: " + totalCost);
    }
}

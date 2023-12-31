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
        System.out.println("Total cost at checkout: " + totalCost);
    }

    public void showMyReservations(int customerId, String currentDate) {
        HotelManagement management = new HotelManagement();
        List<String> reservations = management.getMyReservations(customerId, currentDate);
        for (String reservation : reservations) {
            System.out.println(reservation);
        }
    }
}

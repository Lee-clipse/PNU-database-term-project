package org.example;

public class TestApplication {
    public void roomsOccupiedTest() {
        String clientType = LoginService.login("manager", "");
        if (!clientType.equals("ManagerClient"))
            return;
        ManagerClient managerClient = new ManagerClient();
        String currentDate = "2024-01-04";
        System.out.println("Today: " + currentDate);
        managerClient.showOccupiedRooms(currentDate);
    }

    public void housekeepingTest() {
        String clientType = LoginService.login("manager", "");
        if (!clientType.equals("ManagerClient"))
            return;
        ManagerClient managerClient = new ManagerClient();
        managerClient.showHousekeepingAssignments();
    }

    public void roomsAvailableTest() {
        String clientType = LoginService.login("client", "1");
        if (!clientType.equals("CustomerClient"))
            return;
        CustomerClient customerClient = new CustomerClient();
        int customerId = 1;
        String currentDate = "2024-01-04";
        System.out.println("Customer ID: " + customerId);
        System.out.println("Today: " + currentDate);
        customerClient.showRoomsAvailable(currentDate);
    }

    public void costAtCheckoutTest() {
        String clientType = LoginService.login("client", "1");
        if (!clientType.equals("CustomerClient"))
            return;
        CustomerClient customerClient = new CustomerClient();
        int customerId = 1;
        String currentDate = "2024-01-04";
        System.out.println("Customer ID: " + customerId);
        System.out.println("Today: " + currentDate);
        customerClient.showCostAtCheckout(customerId, currentDate);
    }

    public void myReservationTest() {
        String clientType = LoginService.login("client", "1");
        if (!clientType.equals("CustomerClient"))
            return;
        CustomerClient customerClient = new CustomerClient();
        int customerId = 1;
        String currentDate = "2024-01-01";
        System.out.println("Customer ID: " + customerId);
        System.out.println("Today: " + currentDate);
        customerClient.showMyReservations(customerId, currentDate);
    }
}

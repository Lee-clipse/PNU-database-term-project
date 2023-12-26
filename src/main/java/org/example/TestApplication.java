package org.example;

public class TestApplication {
    public void roomsOccupiedTest() {
        String clientType = LoginService.login("manager", "");
        if (!clientType.equals("ManagerClient"))
            return;
        ManagerClient managerClient = new ManagerClient();
        String currentDate = "2024-01-04";
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
        String clientType = LoginService.login("client", "");
        if (!clientType.equals("CustomerClient"))
            return;
        CustomerClient customerClient = new CustomerClient();
        String currentDate = "2024-01-04";
        customerClient.showRoomsAvailable(currentDate);
    }

    public void costAtCheckoutTest() {
        String clientType = LoginService.login("client", "");
        if (!clientType.equals("CustomerClient"))
            return;
        CustomerClient customerClient = new CustomerClient();
        int customerId = 1;
        String currentDate = "2024-01-04";
        customerClient.showCostAtCheckout(customerId, currentDate);
    }

    public void myReservationTest() {
        String clientType = LoginService.login("client", "");
        if (!clientType.equals("CustomerClient"))
            return;
        CustomerClient customerClient = new CustomerClient();
        int customerId = 1;
        String currentDate = "2024-01-01";
        customerClient.showMyReservations(customerId, currentDate);
    }
}

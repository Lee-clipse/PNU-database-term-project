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
}

package org.example;

public class TestApplication {
    public void roomsOccupiedTest() {
        String clientType = LoginService.login("manager", "");
        if (!clientType.equals("ManagerClient"))
            return;
        ManagerClient managerClient = new ManagerClient();
        managerClient.showOccupiedRooms();
    }
}

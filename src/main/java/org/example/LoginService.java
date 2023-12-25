package org.example;

public class LoginService {
    public static String login(String userType, String userId) {
        return userType.equalsIgnoreCase("manager") ? "ManagerClient" : "CustomerClient";
    }
}

package org.example;

public class LoginService {
    public static String login(String userType, String userId) {
        // 실제 인증 로직 대신 간단한 사용자 타입 체크
        return userType.equalsIgnoreCase("manager") ? "ManagerClient" : "CustomerClient";
    }
}
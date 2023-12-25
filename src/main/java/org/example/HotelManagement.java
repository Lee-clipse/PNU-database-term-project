package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HotelManagement {
    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "jin24564";
        return DriverManager.getConnection(url, user, password);
    }

    // 현재 점유된 객실 조회
    public List<String> getOccupiedRooms() {
        String sql = "SELECT r.room_id, r.type, res.customer_id, res.start_date, res.end_date " +
                "FROM room r " +
                "JOIN reservation res ON r.room_id = res.room_id " +
                "WHERE '2024-01-04' BETWEEN res.start_date AND res.end_date " +
                "AND res.status = 'Confirmed'";

        List<String> occupiedRooms = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String roomDetails = "Room ID: " + rs.getInt("room_id") +
                        ", Type: " + rs.getString("type") +
                        ", Customer ID: " + rs.getInt("customer_id") +
                        ", Date: " + rs.getDate("start_date") + " ~ " + rs.getDate("end_date") + "\n";
                occupiedRooms.add(roomDetails);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving occupied rooms.");
            e.printStackTrace();
        }
        return occupiedRooms;
    }
}

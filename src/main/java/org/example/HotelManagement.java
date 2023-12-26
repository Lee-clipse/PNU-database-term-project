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
    public List<String> getOccupiedRooms(String currentDate) {
        String sql = "SELECT r.room_id, r.type, res.customer_id, res.start_date, res.end_date " +
                "FROM room r " +
                "JOIN reservation res ON r.room_id = res.room_id " +
                "WHERE '" + currentDate + "' BETWEEN res.start_date AND res.end_date " +
                "AND res.status = 'Confirmed'";

        List<String> occupiedRooms = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            conn.setAutoCommit(false);

            while (rs.next()) {
                String roomDetails = "Room ID: " + rs.getInt("room_id") +
                        ", Type: " + rs.getString("type") +
                        ", Customer ID: " + rs.getInt("customer_id") +
                        ", Date: " + rs.getDate("start_date") + " ~ " + rs.getDate("end_date");
                occupiedRooms.add(roomDetails);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error retrieving occupied rooms.");
            e.printStackTrace();
        }
        return occupiedRooms;
    }

    // housekeeping 업무 할당 조회
    public List<String> getHousekeepingAssignments() {
        String sql = "SELECT h.housekeeping_id, h.room_id, h.date, h.status " +
                "FROM housekeeping h";

        List<String> housekeepingAssignments = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            conn.setAutoCommit(false);

            while (rs.next()) {
                String assignmentDetails = "Housekeeping ID: " + rs.getInt("housekeeping_id") +
                        ", Room ID: " + rs.getInt("room_id") +
                        ", Date: " + rs.getDate("date") +
                        ", Status: " + rs.getString("status");
                housekeepingAssignments.add(assignmentDetails);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error retrieving housekeeping assignments.");
            e.printStackTrace();
        }
        return housekeepingAssignments;
    }
}

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
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return conn;
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

    // 이용 가능한 객실 조회
    public List<String> getAvailableRooms(String currentDate) {
        String sql = "SELECT r.room_id, r.type, r.cost " +
                "FROM room r " +
                "LEFT JOIN reservation res ON r.room_id = res.room_id " +
                "AND '" + currentDate + "' BETWEEN res.start_date AND res.end_date " +
                "WHERE res.reservation_id IS NULL";

        List<String> availableRooms = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            conn.setAutoCommit(false);

            while (rs.next()) {
                String roomDetails = "Room ID: " + rs.getInt("room_id") +
                        ", Type: " + rs.getString("type") +
                        ", Cost: " + rs.getDouble("cost");
                availableRooms.add(roomDetails);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error retrieving available rooms.");
            e.printStackTrace();
        }
        return availableRooms;
    }

    // 체크아웃 시 고객의 총 비용 계산
    public double calculateCostAtCheckout(int customerId, String currentDate) {
        String sql = "SELECT SUM(r.cost * (res.end_date - res.start_date)) AS total_cost " +
                "FROM reservation res " +
                "JOIN room r ON res.room_id = r.room_id " +
                "WHERE res.customer_id = " + customerId + " AND '" + currentDate + "' >= res.end_date";

        double totalCost = 0.0;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            conn.setAutoCommit(false);

            if (rs.next()) {
                totalCost = rs.getDouble("total_cost");
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error calculating cost at checkout.");
            e.printStackTrace();
        }
        return totalCost;
    }

    // 고객의 미래 예약 목록 조회
    public List<String> getMyReservations(int customerId, String currentDate) {
        String sql = "SELECT res.reservation_id, r.room_id, r.type, res.start_date, res.end_date, res.status " +
                "FROM reservation res " +
                "JOIN room r ON res.room_id = r.room_id " +
                "WHERE res.customer_id = " + customerId + " AND res.start_date >= '" + currentDate + "'";

        List<String> myReservations = new ArrayList<>();

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            conn.setAutoCommit(false);

            while (rs.next()) {
                String reservationDetails = "Reservation ID: " + rs.getInt("reservation_id") +
                        ", Room ID: " + rs.getInt("room_id") +
                        ", Type: " + rs.getString("type") +
                        ", Start Date: " + rs.getDate("start_date") +
                        ", End Date: " + rs.getDate("end_date") +
                        ", Status: " + rs.getString("status");
                myReservations.add(reservationDetails);
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Error retrieving my reservations.");
            e.printStackTrace();
        }
        return myReservations;
    }
}

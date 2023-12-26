package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TestApplication testInstance = new TestApplication();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1. Test Occupied Rooms For Manager");
        System.out.println("2. Test Housekeeping For Manager");
        System.out.println("3. Test Available Rooms For Customer");
        System.out.println("4. Test Cost at Checkout For Customer");
        System.out.println("5. Test My Reservations For Customer");
        System.out.print("Enter your choice (1-5): ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                testInstance.roomsOccupiedTest();
                break;
            case 2:
                testInstance.housekeepingTest();
                break;
            case 3:
                testInstance.roomsAvailableTest();
                break;
            case 4:
                testInstance.costAtCheckoutTest();
                break;
            case 5:
                testInstance.myReservationTest();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }

        scanner.close();
    }
}
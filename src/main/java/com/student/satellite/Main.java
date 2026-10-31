package com.student.satellite;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final SatelliteDAO dao = new SatelliteDAO();

    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("     SATELLITE MANAGEMENT SYSTEM");
        System.out.println("==================================");

        try {
            // Test database connectivity before displaying the menu.
            DBConnection.getConnection().close();
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            System.out.println("Check MySQL is running and update DBConnection.java.");
            return;
        }

        while (true) {
            System.out.println("\n1. Add Satellite");
            System.out.println("2. View All Satellites");
            System.out.println("3. Search Satellite by ID");
            System.out.println("4. Update Satellite Status");
            System.out.println("5. Delete Satellite");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();
            try {
                switch (choice) {
                    case 1 -> addSatellite();
                    case 2 -> viewSatellites();
                    case 3 -> searchSatellite();
                    case 4 -> updateSatellite();
                    case 5 -> deleteSatellite();
                    case 0 -> {
                        System.out.println("Thank you for using the system.");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database operation failed: " + e.getMessage());
            }
        }
    }

    private static void addSatellite() throws SQLException {
        System.out.print("Satellite name: ");
        String name = sc.nextLine().trim();
        System.out.print("Country/Agency: ");
        String country = sc.nextLine().trim();
        System.out.print("Orbit type (LEO/MEO/GEO): ");
        String orbit = sc.nextLine().trim();
        System.out.print("Launch date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();
        System.out.print("Status (Active/Inactive): ");
        String status = sc.nextLine().trim();

        dao.addSatellite(new Satellite(0, name, country, orbit, date, status));
        System.out.println("Satellite added successfully.");
    }

    private static void viewSatellites() throws SQLException {
        List<Satellite> satellites = dao.getAllSatellites();
        if (satellites.isEmpty()) {
            System.out.println("No satellite records found.");
            return;
        }
        System.out.printf("%-5s %-22s %-18s %-10s %-14s %-12s%n",
                "ID", "NAME", "COUNTRY/AGENCY", "ORBIT", "LAUNCH DATE", "STATUS");
        System.out.println("--------------------------------------------------------------------------------");
        for (Satellite s : satellites) {
            System.out.printf("%-5d %-22s %-18s %-10s %-14s %-12s%n",
                s.getId(), s.getName(), s.getCountry(), s.getOrbitType(),
                s.getLaunchDate(), s.getStatus());
        }
    }

    private static void searchSatellite() throws SQLException {
        System.out.print("Enter satellite ID: ");
        Satellite s = dao.findSatellite(readInt());
        if (s == null) {
            System.out.println("Satellite not found.");
        } else {
            System.out.println("ID: " + s.getId() + " | Name: " + s.getName()
                + " | Country: " + s.getCountry() + " | Orbit: " + s.getOrbitType()
                + " | Launch date: " + s.getLaunchDate() + " | Status: " + s.getStatus());
        }
    }

    private static void updateSatellite() throws SQLException {
        System.out.print("Enter satellite ID: ");
        int id = readInt();
        System.out.print("Enter new status (Active/Inactive): ");
        String status = sc.nextLine().trim();
        System.out.println(dao.updateSatellite(id, status)
            ? "Satellite status updated." : "Satellite ID not found.");
    }

    private static void deleteSatellite() throws SQLException {
        System.out.print("Enter satellite ID to delete: ");
        int id = readInt();
        System.out.println(dao.deleteSatellite(id)
            ? "Satellite deleted." : "Satellite ID not found.");
    }

    private static int readInt() {
        while (true) {
            String value = sc.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}

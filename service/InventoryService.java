package service;

import model.Medicine;
import util.FileHandler;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

public class InventoryService {

    private ArrayList<Medicine> inventory;

    public InventoryService() {
        inventory = FileHandler.loadFromFile();
        System.out.println("Loaded Medicines: " + inventory.size());
    }

    public void addMedicine(String name, String batch, double price, int qty,
                            String date, String category, int priority, String supplier) {

        Medicine m = new Medicine(name, batch, price, qty, date, category, priority, supplier);
        inventory.add(m);

        FileHandler.saveToFile(inventory);

        System.out.println("Medicine added successfully.");
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No medicines available.");
            return;
        }

        System.out.println("\n--- Inventory ---");
        for (Medicine m : inventory) {
            System.out.println(m);
        }
    }

    public ArrayList<Medicine> getInventory() {
        return inventory;
    }

    // 🔥 FIXED RISK LOGIC
    public void riskAnalysis() {
        LocalDate today = LocalDate.now();

        System.out.println("\n--- Risk Analysis ---");

        if (inventory.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        boolean found = false;

        for (Medicine m : inventory) {
            long daysLeft = ChronoUnit.DAYS.between(today, m.getExpiryDate());

            if (daysLeft < 0) {
                System.out.println("CRITICAL: " + m.getName() + " expired.");
                found = true;
            } 
            else if (daysLeft <= 30) {
                System.out.println("HIGH RISK: " + m.getName() + " expires in " + daysLeft + " days.");
                found = true;
            } 
            else if (daysLeft <= 90) {
                System.out.println("MEDIUM RISK: " + m.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("All medicines are safe.");
        }
    }

    public void lowStockAlert() {
        System.out.println("\n--- Low Stock Alerts ---");

        if (inventory.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        boolean found = false;

        for (Medicine m : inventory) {
            if (m.getQuantity() < 10) {
                System.out.println("LOW STOCK: " + m.getName() + " | Qty: " + m.getQuantity());
                found = true;
            }
        }

        if (!found) {
            System.out.println("All items are sufficiently stocked.");
        }
    }

    public void searchMedicine(String keyword) {
        System.out.println("\n--- Search Results ---");

        boolean found = false;

        for (Medicine m : inventory) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(m);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching medicine found.");
        }
    }

    // 🔥 UNIQUE FEATURE: RESTOCK RECOMMENDATION SYSTEM
    public void restockSuggestions(HashMap<String, Integer> salesData) {

        System.out.println("\n--- Restock Recommendations ---");

        if (inventory.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        boolean found = false;

        for (Medicine m : inventory) {

            int stock = m.getQuantity();
            int sold = salesData.getOrDefault(m.getName(), 0);
            int priority = m.getPriorityLevel();

            if (stock < 10 && sold > 5) {
                System.out.println("URGENT: " + m.getName() + " (Low stock + High demand)");
                found = true;
            }
            else if (stock < 20 && priority >= 4) {
                System.out.println("IMPORTANT: " + m.getName() + " (High priority)");
                found = true;
            }
            else if (sold > 10) {
                System.out.println("SUGGESTED: " + m.getName() + " (High sales)");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Stock levels are optimal.");
        }
    }
}
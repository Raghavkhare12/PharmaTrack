package service;

import model.Medicine;

import java.util.ArrayList;
import java.util.HashMap;

public class BillingService {

    private HashMap<String, Integer> salesData = new HashMap<>();

    public void processSale(ArrayList<Medicine> inventory, String name, int qty) {

        for (Medicine m : inventory) {

            if (m.getName().equalsIgnoreCase(name)) {

                if (m.getQuantity() >= qty) {

                    double total = qty * m.getPrice();
                    double discount = (qty > 5) ? 0.10 : 0;
                    double finalAmount = total - (total * discount);

                    m.setQuantity(m.getQuantity() - qty);

                    // update sales analytics
                    salesData.put(name, salesData.getOrDefault(name, 0) + qty);

                    System.out.println("\n*** BILL RECEIPT ***");
                    System.out.println("Product: " + m.getName());
                    System.out.println("Quantity: " + qty);
                    System.out.println("Discount: " + (discount * 100) + "%");
                    System.out.println("Final Amount: Rs." + finalAmount);
                    System.out.println("********************");

                    return;
                } else {
                    System.out.println("Not enough stock.");
                    return;
                }
            }
        }

        System.out.println("Medicine not found.");
    }

    public void showSalesAnalytics() {
        System.out.println("\n--- Sales Analytics ---");

        if (salesData.isEmpty()) {
            System.out.println("No sales recorded yet.");
            return;
        }

        for (String key : salesData.keySet()) {
            System.out.println(key + " sold: " + salesData.get(key));
        }
    }

    // 🔥 NEW: Getter for Restock Feature
    public HashMap<String, Integer> getSalesData() {
        return salesData;
    }
}
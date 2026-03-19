package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Medicine {

    private String name;
    private String batch;
    private double price;
    private int quantity;
    private LocalDate expiryDate;

    private String category;
    private int priorityLevel;
    private String supplier;

    public Medicine(String name, String batch, double price, int quantity,
                    String dateStr, String category, int priority, String supplier) {

        this.name = name;
        this.batch = batch;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.priorityLevel = priority;
        this.supplier = supplier;

        try {
            this.expiryDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Using today's date.");
            this.expiryDate = LocalDate.now();
        }
    }

    // Getters
    public String getName() { return name; }
    public String getBatch() { return batch; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getCategory() { return category; }
    public int getPriorityLevel() { return priorityLevel; }
    public String getSupplier() { return supplier; }

    // Setter
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(
            "Product: %-12s | Batch: %-8s | Price: Rs.%-6.2f | Stock: %-4d | Exp: %s | Cat: %-10s | Priority: %d | Supplier: %s",
            name, batch, price, quantity, expiryDate, category, priorityLevel, supplier
        );
    }
}
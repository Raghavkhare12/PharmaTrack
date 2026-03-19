package main;

import service.InventoryService;
import service.BillingService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        InventoryService inventory = new InventoryService();
        BillingService billing = new BillingService();

        while (true) {

            System.out.println("\n=== SMART PHARMACY SYSTEM ===");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Inventory");
            System.out.println("3. Process Sale");
            System.out.println("4. Risk Analysis");
            System.out.println("5. Low Stock Alerts");
            System.out.println("6. Sales Analytics");
            System.out.println("7. Search Medicines");
            System.out.println("8. Restock Recommendations");
            System.out.println("9. Exit");

            System.out.print("Choose option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = input.nextLine();

                    System.out.print("Batch: ");
                    String batch = input.nextLine();

                    System.out.print("Price: ");
                    double price = input.nextDouble();

                    System.out.print("Quantity: ");
                    int qty = input.nextInt();
                    input.nextLine();

                    System.out.print("Expiry (YYYY-MM-DD): ");
                    String date = input.nextLine();

                    System.out.print("Category: ");
                    String category = input.nextLine();

                    System.out.print("Priority (1-5): ");
                    int priority = input.nextInt();
                    input.nextLine();

                    System.out.print("Supplier: ");
                    String supplier = input.nextLine();

                    inventory.addMedicine(name, batch, price, qty, date, category, priority, supplier);
                    break;

                case 2:
                    inventory.showInventory();
                    break;

                case 3:
                    System.out.print("Medicine Name: ");
                    String medName = input.nextLine();

                    System.out.print("Quantity: ");
                    int sellQty = input.nextInt();

                    billing.processSale(inventory.getInventory(), medName, sellQty);
                    break;

                case 4:
                    inventory.riskAnalysis();
                    break;

                case 5:
                    inventory.lowStockAlert();
                    break;

                case 6:
                    billing.showSalesAnalytics();
                    break;

                case 7:
                    System.out.print("Enter keyword: ");
                    String key = input.nextLine();
                    inventory.searchMedicine(key);
                    break;
                
                case 8:
                    inventory.restockSuggestions(billing.getSalesData());
                    break;

                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
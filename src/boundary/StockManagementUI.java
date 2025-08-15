/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import control.StockCTRL;
import entity.Medicine;
import java.util.Scanner;

/**
 *
 * @author TOWJIAWEI
 */
public class StockManagementUI {
    private StockCTRL stockCTRL;
    private Scanner scanner;

    public StockManagementUI(StockCTRL stockCTRL) {
        this.stockCTRL = stockCTRL;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== MEDICINE STOCK MANAGEMENT ===");
            System.out.println("1. View All Medicines");
            System.out.println("2. Add New Medicine");
            System.out.println("3. Update Medicine");
            System.out.println("4. Delete Medicine");
            System.out.println("5. Restock Medicine");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    displayAllMedicines();
                    break;
                case 2:
                    addMedicine();
                    break;
                case 3:
                    updateMedicine();
                    break;
                case 4:
                    deleteMedicine();
                    break;
                case 5:
                    restockMedicine();
                    break;
                case 6:
                    System.out.println("Exiting stock management...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayAllMedicines() {
        System.out.println("\n=== ALL MEDICINES ===");
        System.out.printf("%-8s %-20s %-10s %-10s%n", "ID", "Name", "Price", "Stock");
        System.out.println("----------------------------------------");
        for (Medicine med : stockCTRL.getAllMedicines()) {
            System.out.printf("%-8s %-20s RM%-9.2f %-10d%n", 
                med.getMedicineID(), 
                med.getName(), 
                med.getPrice(), 
                med.getStock());
        }
    }

    private void addMedicine() {
        System.out.println("\n=== ADD NEW MEDICINE ===");
        System.out.print("Enter Medicine ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Medicine Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: RM");
        double price = scanner.nextDouble();
        System.out.print("Enter Initial Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Medicine newMedicine = new Medicine(id, name, price, stock);
        if (stockCTRL.addMedicine(newMedicine)) {
            System.out.println("Medicine added successfully!");
        } else {
            System.out.println("Failed to add medicine. ID might already exist.");
        }
    }

    private void updateMedicine() {
        System.out.println("\n=== UPDATE MEDICINE ===");
        System.out.print("Enter Medicine ID to update: ");
        String id = scanner.nextLine();
        
        Medicine existing = stockCTRL.findMedicine(id);
        if (existing == null) {
            System.out.println("Medicine not found!");
            return;
        }
        
        System.out.print("Enter new Name (leave blank to keep current): ");
        String name = scanner.nextLine();
        System.out.print("Enter new Price (enter 0 to keep current): RM");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        
        if (!name.isEmpty()) {
            existing.setName(name);
        }
        if (price > 0) {
            existing.setPrice(price);
        }
        
        System.out.println("Medicine updated successfully!");
    }

    private void deleteMedicine() {
        System.out.println("\n=== DELETE MEDICINE ===");
        System.out.print("Enter Medicine ID to delete: ");
        String id = scanner.nextLine();
        
        if (stockCTRL.deleteMedicine(id)) {
            System.out.println("Medicine deleted successfully!");
        } else {
            System.out.println("Medicine not found or deletion failed!");
        }
    }

    private void restockMedicine() {
        System.out.println("\n=== RESTOCK MEDICINE ===");
        System.out.print("Enter Medicine ID: ");
        String id = scanner.nextLine();
        
        Medicine medicine = stockCTRL.findMedicine(id);
        if (medicine == null) {
            System.out.println("Medicine not found!");
            return;
        }
        
        System.out.print("Enter quantity to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        medicine.addStock(quantity);
        System.out.println("Stock updated successfully! New stock: " + medicine.getStock());
    }
}
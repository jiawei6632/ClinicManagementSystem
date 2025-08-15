package entity;

import java.util.List;

public class Medicine {
    private String medicineID;
    private String name;
    private double price;
    private int stock;

    public static List<Medicine> DEFAULT_MEDICINES = List.of(
        new Medicine("M001", "Paracetamol", 5.50, 100),
        new Medicine("M002", "Ibuprofen", 8.00, 50),
        new Medicine("M003", "Amoxicillin", 12.00, 30),
        new Medicine("M004", "Cetirizine", 7.50, 80),
        new Medicine("M005", "Loratadine", 9.00, 60),
        new Medicine("M006", "Omeprazole", 15.00, 40),
        new Medicine("M007", "Aspirin", 6.50, 120),
        new Medicine("M008", "Dextromethorphan", 10.00, 45),
        new Medicine("M009", "Chlorpheniramine", 5.00, 90),
        new Medicine("M010", "Vitamin C", 12.50, 200)
    );

    public Medicine(String id, String name, double price, int stock) {
        this.medicineID = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters & Setters
    public String getMedicineID() { return medicineID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }

    public void reduceStock(int quantity) {
        if (stock >= quantity) stock -= quantity;
        else throw new RuntimeException("Not enough stock!");
    }

    public void addStock(int quantity) {
        stock += quantity;
    }
}
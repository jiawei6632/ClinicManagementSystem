package control;

import entity.Medicine;
import java.util.ArrayList;
import java.util.List;

public class StockCTRL {
    private List<Medicine> medicines;

    public StockCTRL() {
        this.medicines = new ArrayList<>(Medicine.DEFAULT_MEDICINES); // Load defaults
    }

    public List<Medicine> getAllMedicines() {
        return medicines;
    }

    public boolean addMedicine(Medicine medicine) {
        if (findMedicine(medicine.getMedicineID()) != null) {
            return false; // Medicine with same ID exists
        }
        return medicines.add(medicine);
    }

    public boolean deleteMedicine(String medicineID) {
        Medicine med = findMedicine(medicineID);
        if (med != null) {
            return medicines.remove(med);
        }
        return false;
    }

    // Check if a medicine is in stock
    public boolean isMedicineAvailable(String medicineID, int quantity) {
        Medicine med = findMedicine(medicineID);
        return med != null && med.getStock() >= quantity;
    }

    // Dispense medicine (reduce stock)
    public void dispenseMedicine(String medicineID, int quantity) {
        Medicine med = findMedicine(medicineID);
        if (med == null) throw new RuntimeException("Medicine not found!");
        med.reduceStock(quantity);
    }

    // Find medicine by ID
    public Medicine findMedicine(String medicineID) {
        return medicines.stream()
            .filter(m -> m.getMedicineID().equals(medicineID))
            .findFirst()
            .orElse(null);
    }
}
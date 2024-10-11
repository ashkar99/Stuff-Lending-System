package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private String itemId;
    private String category;
    private String name;
    private String description;
    private LocalDate creationDate;
    private int costPerDay;
    private Member owner;
    private List<Contract> lendingHistory;
    private boolean isAvailable;

    // Constructor
    public Item(String category, String name, String description, int costPerDay, Member owner) {
        this.itemId = generateItemId();
        this.category = category;
        this.name = name;
        this.description = description;
        this.creationDate = LocalDate.now();
        this.costPerDay = costPerDay;
        this.owner = owner;
        this.lendingHistory = new ArrayList<>();
        this.isAvailable = true;
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public Member getOwner() {
        return owner;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Methods for managing contracts
    public void addContract(Contract contract) {
        lendingHistory.add(contract);
        this.isAvailable = false;
    }

    public void markAsAvailable() {
        this.isAvailable = true;
    }

    private String generateItemId() {
        return "ITEM" + System.nanoTime() % 1000000;
    }
}

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
  private List<Contract> lendingHistory = new ArrayList<>();
  private boolean isAvailable;

  // Constructor
  public Item(String itemId, String category, String name, String description, LocalDate creationDate, int costPerDay,
      Member owner) {
    setItemId(itemId);
    setCategory(category);
    setName(name);
    setDescription(description);
    setCreationDate(creationDate);
    setCostPerDay(costPerDay);
    setOwner(owner);
    markAsAvailable();
  }
  public Item(String category, String name, String description, int costPerDay) {
    
  }

  public String getItemId() {
    return itemId;
  }

  private void setItemId(String itemId) {
    if (itemId == null) {
      this.itemId = "ITEM" + System.nanoTime() % 1000000; // Generate a unique ID by using cuurrent nano Time
    } else {
      this.itemId = itemId;
    }
  }

  public String getCategory() {
    return category;
  }

  private void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  private void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  private void setCreationDate(LocalDate creationDate) {
    if (creationDate == null) {
      this.creationDate = LocalDate.now();
    } else {
      this.creationDate = creationDate;
    }
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  private void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  public Member getOwner() {
    return owner;
  }

  private void setOwner(Member owner) {
    this.owner = owner;
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
}

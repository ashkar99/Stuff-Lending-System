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
    setItemId(itemId);
    setCategory(category);
    setName(name);
    setDescription(description);
    setCreationDate(creationDate);
    setCostPerDay(costPerDay);
    setOwner(owner);
    setAvailable(isAvailable);
    this.lendingHistory = new ArrayList<>();
    
  }
  public Item(String category, String name, String description, int costPerDay, Member owner, LocalDate creationDate, String itemId) {
    setItemId(itemId);
    setCategory(category);
    setName(name);
    setDescription(description);
    setCreationDate(creationDate);
    setCostPerDay(costPerDay);
    setOwner(owner);
    setAvailable(isAvailable);
    this.lendingHistory = new ArrayList<>();

  }
  private void setCreationDate(LocalDate creationDate) {
   this.creationDate = creationDate;
  }
  public void setItemId(String itemId) {
    if (itemId == null){
      itemId = "ITEM" + System.nanoTime() % 1000000;
    }else this.itemId = itemId;
     
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }
  public void setOwner(Member owner) {
    this.owner = owner;
  }
 
  public void setAvailable(boolean isAvailable) {
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

}

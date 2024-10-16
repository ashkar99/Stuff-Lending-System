package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.m;

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
  public Item(String category, String name, String description, int costPerDay, Member owner) {
    setItemId(itemId);
    setCategory(category);
    setName(name);
    setDescription(description);
    setCreationDate(creationDate);
    setCostPerDay(costPerDay);
    setOwner(owner);
    markAsAvailable(); 
    
  }
  public Item(String category, String name, String description, int costPerDay, Member owner, LocalDate creationDate, String itemId) {
    setItemId(itemId);
    setCategory(category);
    setName(name);
    setDescription(description);
    setCreationDate(creationDate);
    setCostPerDay(costPerDay);
    setOwner(owner);
    markAsAvailable();
  }

  private void setCreationDate(LocalDate creationDate) {
   this.creationDate = creationDate;
  }

  private void setItemId(String itemId) {
    if (itemId == null){
      itemId = "ITEM" + System.nanoTime() % 1000000;
    }else this.itemId = itemId;
  }

  private void setCategory(String category) {
    this.category = category;
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setDescription(String description) {
    this.description = description;
  }
  
  private void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }
  private void setOwner(Member owner) {
    this.owner = owner;
  }

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

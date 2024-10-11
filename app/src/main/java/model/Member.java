package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member {
  private String name;
  private String email;
  private String phoneNumber;
  private String password;
  private int credits;
  private LocalDate creationDate;
  private List<Item> itemsOwned;
  private List<Contract> lendingHistory;

  // Constructor
  public Member(String name, String email, String phoneNumber, String password) {
    setName(name);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setPassword(password);
    this.credits = 0; // initial credits set to 0
    this.creationDate = LocalDate.now();
    this.itemsOwned = new ArrayList<>();
    this.lendingHistory = new ArrayList<>();
  }

  private void setPassword(String password) {
    this.password = password;
  }

  // Getters and Setters
  public String getMemberId() {
    return email;
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public int getCredits() {
    return credits;
  }

  public List<Item> getItemsOwned() {
    return itemsOwned;
  }

  public List<Contract> getLendingHistory() {
    return lendingHistory;
  }

  // Methods for managing items
  public void addItem(Item item) {
    itemsOwned.add(item);
    credits += 100; // Add 100 credits for each new item
  }

  public void removeItem(Item item) {
    itemsOwned.remove(item);
  }

  public String getPassword() {
    return password;
  }

}
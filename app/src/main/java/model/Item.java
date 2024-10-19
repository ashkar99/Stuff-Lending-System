package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Item class represents an item that can be rented out by a member.
 * It contains the item's details such as name, category, description, cost per
 * day, and its availability status.
 * The item also maintains a lending history of contracts it has been a part of.
 */
public class Item {

  private String itemId;
  private String category;
  private String name;
  private String description;
  private int creationDate;
  private int costPerDay;
  private Member owner;
  private List<Contract> lendingHistory = new ArrayList<>();
  private boolean isAvailable;

  /**
   * Constructor to create a new Item with the provided details.
   * The item is marked as available by default.
   *
   * @param itemId       The unique identifier for the item. If null, it will be
   *                     auto-generated.
   * @param category     The category of the item (e.g., TOOL, VEHICLE, etc.).
   * @param name         The name of the item.
   * @param description  A description of the item.
   * @param creationDate The date the item was created (current day will be used).
   * @param costPerDay   The cost of renting the item per day.
   * @param owner        The owner of the item.
   *
   */
  public Item(String itemId, String category, String name, String description, int creationDate, int costPerDay,
      Member owner) {
    setItemId(itemId);
    setCategory(category);
    setName(name);
    setDescription(description);
    setCreationDate();
    setCostPerDay(costPerDay);
    setOwner(owner);
    markAsAvailable();
  }
  public Item(String category, String name, String description, int costPerDay) {
    
  }

  /**
   * Gets the item's unique identifier.
   *
   * @return The item ID.
   *
   */
  public String getItemId() {
    return itemId;
  }

  /**
   * Sets the item ID. If the provided ID is null, an ID is auto-generated
   * using the current time in nanoseconds.
   *
   * @param itemId The item ID to set.
   *
   */
  private void setItemId(String itemId) {
    if (itemId == null) {
      this.itemId = "ITEM" + System.nanoTime() % 1000000; // Generate a unique ID using current nano time
    } else {
      this.itemId = itemId;
    }
  }

  /**
   * Gets the category of the item.
   *
   * @return The item's category.
   *
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the category of the item.
   *
   * @param category The category to set.
   *
   */
  private void setCategory(String category) {
    this.category = category;
  }

  /**
   * Gets the name of the item.
   *
   * @return The item's name.
   *
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the item.
   *
   * @param name The name to set.
   *
   */
  private void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the description of the item.
   *
   * @return The item's description.
   *
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the item.
   *
   * @param description The description to set.
   *
   */
  private void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the creation date of the item.
   *
   * @return The creation date as an integer representing the day.
   *
   */
  public int getCreationDate() {
    return creationDate;
  }

  /**
   * Sets the creation date to the current day.
   */
  private void setCreationDate() {
    Time t = new Time(); // Assuming the Time class provides current day
    this.creationDate = t.getCurrentDay();
  }

  /**
   * Gets the cost per day for renting the item.
   *
   * @return The cost per day.
   *
   */
  public int getCostPerDay() {
    return costPerDay;
  }

  /**
   * Sets the cost per day for the item.
   *
   * @param costPerDay The cost per day to set.
   *
   */
  private void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  /**
   * Gets the owner of the item.
   *
   * @return The item owner as a {@link Member}.
   *
   */
  public Member getOwner() {
    return new Member(owner.getName(), owner.getEmail(), owner.getPhoneNumber(), owner.getPassword());
  }

  /**
   * Sets the owner of the item.
   *
   * @param owner The owner to set.
   *
   */
  private void setOwner(Member owner) {
    this.owner = owner;
  }

  /**
   * Checks if the item is available for rent.
   *
   * @return True if the item is available, false otherwise.
   *
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Adds a new contract to the lending history of the item and marks the item as
   * unavailable.
   *
   * @param contract The contract to add to the lending history.
   *
   */
  public void addContract(Contract contract) {
    lendingHistory.add(contract);
    this.isAvailable = false;
  }

  /**
   * Marks the item as available for rent.
   */
  public void markAsAvailable() {
    this.isAvailable = true;
  }
}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Item class represents an item that can be rented out by a member.
 * It contains the item's details such as name, category, description, cost per
 * day, and its availability status.
 * The item also maintains a lending history of contracts it has been a part of.
 */
public class Item extends FatherOfFunction {

  private CategoryEnum category;
  private String name;
  private String description;
  private int costPerDay;
  private Member owner;
  private List<ImmutableContract> lendingHistory = new ArrayList<>();
  private boolean isAvailable;

  /**
   * Constructor to create a new Item with the provided details.
   * The item is marked as available by default.
   *
   * @param category    The category of the item (e.g., TOOL, VEHICLE, etc.).
   * @param name        The name of the item.
   * @param description A description of the item.
   * @param costPerDay  The cost of renting the item per day.
   * @param owner       The owner of the item.
   */
  public Item(CategoryEnum category, String name, String description, int costPerDay, Member owner) {
    setId();
    setCategory(category);
    setName(name);
    setDescription(description);
    setCreationDate();
    setCostPerDay(costPerDay);
    setOwner(owner);
    markAsAvailable();
  }

  /**
   * Get a deep ccopy of the item.
   *
   * @param item deep copy.
   */
  public Item(Item item) {
    this.category = item.getCategory();
    this.name = item.getName();
    this.description = item.getDescription();
    this.costPerDay = item.getCostPerDay();
    this.owner = item.getOwner();
  }

  /**
   * Update item information.
   *
   * @param category    Update category.
   * @param name        Update name.
   * @param description Update description.
   * @param costPerDay  Update cost per day.
   */
  public void updateItem(CategoryEnum category, String name, String description, int costPerDay) {
    setCategory(category);
    setName(name);
    setDescription(description);
    setCostPerDay(costPerDay);
  }

  /**
   * Gets the category of the item.
   *
   * @return The item's category.
   */
  public CategoryEnum getCategory() {
    return category;
  }

  /**
   * Sets the category of the item.
   *
   * @param category The category to set (e.g., TOOL, VEHICLE, etc.).
   */
  private void setCategory(CategoryEnum category) {
    this.category = category;
  }

  /**
   * Gets the name of the item.
   *
   * @return The name of the item.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the item.
   *
   * @param name The name to set for the item.
   */
  private void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the description of the item.
   *
   * @return The description of the item.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the item.
   *
   * @param description The description to set for the item.
   */
  private void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the cost per day for renting the item.
   *
   * @return The cost per day for renting the item.
   */
  public int getCostPerDay() {
    return costPerDay;
  }

  /**
   * Sets the cost per day for renting the item.
   *
   * @param costPerDay The daily rental cost to set.
   */
  private void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  /**
   * Gets a copy of the owner of the item.
   *
   * @return A new {@link Member} object representing the owner of the item.
   */
  public Member getOwner() {
    return new Member(owner.getName(), owner.getEmail(), owner.getPhoneNumber(), owner.getPassword());
  }

  /**
   * Sets the owner of the item.
   *
   * @param owner The owner to set for the item.
   */
  private void setOwner(Member owner) {
    this.owner = owner;
  }

  /**
   * Get the contracts associated with the item.
   *
   * @return A list of contracts.
   */
  public List<ImmutableContract> getContracts() {
    return new ArrayList<>(lendingHistory);
  }

  /**
   * Checks if the item is available for rent based on its current status.
   *
   * @return True if the item is available, false otherwise.
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Adds a new contract to the lending history of the item and marks the item as
   * unavailable for the period of the contract.
   *
   * @param contract The contract to add to the lending history.
   */
  public void addContract(ImmutableContract contract) {
    lendingHistory.add(contract);
  }

  /**
   * Marks the item as available for rent.
   */
  public void markAsAvailable() {
    this.isAvailable = true;
  }

  /**
   * Marks the item as unavailable for rent.
   */
  public void markAsUnavailable() {
    this.isAvailable = false;
  }

  /**
   * Check if the item is available for a given period (startDay to endDay).
   *
   * @param startDay The start day of the desired period.
   * @param endDay   The end day of the desired period.
   * @return True if the item is available for the given period, false otherwise.
   */
  public boolean isAvailableForPeriod(int startDay, int endDay) {
    for (ImmutableContract contract : lendingHistory) {
      // Check if the desired period overlaps with any existing contracts
      if ((startDay <= contract.getEndDay() && endDay >= contract.getStartDay())) {
        markAsUnavailable();
        return false;
      }

    }
    markAsAvailable();
    return true; // The item is available if no contract overlaps
  }
}

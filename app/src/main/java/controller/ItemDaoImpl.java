package controller;

import java.util.List;
import model.Item;
import model.Member;

public class ItemDaoImpl implements ItemDaoInterface {
  private MemberDaoInterface memberDao;

  public ItemDaoImpl(MemberDaoInterface memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void createItem(String memberId, String category, String name, String description, int costPerDay) {
    // Find the member by ID
    Member member = memberDao.getMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }

    // Create the new item and add it to the member's list of items
    Item newItem = new Item(category, name, description, costPerDay);
    member.addItem(newItem);

    // Add 100 credits to the member for creating an item
    member.updateCredits(100);
  }

  @Override
  public void deleteItem(String memberId, String itemId) {
    // Find the member by ID
    Member member = memberDao.getMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }

    // Find the item by ID in the member's list and remove it
    Item item = findItemById(member, itemId);
    if (item != null) {
      member.removeItem(item);
    } else {
      throw new IllegalArgumentException("Item not found!");
    }
  }

  @Override
  public void modifyItem(String memberId, String itemId, String category, String name, String description, int costPerDay) {
    // Find the member by ID
    Member member = memberDao.getMemberById(memberId);
    if (member == null) {
        throw new IllegalArgumentException("Member not found!");
    }

    // Find the item by ID and create a new instance with updated information
    Item item = findItemById(member, itemId);
    if (item != null) {
        // Create new values for the item if not provided, keep the old ones
        String newCategory = (category != null && !category.isEmpty()) ? category : item.getCategory();
        String newName = (name != null && !name.isEmpty()) ? name : item.getName();
        String newDescription = (description != null && !description.isEmpty()) ? description : item.getDescription();
        int newCostPerDay = (costPerDay > 0) ? costPerDay : item.getCostPerDay();

        // Create a new item instance with the updated information
        Item updatedItem = new Item(newCategory, newName, newDescription, newCostPerDay);
        updatedItem.markAsAvailable();  // Preserve the available status

        // Replace the old item in the member's item list
        member.removeItem(item);
        member.addItem(updatedItem);
    } else {
        throw new IllegalArgumentException("Item not found!");
    }
}

  @Override
  public Item viewItem(String memberId, String itemId) {
    // Find the member by ID
    Member member = memberDao.getMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }

    // Return the item information
    Item item = findItemById(member, itemId);
    if (item == null) {
      throw new IllegalArgumentException("Item not found!");
    }
    return item;
  }

  @Override
  public List<Item> getItemsByMember(String memberId) {
    // Find the member by ID
    Member member = memberDao.getMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException("Member not found!");
    }

    // Return the member's items
    return member.getItems();
  }

  // Helper method to find an item by its ID within a member's items
  private Item findItemById(Member member, String itemId) {
    for (Item item : member.getItems()) {
      if (item.getItemId().equals(itemId)) {
        return item;
      }
    }
    return null;
  }
}

package controller;

import java.util.List;
import model.CategoryEnum;
import model.Item;
import model.Member;

/**
 * The ItemDaoImpl class is responsible for performing CRUD operations
 * related to the items in the system. It allows creating, deleting,
 * modifying, viewing, and retrieving items owned by members.
 * This class uses the MemberDaoInterface to interact with member data.
 */
public class ItemDaoImpl implements ItemDaoInterface {
  private MemberDaoInterface memberDao = new MemberDaoImpl();

  /**
   * Constructor for the ItemDaoImpl class.
   */
  public ItemDaoImpl() {
  }

  /**
   * Creates a new item for a specific member and adds it to the member's list of
   * owned items. Additionally, the member receives 100 credits for creating an
   * item.
   *
   * @param memberId    The ID of the member creating the item.
   * @param category    The category of the item.
   * @param name        The name of the item.
   * @param description A description of the item.
   * @param costPerDay  The cost per day to lend the item.
   * @throws IllegalArgumentException If the member with the given ID is not
   *                                  found.
   */
  @Override
  public void createItem(String memberId, CategoryEnum category, String name, String description, int costPerDay) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found!");
      }

      Item newItem = new Item(category, name, description, costPerDay, member);
      member.addItem(newItem);
      member.updateCredits(100);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error creating item: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while creating the item.", e);
    }
  }

  /**
   * Modifies an existing item owned by a member. If a new value is not provided,
   * the old value is retained.
   *
   * @param memberId    The ID of the member who owns the item.
   * @param itemId      The ID of the item to be modified.
   * @param category    The new category of the item (or null to retain the
   *                    current category).
   * @param name        The new name of the item (or null to retain the current
   *                    name).
   * @param description The new description of the item (or null to retain the
   *                    current description).
   * @param costPerDay  The new cost per day to lend the item (or -1 to retain the
   *                    current cost).
   * @throws IllegalArgumentException If the member or item with the given IDs is
   *                                  not found.
   */
  @Override
  public void modifyItem(String memberId, String itemId, CategoryEnum category, String name, String description,
      int costPerDay) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found!");
      }

      Item item = getItemById(member, itemId);
      if (item == null) {
        throw new IllegalArgumentException("Item not found!");
      }

      String newName = (name != null && !name.isEmpty()) ? name : item.getName();
      String newDescription = (description != null && !description.isEmpty()) ? description : item.getDescription();
      CategoryEnum newCategory = (category != null) ? category : item.getCategory();
      int newCostPerDay = (costPerDay > 0) ? costPerDay : item.getCostPerDay();

      // Update item by creating a new instance with the modified fields
      Item updatedItem = new Item(newCategory, newName, newDescription, newCostPerDay, item.getOwner());
      member.removeItem(item);
      member.addItem(updatedItem);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error modifying item: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while modifying the item.", e);
    }
  }

  /**
   * Deletes an existing item owned by a member.
   *
   * @param memberId The ID of the member who owns the item.
   * @param itemId   The ID of the item to be deleted.
   * @throws IllegalArgumentException If the member or item with the given IDs is
   *                                  not found.
   */
  @Override
  public void deleteItem(String memberId, String itemId) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found!");
      }

      Item item = getItemById(member, itemId);
      if (item == null) {
        throw new IllegalArgumentException("Item not found!");
      }

      member.removeItem(item);

    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error deleting item: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException("An unexpected error occurred while deleting the item.", e);
    }
  }

  /**
   * Retrieves the details of a specific item owned by a member.
   *
   * @param memberId The ID of the member who owns the item.
   * @param itemId   The ID of the item to be viewed.
   * @return The Item object with the specified ID.
   * @throws IllegalArgumentException If the member or item with the given IDs is
   *                                  not found.
   */
  @Override
  public Item viewItem(String memberId, String itemId) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found!");
      }

      Item item = getItemById(member, itemId);
      if (item == null) {
        throw new IllegalArgumentException("Item not found!");
      }

      return item;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error viewing item: " + e.getMessage(), e);
    }
  }

  /**
   * Retrieves a list of all items owned by a specific member.
   *
   * @param memberId The ID of the member.
   * @return A list of items owned by the member.
   * @throws IllegalArgumentException If the member with the given ID is not
   *                                  found.
   */
  @Override
  public List<Item> getItemsByMember(String memberId) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException("Member not found!");
      }

      return member.getItems();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error retrieving member's items: " + e.getMessage(), e);
    }
  }

  /**
   * Helper method to find an item by its ID within a member's list of items.
   *
   * @param member The member whose items are being searched.
   * @param itemId The ID of the item to be found.
   * @return The Item object if found, or null if not found.
   */
  private Item getItemById(Member member, String itemId) {
    for (Item item : member.getItems()) {
      if (item.getId().equals(itemId)) {
        return item;
      }
    }
    return null;
  }
}

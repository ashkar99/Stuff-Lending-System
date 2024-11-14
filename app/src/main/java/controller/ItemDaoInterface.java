package controller;

import java.util.List;
import model.CategoryEnum;
import model.Item;
import model.Member;

/**
 * Interface to define operations for managing items owned by members.
 */
public interface ItemDaoInterface {

  /**
   * Creates a new item for a member.
   *
   * @param memberId    The ID of the member who owns the item.
   * @param category    The category of the item (e.g., Tool, Vehicle).
   * @param name        The name of the item.
   * @param description A short description of the item.
   * @param costPerDay  The cost per day to lend the item.
   *
   */
  void createItem();

  /**
   * Modifies the details of an existing item.
   *
   * @param memberId    The ID of the member who owns the item.
   * @param itemId      The ID of the item to modify.
   * @param category    The new category of the item.
   * @param name        The new name of the item.
   * @param description The new description of the item.
   * @param costPerDay  The new cost per day to lend the item.
   *
   */
  void modifyItem();

  /**
   * Deletes an item owned by a member.
   *
   * @param memberId The ID of the member who owns the item.
   * @param itemId   The ID of the item to delete.
   */
  void deleteItem();

  /**
   * Retrieves the details of a specific item.
   *
   * @param memberId The ID of the member who owns the item.
   * @param itemId   The ID of the item to view.
   * @return The item with the specified ID, or null if not found.
   */


  /**
   * Retrieves a list of all items owned by a member.
   *
   * @param memberId The ID of the member whose items to retrieve.
   * @return A list of items owned by the specified member.
   */
  List<Item> getItemsByMember(String memberId);

  /**
   * Get item by id.
   *
   * @param member to get member's item.
   * @param itemId to get the item.
   * @return item by the id.
   */
  public Item getItemById(Member member, String itemId);
}

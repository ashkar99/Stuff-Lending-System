package controller;

import java.util.List;
import model.Item;
import model.Member;

/**
 * Interface to define operations for managing items owned by members.
 */
public interface ItemDaoInterface {

  /**
   * Creates a new item for a member.
   */
  void createItem();

  /**
   * Modifies the details of an existing item.
   */
  void modifyItem();

  /**
   * Deletes an item owned by a member.
   */
  void deleteItem();

  /**
   * View available items.
   */
  void viewAvailableItems();

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

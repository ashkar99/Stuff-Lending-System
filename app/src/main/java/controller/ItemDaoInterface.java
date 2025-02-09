package controller;


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

}

package view;

import java.util.List;
import model.Item;

/**
 * The ItemViewer class provides methods for displaying and managing items,
 * including viewing items owned by a member, viewing available items, and
 * editing, adding, and deleting items.
 */
public class ItemViewer extends BaseViewer {
  private final ContractViewer contractViewer;

  /**
   * Constructs an ItemViewer with the specified MemberDaoInterface.
   */
  public ItemViewer() {
    this.contractViewer = new ContractViewer();
  }

  /**
   * Displays all items owned by the specified member, including their contracts.
   *
   * @param member The member whose items are to be displayed.
   */
  public void viewItems(List<Item> items) {
    for (Item item : items) {
      displayItemInfo(item);
      contractViewer.viewContract(item);
    }
  }

  /**
   * Displays all available items that can be borrowed or viewed.
   */
  public void viewAvailableItems(List<Item> items) {
    items.forEach(this::displayItemInfo);
  }

  /**
   * Prompts the user to edit an existing item's information.
   */
  public String[] editItemInfo() {
    String memberId = promptForInput("Enter member id: ");
    String itemId = promptForInput("Enter item id: ");
    String category = promptForCategory();
    String name = promptForInput("Enter name: ");
    String description = promptForInput("Enter description: ");
    String cost = promptForInput("Enter cost: ");
    String[] item = { memberId, itemId, category, name, description, cost };
    return item;
  }

  /**
   * Prompts the user to add a new item by collecting details such as member ID,
   * category, name, description, and cost.
   */
  public String[] addNewItem() {
    String memberId = promptForInput("Enter member id: ");
    String category = promptForCategory();
    String name = promptForInput("Enter name: ");
    String description = promptForInput("Enter description: ");
    String cost = promptForInput("Enter cost: ");
    String[] item = { memberId, category, name, description, cost };
    return item;
  }

  /**
   * Prompts the user to delete an item by entering the member ID and item ID.
   */
  public String[] deleteItem() {
    String memberId = promptForInput("Enter member id: ");
    String itemId = promptForInput("Enter item id: ");
    String[] item = { memberId, itemId };
    return item;
  }

  /**
   * Displays the information of an item, including its name, description,
   * category, and cost per day.
   *
   * @param item The item whose information is to be displayed.
   */
  private void displayItemInfo(Item item) {
    System.out.println("  Item ID: " + item.getId());
    System.out.println("  Item name: " + item.getName());
    System.out.println("  Description: " + item.getDescription());
    System.out.println("  Category: " + item.getCategory());
    System.out.println("  Cost per day: " + item.getCostPerDay());
    System.out.println("-----------");
  }

  /**
   * Prompts the user to enter a category from a predefined set of options.
   *
   * @return The CategoryEnum selected by the user.
   */
  private String promptForCategory() {
    System.out.print("Category options: TOOL, VEHICLE, GAME, TOY, SPORT, OTHER. Enter one category: ");
    return (input.nextLine().toUpperCase());
  }
}

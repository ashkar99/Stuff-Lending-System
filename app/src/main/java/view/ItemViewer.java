package view;

import controller.FeedbackMessage;
import controller.ItemDaoImpl;
import controller.ItemDaoInterface;
import controller.MemberDaoInterface;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.CategoryEnum;
import model.Item;
import model.Member;

/**
 * Item viewer.
 */
public class ItemViewer {
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private final ContractViewer contractViewer = new ContractViewer();
  private final ItemDaoInterface itemDaoImp;

  public ItemViewer(MemberDaoInterface memberDaoImpl) {
    itemDaoImp = new ItemDaoImpl(memberDaoImpl);
  }

  /**
   * Displays all items owned by a member, including their contracts.
   *
   * @param member The member whose items are being displayed.
   */
  public void viewItems(Member member) {
    List<Item> items = member.getItems();

    if (items.isEmpty()) {
      System.out.println(FeedbackMessage.ERROR_NO_ITEMS_TO_DISPLAY.getMessage());
    } else {
      for (Item item : items) {
        System.out.println("  Item name: " + item.getName());
        System.out.println("  Item Description: " + item.getDescription());
        System.out.println("  Category: " + item.getCategory());
        System.out.println("  Item cost per day: " + item.getCostPerDay());
        System.out.println("-----------");
        contractViewer.viewContract(item);
      }
    }
  }

  /**
   * Displays available items from the given list.
   *
   * @param items The list of items to display.
   */
  public void viewAvailableItems(List<Item> items) {
    if (items.isEmpty()) {
      System.out.println(FeedbackMessage.ERROR_NO_ITEMS_TO_DISPLAY.getMessage());
    } else {
      for (Item item : items) {
        System.out.println("  Item name: " + item.getName());
        System.out.println("  Item Description: " + item.getDescription());
        System.out.println("  Category: " + item.getCategory());
        System.out.println("  Item cost per day: " + item.getCostPerDay());
        System.out.println("-----------");
      }
    }
  }

  /**
   * Allows editing of an existing item's information, such as name, description,
   * category, and cost.
   */
  public void editItemInfo() {
    final String memberId = promptForInput("Enter member id: ");
    final String itemId = promptForInput("Enter item id: ");
    final CategoryEnum category = promptForCategory();
    final String name = promptForInput("Enter name: ");
    final String description = promptForInput("Enter description: ");
    final int cost = promptForInt("Enter cost: ");

    itemDaoImp.modifyItem(memberId, itemId, category, name, description, cost);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_UPDATE.getMessage());
  }

  /**
   * Adds a new item for a member by prompting the user for details such as
   * name, description, category, and cost.
   */
  public void addNewItem() {
    final String memberId = promptForInput("Enter member id: ");
    final CategoryEnum category = promptForCategory();
    final String name = promptForInput("Enter name: ");
    final String description = promptForInput("Enter description: ");
    final int cost = promptForInt("Enter cost: ");

    itemDaoImp.createItem(memberId, category, name, description, cost);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_CREATION.getMessage());
  }

  /**
   * Deletes an item by prompting the user for the member ID and item ID.
   */
  public void deleteItem() {
   String memberId = promptForInput("Enter member id: ");
    String itemId = promptForInput("Enter item id: ");

    itemDaoImp.deleteItem(memberId, itemId);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_DELETION.getMessage());
  }

  /**
   * Prompts the user to enter a category.
   *
   * @return The category entered by the user.
   */
  private CategoryEnum promptForCategory() {
    System.out.print("Category options: TOOL, VEHICLE, GAME, TOY, SPORT, OTHER. Enter one category: ");
    final String categoryString = input.nextLine().toUpperCase();
    return CategoryEnum.valueOf(categoryString);
  }

  /**
   * Prompts the user for input.
   *
   * @param message The prompt message.
   * @return The user's input as a string.
   */
  private String promptForInput(String message) {
    System.out.print(message);
    return input.nextLine();
  }

  /**
   * Prompts the user for an integer input.
   *
   * @param message The prompt message.
   * @return The user's input as an integer.
   */
  private int promptForInt(String message) {
    System.out.print(message);
    return input.nextInt();
  }
}

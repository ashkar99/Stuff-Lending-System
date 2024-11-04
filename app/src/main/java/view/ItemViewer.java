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
 * The ItemViewer class provides methods for displaying and managing items,
 * including viewing items owned by a member, viewing available items, and
 * editing, adding, and deleting items.
 */
public class ItemViewer extends BaseViewer {
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private final ContractViewer contractViewer;
  private final ItemDaoInterface itemDaoImp;
  private final MemberDaoInterface memberDao;

  /**
   * Constructs an ItemViewer with the specified MemberDaoInterface.
   *
   * @param memberDao An instance of MemberDaoInterface for accessing member-related data.
   */
  public ItemViewer(MemberDaoInterface memberDao) {
    this.memberDao = memberDao;
    this.itemDaoImp = new ItemDaoImpl(memberDao);
    this.contractViewer = new ContractViewer(memberDao);
  }

  /**
   * Displays all items owned by the specified member, including their contracts.
   *
   * @param member The member whose items are to be displayed.
   */
  public void viewItems(Member member) {
    List<Item> items = member.getItems();
    if (items.isEmpty()) {
      System.out.println(FeedbackMessage.ERROR_NO_ITEMS_TO_DISPLAY.getMessage());
    } else {
      for (Item item : items) {
        displayItemInfo(item);
        contractViewer.viewContract(item);
      }
    }
  }

  /**
   * Displays all available items that can be borrowed or viewed.
   */
  public void viewAvailableItems() {
    List<Item> items = memberDao.getAvailableItems();
    if (items.isEmpty()) {
      System.out.println(FeedbackMessage.ERROR_NO_ITEMS_TO_DISPLAY.getMessage());
    } else {
      items.forEach(this::displayItemInfo);
    }
  }

  /**
   * Prompts the user to edit an existing item's information.
   */
  public void editItemInfo() {
    String memberId = promptForInput("Enter member id: ");
    String itemId = promptForInput("Enter item id: ");
    CategoryEnum category = promptForCategory();
    String name = promptForInput("Enter name: ");
    String description = promptForInput("Enter description: ");
    int cost = promptForInt("Enter cost: ");

    itemDaoImp.modifyItem(memberId, itemId, category, name, description, cost);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_UPDATE.getMessage());
  }

  /**
   * Prompts the user to add a new item by collecting details such as member ID,
   * category, name, description, and cost.
   */
  public void addNewItem() {
    String memberId = promptForInput("Enter member id: ");
    CategoryEnum category = promptForCategory();
    String name = promptForInput("Enter name: ");
    String description = promptForInput("Enter description: ");
    int cost = promptForInt("Enter cost: ");

    itemDaoImp.createItem(memberId, category, name, description, cost);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_CREATION.getMessage());
  }

  /**
   * Prompts the user to delete an item by entering the member ID and item ID.
   */
  public void deleteItem() {
    String memberId = promptForInput("Enter member id: ");
    String itemId = promptForInput("Enter item id: ");

    itemDaoImp.deleteItem(memberId, itemId);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_DELETION.getMessage());
  }

  /**
   * Displays the information of an item, including its name, description,
   * category, and cost per day.
   *
   * @param item The item whose information is to be displayed.
   */
  private void displayItemInfo(Item item) {
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
  private CategoryEnum promptForCategory() {
    System.out.print("Category options: TOOL, VEHICLE, GAME, TOY, SPORT, OTHER. Enter one category: ");
    return CategoryEnum.valueOf(input.nextLine().toUpperCase());
  }
}

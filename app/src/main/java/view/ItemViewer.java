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

public class ItemViewer {
  private final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private final ContractViewer contractViewer;
  private final ItemDaoInterface itemDaoImp;
  private final MemberDaoInterface memberDao;

  public ItemViewer(MemberDaoInterface memberDao) {
    this.memberDao = memberDao;
    this.itemDaoImp = new ItemDaoImpl(memberDao);
    this.contractViewer = new ContractViewer(memberDao);
  }

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

  public void viewAvailableItems() {
    List<Item> items = memberDao.getAvailableItems();
    if (items.isEmpty()) {
      System.out.println(FeedbackMessage.ERROR_NO_ITEMS_TO_DISPLAY.getMessage());
    } else {
      items.forEach(this::displayItemInfo);
    }
  }

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

  public void addNewItem() {
    String memberId = promptForInput("Enter member id: ");
    CategoryEnum category = promptForCategory();
    String name = promptForInput("Enter name: ");
    String description = promptForInput("Enter description: ");
    int cost = promptForInt("Enter cost: ");

    itemDaoImp.createItem(memberId, category, name, description, cost);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_CREATION.getMessage());
  }

  public void deleteItem() {
    String memberId = promptForInput("Enter member id: ");
    String itemId = promptForInput("Enter item id: ");

    itemDaoImp.deleteItem(memberId, itemId);
    System.out.println(FeedbackMessage.SUCCESS_ITEM_DELETION.getMessage());
  }

  private void displayItemInfo(Item item) {
    System.out.println("  Item name: " + item.getName());
    System.out.println("  Description: " + item.getDescription());
    System.out.println("  Category: " + item.getCategory());
    System.out.println("  Cost per day: " + item.getCostPerDay());
    System.out.println("-----------");
  }

  private CategoryEnum promptForCategory() {
    System.out.print("Category options: TOOL, VEHICLE, GAME, TOY, SPORT, OTHER. Enter one category: ");
    return CategoryEnum.valueOf(input.nextLine().toUpperCase());
  }

  private String promptForInput(String message) {
    System.out.print(message);
    return input.nextLine();
  }

  private int promptForInt(String message) {
    System.out.print(message);
    return input.nextInt();
  }
}

package view;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import controller.ItemDaoImpl;
import controller.ItemDaoInterface;
import model.CategoryEnum;
import model.Item;
import model.Member;

/**
 * Item viewer.
 */
public class ItemViewer {
  private Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private ContractViewer contractViewer = new ContractViewer();
  private ItemDaoInterface itemDaoImp = new ItemDaoImpl();

  public void viewItems(Member member) {
    List<Item> items = member.getItems();
    for (Item item : items) {

      System.out.println("  Item name: " + item.getName());
      System.out.println("  Item Description: " + item.getDescription());
      System.out.println("  Category: " + item.getCategory());
      System.out.println("  Item cost per day: " + item.getCostPerDay());
      System.err.println("-----------");
      contractViewer.viewContract(item);

    }
  }
  public void ViewAvilbaleItems(List<Item> items) {
    for (Item item :items ){
      System.out.println("  Item name: " + item.getName());
      System.out.println("  Item Description: " + item.getDescription());
      System.out.println("  Category: " + item.getCategory());
      System.out.println("  Item cost per day: " + item.getCostPerDay());
      System.err.println("-----------");
    }
    
  }

  public void editIteminfo() {
    System.out.print("Enter member id: ");
    String memberId = input.nextLine();
    System.out.print("Enter item id: ");
    String itemid = input.nextLine();
    System.out.print("Catagory options, TOOL, VEHICLE, GAME, TOY, SPORT, OTHER. Enter one catagory: ");
    String categoryString = input.nextLine().toUpperCase();
    CategoryEnum category = CategoryEnum.valueOf(categoryString);
    System.out.print("Enter name: ");
    String name = input.nextLine();
    System.out.print("Enter description: ");
    String description = input.nextLine();
    System.out.print("Enter cost: ");
    int cost = input.nextInt();
    itemDaoImp.modifyItem(memberId, itemid, category, name, description, cost);

  }

  public void addNewItem() {
    System.out.print("Enter member id: ");
    String memberId = input.nextLine();
    System.out.print("Catagory options, TOOL, VEHICLE, GAME, TOY, SPORT, OTHER. Enter one catagory: ");
    String categoryString = input.nextLine().toUpperCase();
    CategoryEnum category = CategoryEnum.valueOf(categoryString);
    System.out.print("Enter name: ");
    String name = input.nextLine();
    System.out.print("Enter description: ");
    String description = input.nextLine();
    System.out.print("Enter cost: ");
    int cost = input.nextInt();
    itemDaoImp.createItem(memberId, category, name, description, cost);
  }

  public void deleteItem() {
    System.out.print("Enter member id: ");
    String memberId = input.nextLine();
    System.out.print("Enter item id: ");
    String itemid = input.nextLine();
   itemDaoImp.deleteItem(memberId, itemid);
  }


}

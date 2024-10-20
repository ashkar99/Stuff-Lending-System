package view;

import java.util.List;

import model.Item;
import model.Member;


/**
 * Item viewer.
 */
public class ItemViewer {
  private ContractViewer contractViewer = new ContractViewer();

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

}

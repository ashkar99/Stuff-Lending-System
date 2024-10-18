package controller;

import java.util.List;
import model.Item;

public interface ItemDaoInterface {
  void createItem(String memberId, String category, String name, String description, int costPerDay);

  void deleteItem(String memberId, String itemId);

  void modifyItem(String memberId, String itemId, String category, String name, String description, int costPerDay);

  Item viewItem(String memberId, String itemId);

  List<Item> getItemsByMember(String memberId);
}

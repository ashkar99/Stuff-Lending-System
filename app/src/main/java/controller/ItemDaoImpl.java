package controller;

import java.util.List;
import model.CategoryEnum;
import model.Item;
import model.Member;
import view.FeedbackMessage;
import view.ItemViewer;

/**
 * The ItemDaoImpl class is responsible for performing CRUD operations
 * related to the items in the system. It allows creating, deleting,
 * modifying, viewing, and retrieving items owned by members.
 * This class uses the MemberDaoInterface to interact with member data.
 */
public class ItemDaoImpl implements ItemDaoInterface {
  private MemberDaoInterface memberDao;
  private ItemViewer itemViewer = new ItemViewer();

  /**
   * Constructor for the ItemDaoImpl class.
   */
  public ItemDaoImpl(MemberDaoInterface memberDaoImpl) {
    memberDao = memberDaoImpl;
  }

  @Override
  public void modifyItem() {

    String[] itemStrings = itemViewer.editItemInfo();
    Member member = memberDao.getMemberById(itemStrings[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    Item item = getItemById(member, itemStrings[1]);
    if (item == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
    }
    // No need for redundant null checks if name and description are always non-null
    if (itemStrings[3].isBlank() || itemStrings[4].isBlank() || Integer.valueOf(itemStrings[5]) < 0) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }
    String newName = !itemStrings[3].isBlank() ? itemStrings[3] : item.getName();
    String newDescription = !itemStrings[4].isBlank() ? itemStrings[4] : item.getDescription();
    CategoryEnum newCategory = (CategoryEnum.valueOf(itemStrings[2]) != null) ? CategoryEnum.valueOf(itemStrings[2])
        : item.getCategory();
    int newCostPerDay = (Integer.valueOf(itemStrings[5]) > 0) ? Integer.valueOf(itemStrings[5])
        : item.getCostPerDay();

    Item updatedItem = new Item(newCategory, newName, newDescription, newCostPerDay, item.getOwner());
    member.removeItem(item);
    member.addItem(updatedItem);

  }

  @Override
  public void createItem() {

    String[] itemStrings = itemViewer.addNewItem();
    Member member = memberDao.getMemberById(itemStrings[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    // Check if any field is empty or invalid
    if (CategoryEnum.valueOf(itemStrings[1]) == null || itemStrings[2].isBlank() || itemStrings[3].isBlank()
        || Integer.valueOf(itemStrings[4]) < 0) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }

    Item newItem = new Item(CategoryEnum.valueOf(itemStrings[1]), itemStrings[2], itemStrings[3],
        Integer.valueOf(itemStrings[4]), member);
    member.addItem(newItem);
    member.updateCredits(100);

  }

  @Override
  public void deleteItem() {

    String itemStrings[] = itemViewer.deleteItem();
    Member member = memberDao.getMemberById(itemStrings[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    Item item = getItemById(member, itemStrings[1]);
    if (item == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
    }

    member.removeItem(item);

  }

  // @Override
  // public Item viewItem(String memberId, String itemId) {
  // try {
  // String itemStrings [] = itemViewer.
  // Member member = memberDao.getMemberById(memberId);
  // if (member == null) {
  // throw new
  // IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
  // }

  // Item item = getItemById(member, itemId);
  // if (item == null) {
  // throw new
  // IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
  // }

  // return item;
  // } catch (IllegalArgumentException e) {
  // throw new
  // IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage(),
  // e);
  // } catch (Exception e) {
  // throw new
  // RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
  // }
  // }

  @Override
  public List<Item> getItemsByMember(String memberId) {

    Member member = memberDao.getMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
    return member.getItems();

  }

  /**
   * Get item by id.
   *
   * @param member to get member's item.
   * @param itemId to get the item.
   * @return item by the id.
   */
  public Item getItemById(Member member, String itemId) {
    for (Item item : member.getItems()) {
      if (item.getId().equals(itemId)) {
        return item;
      }
    }
    return null;
  }

  @Override
  protected final void finalize() throws Throwable {
    // Empty finalizer to prevent attacks
  }
}

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
  private final MemberDaoInterface memberDao; // Made final to ensure immutability
  private final ItemViewer itemViewer = new ItemViewer();

  /**
   * Constructor for the ItemDaoImpl class.
   */
  public ItemDaoImpl(MemberDaoInterface memberDaoImpl) {
    if (memberDaoImpl == null) {
      throw new IllegalArgumentException("MemberDaoInterface cannot be null.");
    }
    this.memberDao = memberDaoImpl; // Store the reference directly if it is immutable.
  }

  @Override
  public void modifyItem() {

    memberDao.displayMembersWithDetailedItems();
    String[] itemStrings = itemViewer.editItemInfo();
    Member member = memberDao.getMemberById(itemStrings[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    Item item = getItemById(member, itemStrings[1]);
    if (item == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
    }

    String newName = !itemStrings[3].isBlank() ? itemStrings[3] : item.getName();
    String newDescription = !itemStrings[4].isBlank() ? itemStrings[4] : item.getDescription();
    CategoryEnum newCategory;
    try {
      newCategory = !itemStrings[2].isBlank() ? CategoryEnum.valueOf(itemStrings[2]) : item.getCategory();
    } catch (IllegalArgumentException e) {
      newCategory = item.getCategory(); // Default to the current category if the input is invalid
    }
    int newCostPerDay = (!itemStrings[5].isBlank() && Integer.parseInt(itemStrings[5]) > 0)
        ? Integer.parseInt(itemStrings[5])
        : item.getCostPerDay();
    Item updatedItem = new Item(newCategory, newName, newDescription, newCostPerDay, member);
    member.removeItem(item);
    member.addItem(updatedItem);
    itemViewer.displayFeedback(true, FeedbackMessage.SUCCESS_ITEM_UPDATE.getMessage(), null);
  }

  @Override
  public void createItem() {

    memberDao.findbyList();
    String[] itemStrings = itemViewer.addNewItem();
    Member member = memberDao.getMemberById(itemStrings[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    // Check if any field is empty or invalid
    if (itemStrings[1].isBlank() || itemStrings[2].isBlank() || itemStrings[3].isBlank()
        || Integer.parseInt(itemStrings[4]) < 0) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }

    Item newItem = new Item(CategoryEnum.valueOf(itemStrings[1]), itemStrings[2], itemStrings[3],
        Integer.parseInt(itemStrings[4]), member);
    member.addItem(newItem);
    member.updateCredits(100);
    itemViewer.displayFeedback(true, FeedbackMessage.SUCCESS_ITEM_CREATION.getMessage(), null);
  }

  @Override
  public void deleteItem() {

    memberDao.findbyList();
    String[] itemStrings = itemViewer.deleteItem();
    Member member = memberDao.getMemberById(itemStrings[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    Item item = getItemById(member, itemStrings[1]);
    if (item == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
    }

    member.removeItem(item);
    itemViewer.displayFeedback(true, FeedbackMessage.SUCCESS_ITEM_DELETION.getMessage(), null);
  }

  @Override
  public void viewAvailableItems() {
    itemViewer.viewAvailableItems(memberDao.getAvailableItems());
  }

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

package controller;

import java.util.List;
import model.CategoryEnum;
import model.Item;
import model.Member;
import model.SystemManager;
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
  private final SystemManager systemManager = new SystemManager();

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
    String[] itemInfo = itemViewer.editItemInfo();
    Member member = systemManager.getMemberById(itemInfo[0]);
    Item item = systemManager.getItemById(member, itemInfo[1]);

    systemManager.updateItem(member, item, itemInfo);
    itemViewer.displayFeedback(true, FeedbackMessage.SUCCESS_ITEM_UPDATE.getMessage(), null);
  }

  @Override
  public void createItem() {

    memberDao.findbyList();
    String[] itemStrings = itemViewer.addNewItem();
    Member member = systemManager.getMemberById(itemStrings[0]);
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
    Member member = systemManager.getMemberById(itemStrings[0]);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }

    Item item = systemManager.getItemById(member, itemStrings[1]);
    if (item == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
    }

    member.removeItem(item);
    itemViewer.displayFeedback(true, FeedbackMessage.SUCCESS_ITEM_DELETION.getMessage(), null);
  }

  @Override
  public void viewAvailableItems() {
    itemViewer.viewAvailableItems(systemManager.getAvailableItems());
  }

  @Override
  public List<Item> getItemsByMember(String memberId) {

    Member member = systemManager.getMemberById(memberId);
    if (member == null) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
    }
    return member.getItems();
  }

  @Override
  protected final void finalize() throws Throwable {
    // Empty finalizer to prevent attacks
  }
}

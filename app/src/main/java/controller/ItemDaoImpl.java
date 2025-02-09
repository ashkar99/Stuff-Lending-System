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
  public void createItem() {

    memberDao.findbyList();
    String[] itemInfo = itemViewer.addNewItem();
    systemManager.createItem(itemInfo);
    itemViewer.displayFeedback(true, FeedbackMessage.SUCCESS_ITEM_CREATION.getMessage(), null);
  }

  @Override
  public void modifyItem() {

    memberDao.displayMembersWithDetailedItems();
    String[] itemInfo = itemViewer.editItemInfo();

    systemManager.updateItem(itemInfo);
    itemViewer.displayFeedback(true, FeedbackMessage.SUCCESS_ITEM_UPDATE.getMessage(), null);
  }

  @Override
  public void deleteItem() {

    memberDao.findbyList();
    String[] itemInfo = itemViewer.deleteItem();
    systemManager.deleteItem(itemInfo);
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

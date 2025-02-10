package controller;

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
  private MemberDaoInterface memberDao;
  private final ItemViewer itemViewer = new ItemViewer();
  private final SystemManager systemManager;

  /**
   * Constructor for the ItemDaoImpl class.
   */
  public ItemDaoImpl(SystemManager systemManager) {
    this.systemManager = systemManager;
    memberDao = new MemberDaoImpl(systemManager);
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
  protected final void finalize() throws Throwable {
    // Empty finalizer to prevent attacks
  }
}

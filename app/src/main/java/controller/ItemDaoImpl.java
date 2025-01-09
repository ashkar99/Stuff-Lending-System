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
    System.out.println("1");
    if (itemStrings[2].isBlank()){
      CategoryEnum newCategory = null;
    }
    CategoryEnum newCategory = (CategoryEnum.valueOf(itemStrings[2]) != null ) ? CategoryEnum.valueOf(itemStrings[2])
        : item.getCategory();
        System.out.println("11");
    int newCostPerDay = (Integer.valueOf(itemStrings[5]) > 0) ? Integer.valueOf(itemStrings[5])
        : item.getCostPerDay();
    System.out.println("111");
    Item updatedItem = new Item(newCategory, newName, newDescription, newCostPerDay, member);
    System.out.println("1");
    member.removeItem(item);
    System.out.println("sad");
    member.addItem(updatedItem);
    itemViewer.displayFeedback(true,FeedbackMessage.SUCCESS_ITEM_UPDATE.getMessage(),null);

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
    if (CategoryEnum.valueOf(itemStrings[1]) == null || itemStrings[2].isBlank() || itemStrings[3].isBlank()
        || Integer.valueOf(itemStrings[4]) < 0) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
    }

    Item newItem = new Item(CategoryEnum.valueOf(itemStrings[1]), itemStrings[2], itemStrings[3],
        Integer.valueOf(itemStrings[4]), member);
    member.addItem(newItem);
    member.updateCredits(100);
    itemViewer.displayFeedback(true,FeedbackMessage.SUCCESS_ITEM_CREATION.getMessage(),null);

  }

  @Override
  public void deleteItem() {

    memberDao.findbyList();
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
    itemViewer.displayFeedback(true,FeedbackMessage.SUCCESS_ITEM_DELETION.getMessage(),null);

  }

  @Override
  public void viewAvailableItems(){
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

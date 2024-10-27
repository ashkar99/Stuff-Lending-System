package controller;

import java.util.List;
import model.CategoryEnum;
import model.Item;
import model.Member;

/**
 * The ItemDaoImpl class is responsible for performing CRUD operations
 * related to the items in the system. It allows creating, deleting,
 * modifying, viewing, and retrieving items owned by members.
 * This class uses the MemberDaoInterface to interact with member data.
 */
public class ItemDaoImpl implements ItemDaoInterface {
  private MemberDaoInterface memberDao;

  /**
   * Constructor for the ItemDaoImpl class.
   */
  public ItemDaoImpl(MemberDaoInterface memberDaoImpl) {
    memberDao = memberDaoImpl;
  }

  @Override
  public void modifyItem(String memberId, String itemId, CategoryEnum category, String name, String description,
      int costPerDay) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }

      Item item = getItemById(member, itemId);
      if (item == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
      }

      // No need for redundant null checks if name and description are always non-null
      if (name.isBlank() || description.isBlank() || costPerDay < 0) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
      }

      String newName = !name.isBlank() ? name : item.getName();
      String newDescription = !description.isBlank() ? description : item.getDescription();
      CategoryEnum newCategory = (category != null) ? category : item.getCategory();
      int newCostPerDay = (costPerDay > 0) ? costPerDay : item.getCostPerDay();

      Item updatedItem = new Item(newCategory, newName, newDescription, newCostPerDay, item.getOwner());
      member.removeItem(item);
      member.addItem(updatedItem);

      // Item successfully updated, message can be handled in view.
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error modifying item: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public void createItem(String memberId, CategoryEnum category, String name, String description, int costPerDay) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }

      // Check if any field is empty or invalid
      if (category == null || name.isBlank() || description.isBlank() || costPerDay < 0) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_FIELD_EMPTY.getMessage());
      }

      Item newItem = new Item(category, name, description, costPerDay, member);
      member.addItem(newItem);
      member.updateCredits(100);

      // Item successfully created, message can be handled in view.
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error creating item: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public void deleteItem(String memberId, String itemId) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }

      Item item = getItemById(member, itemId);
      if (item == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
      }

      member.removeItem(item);

      // Item successfully deleted, message can be handled in view.
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error deleting item: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public Item viewItem(String memberId, String itemId) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }

      Item item = getItemById(member, itemId);
      if (item == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage());
      }

      return item;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(FeedbackMessage.ERROR_ITEM_NOT_FOUND.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
  }

  @Override
  public List<Item> getItemsByMember(String memberId) {
    try {
      Member member = memberDao.getMemberById(memberId);
      if (member == null) {
        throw new IllegalArgumentException(FeedbackMessage.ERROR_MEMBER_NOT_FOUND.getMessage());
      }
      return member.getItems();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Error retrieving member's items: " + e.getMessage(), e);
    } catch (Exception e) {
      throw new RuntimeException(FeedbackMessage.ERROR_OPERATION_FAILED.getMessage(), e);
    }
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

package controller;

import java.util.List;
import model.Item;
import model.Member;

/**
 * MemberDaoInterface defines the data access methods for managing
 * members in the system. This interface follows the DAO (Data Access Object)
 * design pattern to allow flexibility if the underlying data source changes
 * in the future.
 */
public interface MemberDaoInterface {

  /**
   * Generated members to test, remove in end.
   */
  void generated();

  /**
   * Creates a new member to the system.
   */
  void createMember();

  /**
   * Update the details of an existing member.
   */
  void updateMember();

  /**
   * Deletes a member from the system based on their member ID and password.
   */
  void deleteMember();

  /**
   * Retrieves detailed information for a specific member.
   */
  void showSpecificMemberInfo();

  /**
   * Display member overview.
   */
  void displayMembersOverview();

  /**
   * Display Member with thier items.
   */
  void displayMembersWithDetailedItems();

  /**
   * Find members list.
   */
  void findbyList();

  /**
   * Get avilebale item.
   */
  List<Item> getAvailableItems();
}

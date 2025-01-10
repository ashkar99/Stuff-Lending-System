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
   * Modifies the details of an existing member.
   */
  void modifyMember();

  /**
   * Deletes a member from the system based on their member ID and password.
   */
  void deleteMember();

  /**
   * Checks if the provided email or phone number is unique in the system.
   *
   * @param email       The email to check for uniqueness.
   * @param phoneNumber The phone number to check for uniqueness.
   * @throws IllegalArgumentException if the email or phone number is already in
   *                                  use.
   */
  void checkUnique(String email, String phoneNumber);

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
   * Finds a member by their unique ID.
   *
   * @param memberId The ID of the member to search for.
   * @return The {@link Member} object if found, or null if not found.
   */
  Member getMemberById(String memberId);

  /**
   * Get avilebale item.
   */
  List<Item> getAvailableItems();
}

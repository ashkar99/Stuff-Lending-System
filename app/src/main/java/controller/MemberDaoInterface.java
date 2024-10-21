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

  void generated();

  /**
   * Adds a new member to the system.
   *
   * @param name        The name of the new member.
   * @param email       The email address of the new member.
   * @param phoneNumber The phone number of the new member.
   * @param password    The password for the new member.
   */
  void addMember(String name, String email, String phoneNumber, String password);

  /**
   * Modifies the details of an existing member.
   *
   * @param memberId    The ID of the member to be modified.
   * @param name        The new name for the member (if applicable).
   * @param email       The new email for the member (if applicable).
   * @param phoneNumber The new phone number for the member (if applicable).
   * @param password    The new password for the member (if applicable).
   */
  void modifyMember(String memberId, String name, String email, String phoneNumber, String password);

  /**
   * Deletes a member from the system based on their member ID and password.
   *
   * @param memberId The ID of the member to be deleted.
   * @param password The password of the member, used for verification.
   */
  void deleteMember(String memberId, String password);

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
   *
   * @param memberId The ID of the member to retrieve.
   * @return The {@link Member} object containing the member's details.
   *
   */
  Member showSpecificMemberInfo(String memberId);

  /**
   * Finds a member by their unique ID.
   *
   * @param memberId The ID of the member to search for.
   * @return The {@link Member} object if found, or null if not found.
   */
  Member getMemberById(String memberId);

  /**
   * Retrieves all members in the system.
   *
   * @return A list of {@link Member} objects representing all members.
   */
  List<Member> getMembers();

  /**
   * Get avilebale item.
   */
  List<Item> getAvilbaleItems();
}

package controller;

import view.FeedbackMessage;
import view.Viewer;

/**
 * Menu class contains the main menu.
 */
public class Menu extends view.BaseViewer {
  private MemberDaoInterface memberDao = new MemberDaoImpl();
  private ItemDaoInterface itemDao = new ItemDaoImpl(memberDao);
  private ContractDaoInterface contractDao = new ContractDaoImpl();
  private Viewer viewer = new Viewer();

  public Menu() {
  }

  /**
   * Main menu, providing access to other menus in the system.
   */
  public void mainMenu() {
    boolean running = true;
    while (running) {
      int choice = viewer.mainMenu();

      switch (choice) {
        case 1 -> memberMenu();
        case 2 -> contractMenu();
        case 3 -> itemMenu();
        case 4 -> running = false;
        default -> System.out.println("Invalid option. Please try again.");
      }
    }
  }

  /**
   * Displays the Member Menu.
   */
  private void memberMenu() {
    try {

      boolean running = true;
      while (running) {
        int choice = viewer.memberMenu();

        switch (choice) {
          case 1 -> memberDao.createMember();
          case 2 -> memberDao.updateMember();
          case 3 -> memberDao.deleteMember();
          case 4 -> memberDao.showSpecificMemberInfo();
          case 5 -> memberDao.displayMembersOverview();
          case 6 -> memberDao.displayMembersWithDetailedItems();
          case 7 -> running = false;
          default -> System.out.println(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Displays the Contract Menu.
   */
  private void contractMenu() {
    try {
      boolean running = true;
      while (running) {
        viewer.contractMenu();
        int choice = promptForInt("");
        switch (choice) {
          case 1 -> contractDao.createContract();
          case 2 -> running = false;
          default -> System.out.println(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Displays the Item Menu.
   */
  private void itemMenu() {
    try {
      boolean running = true;
      while (running) {
        viewer.itemMenu();

        int choice = promptForInt("");
        switch (choice) {
          case 1 -> itemDao.createItem();
          case 2 -> itemDao.modifyItem();
          case 3 -> itemDao.deleteItem();
          case 4 -> itemDao.viewAvailableItems();
          case 5 -> running = false;
          default -> System.out.println(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}

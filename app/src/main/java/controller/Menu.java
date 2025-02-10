package controller;

import model.SystemManager;
import view.FeedbackMessage;
import view.MenuOption;
import view.Viewer;

/**
 * Menu class contains the main menu.
 */
public class Menu {
  private SystemManager systemManager = new SystemManager();
  private MemberDaoInterface memberDao = new MemberDaoImpl(systemManager);
  private ItemDaoInterface itemDao = new ItemDaoImpl(systemManager);
  private ContractDaoInterface contractDao = new ContractDaoImpl(systemManager);
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
      try {
        MenuOption option = MenuOption.fromMainMenuChoice(choice);
        switch (option) {
          case MAIN_MEMBER_MENU -> memberMenu();
          case MAIN_CONTRACT_MENU -> contractMenu();
          case MAIN_ITEM_MENU -> itemMenu();
          case MAIN_EXIT -> running = false;
          default -> viewer.displayFeedback(false, null, FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
        }
      } catch (IllegalArgumentException e) {
        viewer.displayFeedback(false, null, e.getMessage());
      }
    }
  }

  /**
   * Displays the Member Menu.
   */
  private void memberMenu() {
    boolean running = true;
    while (running) {
      int choice = viewer.memberMenu();
      try {
        MenuOption option = MenuOption.fromMemberMenuChoice(choice);
        switch (option) {
          case MEMBER_CREATE -> memberDao.createMember();
          case MEMBER_EDIT -> memberDao.updateMember();
          case MEMBER_DELETE -> memberDao.deleteMember();
          case MEMBER_SHOW_INFO -> memberDao.showSpecificMemberInfo();
          case MEMBER_OVERVIEW -> memberDao.displayMembersOverview();
          case MEMBER_WITH_ITEMS -> memberDao.displayMembersWithDetailedItems();
          case MEMBER_BACK -> running = false;
          default -> viewer.displayFeedback(false, null, FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
        }
      } catch (IllegalArgumentException e) {
        viewer.displayFeedback(false, null, e.getMessage());
      }
    }
  }

  /**
   * Displays the Contract Menu.
   */
  private void contractMenu() {
    boolean running = true;
    while (running) {
      viewer.contractMenu();
      int choice = viewer.promptForInt();
      try {
        MenuOption option = MenuOption.fromContractMenuChoice(choice);
        switch (option) {
          case CONTRACT_CREATE -> contractDao.createContract();
          case CONTRACT_BACK -> running = false;
          default -> viewer.displayFeedback(false, null, FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
        }
      } catch (IllegalArgumentException e) {
        viewer.displayFeedback(false, null, e.getMessage());
      }
    }
  }

  /**
   * Displays the Item Menu.
   */
  private void itemMenu() {
    boolean running = true;
    while (running) {
      viewer.itemMenu();
      int choice = viewer.promptForInt();
      try {
        MenuOption option = MenuOption.fromItemMenuChoice(choice);
        switch (option) {
          case ITEM_ADD -> itemDao.createItem();
          case ITEM_EDIT -> itemDao.modifyItem();
          case ITEM_DELETE -> itemDao.deleteItem();
          case ITEM_VIEW_AVAILABLE -> itemDao.viewAvailableItems();
          case ITEM_BACK -> running = false;
          default -> viewer.displayFeedback(false, null, FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
        }
      } catch (IllegalArgumentException e) {
        viewer.displayFeedback(false, null, e.getMessage());
      }
    }
  }
}

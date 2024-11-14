package controller;

import model.MemberRepository;
import view.FeedbackMessage;
import view.ItemViewer;
import view.MemberViewer;
import view.Viewer;

public class Menu extends view.BaseViewer {
  private MemberDaoInterface memberDao = new MemberDaoImpl();
  private MemberViewer memberViewer = new MemberViewer();
  private ItemViewer itemViewer = new ItemViewer();
  private MemberRepository memberRepository = new MemberRepository();
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
      viewer.mainMenu();
      int choice = promptForInt(null);

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
    boolean running = true;
    while (running) {
      int choice = viewer.memberMenu();
      
      switch (choice) {
        case 1 -> memberDao.createMember();
        case 2 -> memberDao.modifyMember();
        case 3 -> memberDao.deleteMember();
        case 4 -> memberViewer.specificMemberFullInfo(memberRepository.getMembers());
        case 5 -> memberViewer.displayMembersOverview(memberRepository.getMembers());
        case 6 -> memberViewer.displayMembersWithDetailedItems(memberRepository.getMembers());
        case 7 -> running = false;
        default -> System.out.println("Invalid option. Please try again.");
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
      int choice = promptForInt("");
      switch (choice) {
        case 1 -> contractDao.createContract();
        case 2 -> running = false;
        default -> System.out.println(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
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

      int choice = promptForInt("");
      switch (choice) {
        case 1 -> itemDao.createItem();
        case 2 -> itemDao.modifyItem();
        case 3 -> itemDao.deleteItem();
        case 4 -> itemViewer.viewAvailableItems(memberDao.getAvailableItems());
        case 5 -> running = false;
        default -> System.out.println("Invalid option. Please try again.");
      }
    }
  }
}

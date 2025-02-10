package view;

/**
 * Enum representing menu options for different system menus.
 */
public enum MenuOption {

  // Main Menu Options
  MAIN_MEMBER_MENU(1),
  MAIN_CONTRACT_MENU(2),
  MAIN_ITEM_MENU(3),
  MAIN_EXIT(4),

  // Member Menu Options
  MEMBER_CREATE(1),
  MEMBER_EDIT(2),
  MEMBER_DELETE(3),
  MEMBER_SHOW_INFO(4),
  MEMBER_OVERVIEW(5),
  MEMBER_WITH_ITEMS(6),
  MEMBER_BACK(7),

  // Contract Menu Options
  CONTRACT_CREATE(1),
  CONTRACT_BACK(2),

  // Item Menu Options
  ITEM_ADD(1),
  ITEM_EDIT(2),
  ITEM_DELETE(3),
  ITEM_VIEW_AVAILABLE(4),
  ITEM_BACK(5);

  private final int choice;

  MenuOption(int choice) {
    this.choice = choice;
  }

  public int getChoice() {
    return choice;
  }

  /**
   * Retrieves the corresponding Main Menu option from an integer choice.
   */
  public static MenuOption fromMainMenuChoice(int choice) {
    return switch (choice) {
      case 1 -> MAIN_MEMBER_MENU;
      case 2 -> MAIN_CONTRACT_MENU;
      case 3 -> MAIN_ITEM_MENU;
      case 4 -> MAIN_EXIT;
      default -> throw new IllegalArgumentException(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
    };
  }

  /**
   * Retrieves the corresponding Member Menu option from an integer choice.
   */
  public static MenuOption fromMemberMenuChoice(int choice) {
    return switch (choice) {
      case 1 -> MEMBER_CREATE;
      case 2 -> MEMBER_EDIT;
      case 3 -> MEMBER_DELETE;
      case 4 -> MEMBER_SHOW_INFO;
      case 5 -> MEMBER_OVERVIEW;
      case 6 -> MEMBER_WITH_ITEMS;
      case 7 -> MEMBER_BACK;
      default -> throw new IllegalArgumentException(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
    };
  }

  /**
   * Retrieves the corresponding Contract Menu option from an integer choice.
   */
  public static MenuOption fromContractMenuChoice(int choice) {
    return switch (choice) {
      case 1 -> CONTRACT_CREATE;
      case 2 -> CONTRACT_BACK;
      default -> throw new IllegalArgumentException(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
    };
  }

  /**
   * Retrieves the corresponding Item Menu option from an integer choice.
   */
  public static MenuOption fromItemMenuChoice(int choice) {
    return switch (choice) {
      case 1 -> ITEM_ADD;
      case 2 -> ITEM_EDIT;
      case 3 -> ITEM_DELETE;
      case 4 -> ITEM_VIEW_AVAILABLE;
      case 5 -> ITEM_BACK;
      default -> throw new IllegalArgumentException(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
    };
  }
}

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
   * Retrieves the corresponding MenuOption from an integer choice.
   *
   * @param choice The numeric choice entered by the user.
   * @return The corresponding MenuOption.
   * @throws IllegalArgumentException If the choice is invalid.
   */
  public static MenuOption fromChoice(int choice) {
    for (MenuOption option : values()) {
      if (option.getChoice() == choice) {
        return option;
      }
    }
    throw new IllegalArgumentException(FeedbackMessage.ERROR_INVALID_INPUT.getMessage());
  }
}

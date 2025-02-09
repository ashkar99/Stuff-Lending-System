package model;

/**
 * CategoryEnum defines the various categories that an item or entity
 * can belong to. This enum can be used to classify objects into
 * predefined categories.
 */
public enum CategoryEnum {
  TOOL, VEHICLE, GAME, TOY, SPORT, OTHER;

  /**
   * Check if the category is valid.
   *
   * @param newCategory new updated category.
   * @param defaultCategory previous category.
   * @return return the new category if vaild, otherwise reutrn the previous one.
   */
  public static CategoryEnum safeCategoryParse(String newCategory, CategoryEnum defaultCategory) {
    try {
      return !newCategory.isBlank() ? CategoryEnum.valueOf(newCategory) : defaultCategory;
    } catch (IllegalArgumentException e) {
      return defaultCategory;
    }
  }
}

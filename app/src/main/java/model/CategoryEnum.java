package model;

/**
 * CategoryEnum defines the various categories that an item or entity
 * can belong to. This enum can be used to classify objects into
 * predefined categories.
 */
public enum CategoryEnum {
  TOOL, VEHICLE, GAME, TOY, SPORT, OTHER;

  public static CategoryEnum safeCategoryParse(String categoryStr, CategoryEnum defaultCategory) {
    try {
      return !categoryStr.isBlank() ? CategoryEnum.valueOf(categoryStr) : defaultCategory;
    } catch (IllegalArgumentException e) {
      return defaultCategory;
    }
  }
}

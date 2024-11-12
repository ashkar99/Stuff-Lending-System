package view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * BaseViewer class provides common utility methods for user input handling.
 * Other viewer classes can extend this class to avoid code duplication.
 */
public abstract class BaseViewer {
  protected final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);

  /**
   * Prompts the user for input and returns the entered string.
   *
   * @param message The prompt message to display to the user.
   * @return The user's input as a string.
   */
  protected String promptForInput(String message) {
    System.out.print(message);
    return input.nextLine();
  }

  /**
   * Prompts the user for an integer input, handling invalid input until a valid
   * integer is provided.
   *
   * @param message The prompt message to display to the user.
   * @return The user's input as an integer.
   */
  protected int promptForInt(String message) {
    System.out.print(message);
    while (!input.hasNextInt()) {
      System.out.print("That's not a valid number. Please enter a number: ");
      input.next();
    }
    int result = input.nextInt();
    input.nextLine(); // Consume newline
    return result;
  }

  /**
   * Prompts the user to press ENTER to return to the menu.
   */
  protected void waitForUserInput() { // Use in other classes too!!!!!!!!!!!!!!!
    promptForInput("Press ENTER to go back to the menu: ");
  }

      /**
     * Utility method to display feedback messages based on success or failure of actions.
     */
    private void displayFeedback(boolean success, String successMsg, String errorMsg) {
      System.out.println(success ? successMsg : errorMsg);
      waitForUserInput();
  }
}

import controller.Menu;


/**
 * Responsible for staring the application.
 */
public class App extends view.BaseViewer{
  static App app = new App();
  

  public App() {
  }

  /**
   * Application starting point.
   *
   * @param args command line arguments.
   *
   */
  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.mainMenu();
  }

  public int promptForInt(String message) {
    System.out.print(message);
    while (!input.hasNextInt()) {
      System.out.print("That's not a valid number. Please enter a number: ");
      input.next();
    }
    int result = input.nextInt();
    input.nextLine(); // Consume newline
    return result;
  }

}

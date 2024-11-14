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

}

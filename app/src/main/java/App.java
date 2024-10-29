import view.Viewer;

/**
 * Responsible for staring the application.
 */
public class App {
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
    Viewer viewer = new Viewer();
    viewer.mainMenu();

  }

}

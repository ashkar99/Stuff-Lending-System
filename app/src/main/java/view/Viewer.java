package view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * The Viewer class represents the main entry point to the Stuff Lending System
 * App.
 * It displays the start screen to the user and allows them to choose to either
 * log in or sign up.
 */
public class Viewer {
  private Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
  private MemberViewer viewer = new MemberViewer();

  /**
   * Displays the start screen of the application with options to login or sign
   * in.
   * It takes user input to determine whether to proceed with login or sign-in.
   */
  public void startScreen() {
    System.out.println("Welcome to Stuff Lending System App");
    System.out.println("Press L to Login");
    System.out.println("Press S to Sign in");
    String choice = input.nextLine().toLowerCase();

    switch (choice) {
      case "l":
        viewer.login();
        break;
      case "s":
        viewer.signIn();
        break;
      default:
        break;
    }
  }

}

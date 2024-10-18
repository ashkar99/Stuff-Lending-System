package view;

import view.*;
import java.util.Scanner;

public class Viewer {
  private Scanner input = new Scanner(System.in);
  private MemberViewer viewer = new MemberViewer();

  public void StartScreen() {
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
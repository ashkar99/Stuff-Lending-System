package view;

import controller.*;
import java.util.Scanner;

public class Register {
  private Scanner input = new Scanner(System.in);

  public Register() {
    System.out.println("Welcome to Stuff Lending System App");
    System.out.println("Press L to Login");
    System.out.println("Press S to Sign in");
    String choice = input.nextLine().toLowerCase();
    switch (choice) {
      case "l":

        break;
      case "s":
        signIn();
        break;
      default:
        break;
    }
  }

  private void signIn() {
    System.out.println("ENTER YOUR NAME:");
    String name = input.nextLine();
    System.out.println("ENTER YOUR EMAIL:");
    String email = input.nextLine();
    System.out.println("ENTER YOUR PASSWORD:");
    String password = input.nextLine();
    System.out.println("ENTER YOUR PHONE NUMBER:");
    int phonNum = input.nextInt();
    controller.MemberCont t = new MemberCont();
    t.createMember(name, email, phonNum, password);
  }
}

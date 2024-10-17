package view;

import controller.*;
import model.Member;

import java.util.Scanner;

public class MemberViewer {
  private Scanner input = new Scanner(System.in);

  public MemberViewer() {

  }

  

  

  public void login() {

    System.out.println("ENTER YOUR EMAIL:");
    String email = input.nextLine();
    System.out.println("ENTER YOUR PASSWORD:");
    String password = input.nextLine();
    Basic.getInstance().logIn(email, password);

    System.out.println("Welcome to Stuff Lending System App");
    System.out.println("Press S to list all member");
    System.out.println("Press d to delete a member");
    System.out.println("Press d to delete a member");
    String choice = input.nextLine().toLowerCase();
    switch (choice) {
      case "s":
        showSimpleAllMembers();
        break;
      case "d":
        deleteMember();
        break;
      case "l":
        specificFullInfo();
        break;
        case "v":
        showVerboseAllMembers();
        break;
      default:
        break;
    }
  }

  private void specificFullInfo() {
    showSimpleAllMembers();
    System.out.println("Enter the email of the selected member");
    String email = input.nextLine();
    Member member = Basic.getInstanceMember().findMemberById(email);
  }

  private void deleteMember() {
    System.out.println("ENTER YOUR EMAIL:");
    String email = input.nextLine();
    System.out.println("ENTER YOUR PASSWORD:");
    String password = input.nextLine();
    Basic.getInstanceMember().deleteMember(email, password);

  }

  public void signIn() {
    System.out.println("ENTER YOUR NAME:");
    String name = input.nextLine();
    System.out.println("ENTER YOUR EMAIL:");
    String email = input.nextLine();
    System.out.println("ENTER YOUR PASSWORD:");
    String password = input.nextLine();
    System.out.println("ENTER YOUR PHONE NUMBER:");
    String phonNum = input.nextLine();

    Basic.getInstance().addMember(name, email, phonNum, password);
  }

  private void showSimpleAllMembers() {
    for (Member member : Basic.getInstance().getMembers()) {
      System.out.println(member.toString());
      System.out.println();
    }

  }

  private void showVerboseAllMembers() {
    for (Member member : Basic.getInstance().getMembers()) {
      System.out.println(member.toString());
      System.out.println(member.getItemsOwned());

    }
  }
}
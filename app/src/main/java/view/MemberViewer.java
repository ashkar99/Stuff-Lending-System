package view;

import controller.*;
import model.Member;

import java.util.List;
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
   // Basic.getMemberInstance().logIn(email, password);

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
    System.out.println("Enter the memberId of the selected member");
    String memberID = input.nextLine();
    Basic.getMemberInstance().showSpecificMemberInfo(memberID);
  }

  private void deleteMember() {
    System.out.println("ENTER YOUR EMAIL:");
    String email = input.nextLine();
    System.out.println("ENTER YOUR PASSWORD:");
    String password = input.nextLine();
    Basic.getMemberInstance().deleteMember(email, password);

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

    Basic.getMemberInstance().addMember(name, email, phonNum, password);
  }

  private void showSimpleAllMembers() {
    List<Member> simplList=  Basic.getMemberInstance().listSimpleMembersInfo();
    for(Member member : simplList){
      System.out.println("----------------------------------------");
      System.out.println(member.getName());
      System.out.println(member.getEmail());
      System.out.println(member.getPhoneNumber());
      System.out.println(member.getPassword());
      System.out.println(member.getCreationDate());
      System.out.println("----------------------------------------");
      
      
      
    }

  }

  private void showVerboseAllMembers() {
    List<Member> verboseList=  Basic.getMemberInstance().listSimpleMembersInfo();
    for(Member member : verboseList){
      System.out.println("----------------------------------------");
      System.out.println(member.getName());
      System.out.println(member.getEmail());
      System.out.println(member.getPhoneNumber());
      System.out.println(member.getPassword());
      System.out.println(member.getCreationDate());
      System.out.println("----------------------------------------");

    }
  }
}
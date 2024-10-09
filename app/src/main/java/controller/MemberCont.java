package controller;
import model.*;
public class MemberCont {

  public MemberCont() {

  }

  public void createMember(String name, String mail, int phoneNum, String password) {
    Member m = new model.Member(name, mail, phoneNum, password);


  }
  public void viewMember(){
    Member m = new Member();
    String l = m.getName();
    System.out.println(l);

  }

}

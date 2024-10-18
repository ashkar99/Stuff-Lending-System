package controller;

public final class Basic {
  private static controller.MemberController memberController;

  private Basic() {

  }

  public static MemberController getInstance() {
    if (memberController == null) {
      memberController = new MemberController();
    }
    return memberController;
  }

}

package controller;

public final class Basic {
  private static controller.MemberDaoImpl memberController;

  private Basic() {

  }

  public static MemberDaoImpl getMemberInstance() {
    if (memberController == null) {
      memberController = new MemberDaoImpl();
    }
    return memberController;
  }

}

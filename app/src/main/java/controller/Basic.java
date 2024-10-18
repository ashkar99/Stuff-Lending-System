package controller;

public final class Basic {
  private static controller.MemberDaoImpl memberController;

  private Basic() {

  }

  public static MemberDaoImpl getInstance() {
    if (memberController == null) {
      memberController = new MemberDaoImpl();
    }
    return memberController;
  }

}

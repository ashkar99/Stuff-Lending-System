package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Basic is a utility class that provides a singleton-like access
 * to the MemberDaoImpl instance. It ensures that only one instance
 * of MemberDaoImpl is created during the application's lifecycle.
 * This class cannot be instantiated.
 */
public final class Basic {
  private static controller.MemberDaoImpl memberController;

  /**
   * Private constructor to prevent instantiation of the class.
   * This class is designed to be used statically.
   */
  private Basic() {
  }

  /**
   * Returns the singleton instance of the MemberDaoImpl.
   * If the instance is null, it will create a new one.
   */
  @SuppressFBWarnings
  public static MemberDaoImpl getMemberInstance() {
    if (memberController == null) {
      memberController = new MemberDaoImpl();
    }
    return memberController;
  }

}

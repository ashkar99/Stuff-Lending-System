
import java.util.List;
import model.Member;
import model.Time;
import view.Viewer;

/**
 * Responsible for staring the application.
 */
public class App {
  static App app = new App();

  public App() {

  }

  /**
   * Application starting point.
   *
   * @param args command line arguments.
   *
   */
  public static void main(String[] args) {
    Viewer viewer = new Viewer();
    viewer.startScreen();
    
  }

  /**
   * Hard-coding some members, items and contracts.
   */
  private void generated() {


    // // Retrieve members for easy reference
    // Member alice = Basic.getInstance().findMemberById("alice@example.com");
    // Member bob = Basic.getInstance().findMemberById("bob@example.com");
    // Member charlie = Basic.getInstance().findMemberById("charlie@example.com");

    // // Add Items to members
    // Basic.getInstance().addItemToMember(alice.getMemberId(), "Ite123", "Hammer",
    // "Steel hammer", 5,"Ite123","");
    // Basic.getInstance().addItemToMember(alice.getMemberId(), "Game", "Board
    // Game", "Monopoly game", 2);
    // Basic.getInstance().addItemToMember(bob.getMemberId(), "Toy", "Toy Car", "Red
    // remote control car", 3);
    // Basic.getInstance().addItemToMember(charlie.getMemberId(), "Sport", "Tennis
    // Racket", "Wilson Pro racket", 4);

    // List initial members and their items
    // System.out.println("Initial Members and Items:");
    // controller.listAllMembersVerbose();

    // Advance time to day 1

    // Create Contracts
    // controller.createContract(alice.getMemberId(), bob.getMemberId(),
    // 'alice.getItemID()', 1, 3); // Bob borrows Alice's Hammer
    // controller.createContract(charlie.getMemberId(), alice.getMemberId(), "Toy
    // Car", 2, 5); // Alice borrows Bob's Toy Car

    
    }

  }



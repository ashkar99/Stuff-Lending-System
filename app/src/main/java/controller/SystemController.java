// package controller;

// import model.*;
// import java.util.ArrayList;
// import java.util.List;

// public class SystemController {
//   private List<Member> members;
//   private List<Item> items;
//   private List<Contract> contracts;

//   // Constructor
//   public SystemController() {
//     this.members = new ArrayList<>();
//     this.items = new ArrayList<>();
//     this.contracts = new ArrayList<>();
//   }

//   // methods LogIn
//   public void logIn(String email, String password) {
//     Member member = findMemberById(email);
//     if (member != null) {
//       checkPassword(member, password);
//     }

//   }


//   // Methods for managing items
//   public void addItemToMember(String memberId, String category, String name, String description, int costPerDay) {
//     Member member = findMemberById(memberId);
//     if (member != null) {
//       Item newItem = new Item(category, name, description, costPerDay, member);
//       member.addItem(newItem);
//       items.add(newItem);
//     }
//   }

//   public void deleteItem(String itemId) {
//     Item item = findItemById(itemId);
//     if (item != null) {
//       item.getOwner().removeItem(item);
//       items.remove(item);
//     }
//   }

//   // Methods for managing contracts
//   public void createContract(String lenderId, String borrowerId, String itemId, int startDay, int endDay) {
//     Member lender = findMemberById(lenderId);
//     Member borrower = findMemberById(borrowerId);
//     Item item = findItemById(itemId);

//     if (lender != null && borrower != null && item != null && item.isAvailable() &&
//         borrower.getCredits() >= item.getCostPerDay() * (endDay - startDay)) {
//       Contract newContract = new Contract(lender, borrower, item, startDay, endDay);
//       contracts.add(newContract);
//       int creditB = borrower.getCredits();
//       creditB -= newContract.getTotalCost();
//       int creditL = lender.getCredits();
//       creditL += newContract.getTotalCost();
//       item.addContract(newContract);
//     }
//   }

//   public void checkPassword(Member member, String password) {
//     if (member.getPassword().equals(password)) {
//       System.out.println(member.getName() + "Inloggad");
//     }

//   }

//   private Item findItemById(String itemId) {
//     for (Item item : items) {
//       if (item.getItemId().equals(itemId))
//         return item;
//     }
//     return null;
//   }

  
// }
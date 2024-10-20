package controller;

import model.Item;
import model.Member;

public class ContractDaoImpl implements ContractDaoInterface {

  @Override
  public void createContract(Member lender, Member borrower, Item item, int startDay, int endDay) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createContract'");
  }

  @Override
  public boolean isEnoughFundsToBorrow(int borrowerFunds, int itemCost) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isEnoughFundsToBorrow'");
  }

  @Override
  public boolean isItemAvailableToLend(Item item) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'isItemAvailable'");
  }
  
}

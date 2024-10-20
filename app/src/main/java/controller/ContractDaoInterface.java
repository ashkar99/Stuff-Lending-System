package controller;

import model.Item;
import model.Member;

public interface ContractDaoInterface {

void createContract(Member lender, Member borrower, Item item, int startDay, int endDay);

boolean isEnoughFundsToBorrow(int borrowerFunds, int itemCost);

boolean isItemAvailableToLend(Item item);
}
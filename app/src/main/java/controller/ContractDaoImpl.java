package controller;

import model.SystemManager;
import view.ContractViewer;
import view.FeedbackMessage;

/**
 * Implementation of ContractDaoInterface to manage the creation and validation
 * of contracts.
 */
public class ContractDaoImpl implements ContractDaoInterface {
  private ContractViewer contractViewer = new ContractViewer();
  private SystemManager systemManager = new SystemManager();

  @Override
  public void createContract() {

    String[] contractInfo = contractViewer.createContract();
    systemManager.createContract(contractInfo);
    contractViewer.displayFeedback(true, FeedbackMessage.SUCCESS_CONTRACT_CREATION.getMessage(), null);
  }
}

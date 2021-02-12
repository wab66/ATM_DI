package step_definitions;

import io.cucumber.java.en.When;
import nicebank.Teller;
import support.TestAccount;
import support.AtmUserInterface;

public class TellerSteps {

    TestAccount account;
    Teller teller;

    public TellerSteps(AtmUserInterface teller, TestAccount account) {
        this.teller = teller;
        this.account = account;
    }

    @When("^I withdraw (\\d+)$")
    public void iWithdraw(int withdrawAmount){
        teller.withdrawFrom(account, withdrawAmount);
    }
}

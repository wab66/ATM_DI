package step_definitions;

//import cucumber.api.Transform;
//import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.en.Then;
import nicebank.CashSlot;
import org.junit.Assert;

//@ScenarioScoped
public class CashSlotSteps {

    double del = 0;
    CashSlot cashSlot;

    //@Inject
    public CashSlotSteps(CashSlot cashSlot) {
        this.cashSlot = cashSlot;
    }

    @Then("^(\\d+) should be dispensed$")
    public void shouldBeDispensed(int dispenseAmount) {
        int actualDispenseAmount = cashSlot.getContents();
        Assert.assertTrue("Incorrect amount (" + dispenseAmount + ") dispensed. Actual amount was: (" +
                actualDispenseAmount + ").", dispenseAmount == actualDispenseAmount);
    }
}

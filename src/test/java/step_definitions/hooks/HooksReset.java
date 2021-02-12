package step_definitions.hooks;

//import cucumber.runtime.java.guice.ScenarioScoped;
//import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.Before;
import nicebank.Account;
import nicebank.TransactionQueue;
import org.javalite.activejdbc.Base;

//@ScenarioScoped
public class HooksReset {
    //@Before(order = 1)
    @Before
    public void reset() {
        if (!Base.hasConnection()) {
            Base.open(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost/bank",
                    "teller", "password");
        }

        Account.deleteAll();
        TransactionQueue.clear();
    }
}

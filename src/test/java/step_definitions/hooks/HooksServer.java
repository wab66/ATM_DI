package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import nicebank.AtmServer;
import nicebank.CashSlot;
import support.TestAccount;

public class HooksServer {

    public static final int PORT = 8887;
    private AtmServer server;
    private TestAccount account;
    private CashSlot cashSlot;

    public HooksServer(TestAccount account, CashSlot cashSlot) {
        this.account = account;
        this.cashSlot = cashSlot;
    }

    @Before
    public void startServer() throws Exception {
        server = new AtmServer( PORT, cashSlot, account);
        server.start();
    }

    @After
    public void afterRunningScenario(Scenario scenario) throws Exception {
        System.out.println("*********** [ServerHooks] > [@After] > [afterRunningScenario] >: Just finished running scenario: "
                + scenario.getStatus());
        server.stop();
    }
}
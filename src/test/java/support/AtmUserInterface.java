package support;

import nicebank.Account;
import nicebank.Teller;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import step_definitions.hooks.HooksServer;

public class AtmUserInterface implements Teller {
    private final EventFiringWebDriver webDriver;

    public AtmUserInterface(MyWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void withdrawFrom(Account account, int amount) {
        webDriver.get("http://localhost:" + HooksServer.PORT);
        webDriver.findElement(By.id("amount"))
                .sendKeys(String.valueOf(amount));
        webDriver.findElement(By.id("withdraw")).click();
    }
}

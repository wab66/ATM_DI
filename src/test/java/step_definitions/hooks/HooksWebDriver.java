package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import support.MyWebDriver;

public class HooksWebDriver {

    private final EventFiringWebDriver webDriver;
    public HooksWebDriver(MyWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @After
    public void finish(Scenario scenario) {
        System.out.println("*********** [WebDriverHooks] > [@After] > [finish]: Take screen shot");
        try {
            TakesScreenshot ts = (TakesScreenshot) webDriver;
            //byte[] screenshot =  helper.getWebDriver().getScreenshotAs(OutputType.BYTES);
            byte[] screenshot =  ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png","Out_WebDriverHooks");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        finally {
            webDriver.close();
        }
    }
}
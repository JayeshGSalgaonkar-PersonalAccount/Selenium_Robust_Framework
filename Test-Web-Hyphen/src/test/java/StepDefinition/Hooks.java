package StepDefinition;

import DriverFactory.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class Hooks {

    WebDriver driver = DriverManager.getDriver();
    Properties properties = new Properties();

    @After
    public void screenShot(Scenario scenario) {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot,"image/png","fail");
                DriverManager.teardown();
        }
    }
}

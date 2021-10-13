package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    static WebDriver driver;
    static Properties properties = new Properties();

    public static WebDriver setup(String browser) {

        if (Objects.equals(browser, "Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.manage().window().fullscreen();

        } else if (Objects.equals(browser, "Firefox") || browser.isEmpty()) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            driver.manage().window().fullscreen();

        } else {
            System.out.println("Undefined browser!");
        }
        return driver;
    }

    public static Properties propertiesFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/config/config.properties");
        properties.load(fileInputStream);
        return properties;
    }

    public static void teardown() {
        driver.quit();
    }

    public static void embedScreenShot(String name) throws IOException {
        SessionId s = ((RemoteWebDriver) driver).getSessionId();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("./Pass_Screenshot/" + "Pass_" + name + s + ".png"));
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("Driver session not passed to After-Hook on failure");
        }
        return driver;
    }

}

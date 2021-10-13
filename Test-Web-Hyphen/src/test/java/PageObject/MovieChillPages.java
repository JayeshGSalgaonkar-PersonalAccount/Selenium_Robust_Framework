package PageObject;

import DriverFactory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;

public class MovieChillPages {

    @FindBy(xpath = "//h2[normalize-space()='Popular Movies (Test CI/CD)']")
    WebElement pageHeader;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchText;

    @FindBy(xpath = "//body/div/div/div[1]/div[2]/div[1]/button[1]")
    WebElement searchClick;

    @FindBy(xpath = "//body//div//button[10]")
    WebElement nextButton;

    public MovieChillPages(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
    }

    public void pageHeader(){
        pageHeader.isDisplayed();
        Assert.assertEquals(pageHeader.getText(), "Popular Movies (Test CI/CD)");
        System.out.println("Page header = " + pageHeader.getText());
    }

    public void pageSearch(){
        searchText.isEnabled();
        searchClick.isEnabled();
        System.out.println("Page search components are Enabled");
    }

    public void nextButton(){
        nextButton.click();
    }
}



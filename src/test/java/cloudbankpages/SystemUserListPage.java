package cloudbankpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class SystemUserListPage {

    WebDriver driver;
    @FindBy(xpath = "//h1[text()='System User List']")
    public WebElement systemUserListTitle;

    public SystemUserListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebElement getUserNameListElement(String username){
        String userNameListLocator = "//tbody/tr/td/a[contains(text(),'%s')]";
        return driver.findElement(By.xpath(String.format(userNameListLocator, username)));
    }

    public void verifyUserExists(String username){
        assertTrue(getUserNameListElement(username).isDisplayed());
    }

}

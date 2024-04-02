package cloudBankPages;

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

    private WebElement getUserNameListLocator(String text){
        String userNameListLocator = "//tbody/tr/td/a[text()='%s']";
        return driver.findElement(By.xpath(String.format(userNameListLocator, text)));
    }


    public SystemUserListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyHeader(){
        assertTrue(systemUserListTitle.isDisplayed());
    }

    public void verifyUserExists(String username){
        assertTrue(getUserNameListLocator(username).isDisplayed());
    }

}

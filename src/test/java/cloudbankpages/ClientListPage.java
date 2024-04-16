package cloudbankpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class ClientListPage {

    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Client List']")
    public WebElement clientListTitle;

    public ClientListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebElement getAccountNumberElement(String accountNumber){
        String accountNumberLocator = "//table[@id='dataTable']//tbody/tr/td[text()='%s']";
        return driver.findElement(By.xpath(String.format(accountNumberLocator, accountNumber)));
    }

    public WebElement getClientNameElementByAccountNumber(String accountNumber){
        String clientNameByAccountNumberLocator = "//tbody/tr/td[text()='%s']/preceding-sibling::td[2]/a";
        return driver.findElement(By.xpath(String.format(clientNameByAccountNumberLocator, accountNumber)));
    }

    public WebElement getClientNameElement(String clientName){
        String clientNameLocator = "//tbody/tr/td/a[contains(text(),'%s')]";
        return driver.findElement(By.xpath(String.format(clientNameLocator, clientName)));
    }

    public void verifyClientExists(String accountNum) {
        assertTrue(getAccountNumberElement(accountNum).isDisplayed());
    }
}

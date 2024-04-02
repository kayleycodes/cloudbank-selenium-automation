package cloudBankPages;

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

    private WebElement getClientNameListLocator(String text){
        String clientNameListLocator = "//table[@id='dataTable']//tbody/tr/td[text()='%s']";
        return driver.findElement(By.xpath(String.format(clientNameListLocator, text)));
    }

    public ClientListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyClientExists(String accountNum) {
        assertTrue(getClientNameListLocator(accountNum).isDisplayed());
    }
}

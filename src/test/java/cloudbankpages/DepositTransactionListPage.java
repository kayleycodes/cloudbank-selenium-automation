package cloudbankpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class DepositTransactionListPage {
    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Deposit Transaction List']")
    public WebElement depositTransactionListTitle;

    public DepositTransactionListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebElement getDepositTransactionInList(String transactionReference){
        String transactionReferenceInListLocator = "//table[@id='dataTable']//tbody//a[text()='%s']";
        return driver.findElement(By.xpath(String.format(transactionReferenceInListLocator, transactionReference)));
    }
    public void verifyDepositTransactionExists(String transactionReference) {
        assertTrue(getDepositTransactionInList(transactionReference).isDisplayed());
    }
}

package cloudbankpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class TransferTransactionListPage {

    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Transfer Transaction List']")
    public WebElement transferTransactionListTitle;

    public TransferTransactionListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebElement getTransferTransactionInList(String transactionReference){
        String transactionReferenceInListLocator = "//table[@id='dataTable']//tbody//a[text()='%s']";
        return driver.findElement(By.xpath(String.format(transactionReferenceInListLocator, transactionReference)));
    }
    public void verifyTransferTransactionExists(String transactionReference) {
        assertTrue(getTransferTransactionInList(transactionReference).isDisplayed());
    }
}

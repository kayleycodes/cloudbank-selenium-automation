package cloudbankpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class WithdrawTransactionListPage {
    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Withdraw Transaction List']")
    public WebElement withdrawTransactionListTitle;

    public WithdrawTransactionListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getWithdrawTransactionInList(String transactionReference){
        String trxRefInListLocator = "//table[@id='dataTable']//tbody//a[text()='%s']";
        return driver.findElement(By.xpath(String.format(trxRefInListLocator, transactionReference)));
    }
    public void verifyWithdrawTransactionExists(String transactionReference) {
        assertTrue(getWithdrawTransactionInList(transactionReference).isDisplayed());
    }
}

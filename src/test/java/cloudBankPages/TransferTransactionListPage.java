package cloudBankPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class TransferTransactionListPage {
    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Transfer Transaction List']")
    public WebElement transferTrxListTitle;

    private WebElement getTransferTrxInList(String text){
        String trxRefInListLocator = "//table[@id='dataTable']//tbody//a[text()='%s']";
        return driver.findElement(By.xpath(String.format(trxRefInListLocator, text)));
    }
    public TransferTransactionListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyTransferTrxExists(String trxRef) {
        assertTrue(getTransferTrxInList(trxRef).isDisplayed());
    }
}

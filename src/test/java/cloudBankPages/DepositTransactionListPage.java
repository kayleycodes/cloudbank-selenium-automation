package cloudBankPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class DepositTransactionListPage {
    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Deposit Transaction List']")
    public WebElement depositTrxListTitle;

    public WebElement getDepositTrxInList(String text){
        String trxRefInListLocator = "//table[@id='dataTable']//tbody//a[text()='%s']";
        return driver.findElement(By.xpath(String.format(trxRefInListLocator, text)));
    }
    public DepositTransactionListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void verifyDepositTrxExists(String trxRef) {
        assertTrue(getDepositTrxInList(trxRef).isDisplayed());
    }
}

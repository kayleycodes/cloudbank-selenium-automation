package cloudBankPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class WithdrawTransactionListPage {
    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Withdraw Transaction List']")
    public WebElement withdrawTrxListTitle;

    private WebElement getWithdrawTrxInList(String text){
        String trxRefInListLocator = "//table[@id='dataTable']//tbody//a[text()='%s']";
        return driver.findElement(By.xpath(String.format(trxRefInListLocator, text)));
    }
    public WithdrawTransactionListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyWithdrawTrxExists(String trxRef) {
        assertTrue(getWithdrawTrxInList(trxRef).isDisplayed());
    }
}

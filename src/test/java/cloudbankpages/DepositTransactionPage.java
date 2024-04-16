package cloudbankpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class DepositTransactionPage {

    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Deposit Transaction']")
    public WebElement depositTransactionTitle;
    @FindBy(id = "id_trx_date")
    public WebElement transactionDateTxt;
    @FindBy(id = "id_trx_ref")
    public WebElement transactionReferenceTxt;
    @FindBy(id = "id_status")
    public WebElement statusTxt;
    @FindBy(id = "id_client")
    public WebElement clientDrpDown;
    @FindBy(id = "id_deposit_amt")
    public WebElement depositAmountTxt;
    @FindBy(id = "id_curr")
    public WebElement currencyTxt;
    @FindBy(xpath = "//button/span[text()='Confirm']")
    public WebElement confirmBtn;
    public String trsansactionReference;

    public DepositTransactionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutForm(String client, String depositAmt){
 //       clientDrpDown.click();
        Select clientDropdown = new Select(clientDrpDown);
        clientDropdown.selectByVisibleText(client);
        depositAmountTxt.sendKeys(depositAmt);
        trsansactionReference = transactionReferenceTxt.getAttribute("value");
        confirmBtn.click();
    }

    public void verifyDepositTrx(String trxDate, String trxRef, String status, String client, String depositAmount, String currency){
        SoftAssert softAssert = new SoftAssert();
        Select clientTxt = new Select(clientDrpDown);

        softAssert.assertTrue(transactionDateTxt.getAttribute("value").equals(trxDate),
                "Transaction Date does not match");
        softAssert.assertTrue(transactionReferenceTxt.getAttribute("value").equals(trxRef),
                "Transaction Reference does not match");
        softAssert.assertTrue(statusTxt.getAttribute("value").equals(status),
                "Status does not match");
        softAssert.assertTrue(clientTxt.getFirstSelectedOption().getText().equals(client),
                "Client does not match");
        softAssert.assertTrue(depositAmountTxt.getAttribute("value").equals(depositAmount),
                "Deposit Amount does not match");
        softAssert.assertTrue(currencyTxt.getAttribute("value").equals(currency),
                "Currency does not match");

        softAssert.assertAll();
    }
}

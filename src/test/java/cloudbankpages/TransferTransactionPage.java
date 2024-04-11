package cloudbankpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class TransferTransactionPage {

    WebDriver driver;
    @FindBy(xpath = "//h1[text()='Transfer Transaction']")
    public WebElement transferTransactionTitle;
    @FindBy(id = "id_trx_date")
    public WebElement transactionDateTxt;
    @FindBy(id = "id_trx_ref")
    public WebElement transactionReferenceTxt;
    @FindBy(id = "id_status")
    public WebElement statusTxt;
    @FindBy(id = "id_from_client")
    public WebElement fromClientdrpDown;
    @FindBy(id = "id_to_client")
    public WebElement toClientdrpDown;
    @FindBy(id = "id_transfer_amt")
    public WebElement transferAmountTxt;
    @FindBy(id = "id_curr")
    public WebElement currencyTxt;
    @FindBy(xpath = "//button/span[text()='Confirm']")
    public WebElement confirmBtn;

    public String transactionReference;

    public TransferTransactionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutForm(String fromClient, String toClient, String transferAmt){
        fromClientdrpDown.click();
        Select fromClientDropdown = new Select(fromClientdrpDown);
        fromClientDropdown.selectByVisibleText(fromClient);
        toClientdrpDown.click();
        Select toClientDropdown = new Select(toClientdrpDown);
        toClientDropdown.selectByVisibleText(toClient);
        transferAmountTxt.sendKeys(transferAmt);
        transactionReference = transactionReferenceTxt.getAttribute("value");
        confirmBtn.click();
    }
    public void verifyTransferTransaction(String trxDate, String trxRef, String status, String fromClient, String toClient, String transferAmount, String currency){

        SoftAssert softAssert = new SoftAssert();
        Select fromClientTxt = new Select(fromClientdrpDown);
        Select toClientTxt = new Select(toClientdrpDown);

        softAssert.assertTrue(transactionDateTxt.getAttribute("value").equals(trxDate),
                "Transaction Date does not match");
        softAssert.assertTrue(transactionReferenceTxt.getAttribute("value").equals(trxRef),
                "Transaction Reference does not match");
        softAssert.assertTrue(statusTxt.getAttribute("value").equals(status),
                "Status does not match");
        softAssert.assertTrue(fromClientTxt.getFirstSelectedOption().getText().equals(fromClient),
                "From client does not match");
        softAssert.assertTrue(toClientTxt.getFirstSelectedOption().getText().equals(toClient),
                "To client does not match");
        softAssert.assertTrue(transferAmountTxt.getAttribute("value").equals(transferAmount),
                "Transfer Amount does not match");
        softAssert.assertTrue(currencyTxt.getAttribute("value").equals(currency),
                "Currency does not match");

        softAssert.assertAll();
    }

}

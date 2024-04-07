package cloudBankPages;

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
    public WebElement trxDateTxt;
    @FindBy(id = "id_trx_ref")
    public WebElement trxRefTxt;
    @FindBy(id = "id_status")
    public WebElement statusTxt;
    @FindBy(id = "id_from_client")
    public WebElement fromClientdrpDown;
    @FindBy(id = "id_to_client")
    public WebElement toClientdrpDown;
    @FindBy(id = "id_transfer_amt")
    public WebElement transferAmtTxt;
    @FindBy(id = "id_curr")
    public WebElement currencyTxt;
    @FindBy(xpath = "//button/span[text()='Confirm']")
    public WebElement confirmBtn;
    @FindBy(xpath = "//button/span[text()='Confirm and add another']")
    public WebElement confirmAndAddAnotherBtn;

    public String trxRef;

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
        transferAmtTxt.sendKeys(transferAmt);
        trxRef = trxRefTxt.getAttribute("value");
        confirmBtn.click();
    }
    public void verifyTransferTrx(String trxDate, String trxRef, String status, String fromClient, String toClient, String transferAmount, String currency){

        SoftAssert softAssert = new SoftAssert();
        Select fromClientTxt = new Select(fromClientdrpDown);
        Select toClientTxt = new Select(toClientdrpDown);

        softAssert.assertTrue(trxDateTxt.getAttribute("value").equals(trxDate),
                "Transaction Date does not match");
        softAssert.assertTrue(trxRefTxt.getAttribute("value").equals(trxRef),
                "Transaction Reference does not match");
        softAssert.assertTrue(statusTxt.getAttribute("value").equals(status),
                "Status does not match");
        softAssert.assertTrue(fromClientTxt.getFirstSelectedOption().getText().equals(fromClient),
                "From client does not match");
        softAssert.assertTrue(toClientTxt.getFirstSelectedOption().getText().equals(toClient),
                "To client does not match");
        softAssert.assertTrue(transferAmtTxt.getAttribute("value").equals(transferAmount),
                "Transfer Amount does not match");
        softAssert.assertTrue(currencyTxt.getAttribute("value").equals(currency),
                "Currency does not match");

        softAssert.assertAll();
    }

}

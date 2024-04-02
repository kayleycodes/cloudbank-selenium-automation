package cloudBankPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
}

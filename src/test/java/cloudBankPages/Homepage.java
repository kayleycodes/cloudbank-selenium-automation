package cloudBankPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Homepage {

    WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Welcome')]")
    public WebElement welcomeUserTxt;
    @FindBy(xpath = "//span[text()='System Users']")
    public WebElement systemUserBtn;
    @FindBy(xpath = "//div[@id='collapseUsers']/div/a[text()='New System User']")
    public WebElement newSystemUserBtn;
    @FindBy(xpath = "//div[@id='collapseUsers']/div/a[text()='System User List']")
    public WebElement systemUserListBtn;
    @FindBy(xpath = "//span[text()='Clients']")
    public WebElement clientsBtn;
    @FindBy(xpath = "//div[@id='collapseClients']/div/a[text()='New Client']")
    public WebElement newClientBtn;
    @FindBy(xpath = "//div[@id='collapseClients']/div/a[text()='Client List']")
    public WebElement clientListBtn;
    @FindBy(xpath = "//span[text()='Deposit']")
    public WebElement depositBtn;
    @FindBy(xpath = "//div[@id='collapseDeposit']/div/a[text()='Deposit Transaction List']")
    public WebElement depositTrxListBtn;
    @FindBy(xpath = "//div[@id='collapseDeposit']/div/a[text()='New Deposit']")
    public WebElement newDepositBtn;
    @FindBy(xpath = "//span[text()='Withdraw']")
    public WebElement withdrawBtn;
    @FindBy(xpath = "//div[@id='collapseWithdraw']/div/a[text()='Withdraw Transaction List']")
    public WebElement withdrawTrxListBtn;
    @FindBy(xpath = "//div[@id='collapseWithdraw']/div/a[text()='New Withdraw']")
    public WebElement newWithdrawBtn;
    @FindBy(xpath = "//span[text()='Transfer']")
    public WebElement transferBtn;
    @FindBy(xpath = "//div[@id='collapseTransfer']/div/a[text()='Transfer Transaction List']")
    public WebElement transferTrxListBtn;
    @FindBy(xpath = "//div[@id='collapseTransfer']/div/a[text()='New Transfer']")
    public WebElement newTransferBtn;

    public Homepage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}

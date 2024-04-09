package cloudBankPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateClientPage {

    WebDriver driver;

    @FindBy(xpath = "//h1[text()='Create Client']")
    public WebElement createClientTitle;

    @FindBy(xpath = "//h1[text()='Update Client']")
    public WebElement updateClientTitle;
    @FindBy(id = "id_fname")
    public WebElement firstNameTxt;

    @FindBy(id = "id_lname")
    public WebElement lastNameTxt;
    @FindBy(id = "id_addr")
    public WebElement addressTxt;
    @FindBy(id = "id_acct_num")
    public WebElement accountNumTxt;
    @FindBy(id = "id_email_addr")
    public WebElement emailAddTxt;
    @FindBy(id = "id_mobile_num")
    public WebElement mobileNumTxt;
    @FindBy(xpath = "//button/span[text()='Confirm']")
    public WebElement confirmBtn;
    @FindBy(xpath = "//a/span[text()='Delete']")
    public WebElement deleteBtn;
    @FindBy(xpath = "//button/span[text()='Confirm and add another']")
    public WebElement confirmAndAddAnotherBtn;

    public String accountNum;

    public CreateClientPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutForm(String firstName, String lastName, String address, String mobile, String eAdd){
        firstNameTxt.sendKeys(firstName);
        lastNameTxt.sendKeys(lastName);
        addressTxt.sendKeys(address);
        mobileNumTxt.sendKeys(mobile);
        emailAddTxt.sendKeys(eAdd);
        accountNum = accountNumTxt.getAttribute("value");

        confirmBtn.click();
    }

    public void clearAllTextFieldValue(){
        firstNameTxt.clear();
        lastNameTxt.clear();
        addressTxt.clear();
        mobileNumTxt.clear();
        emailAddTxt.clear();
    }
}

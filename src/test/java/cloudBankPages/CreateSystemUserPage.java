package cloudBankPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateSystemUserPage {

    WebDriver driver;

    @FindBy(xpath = "//h1[text()='Create System User']")
    public WebElement createUserTitle;
    @FindBy(xpath = "//h1[text()='Update System User']")
    public WebElement updateUserTitle;
    @FindBy(id = "id_username")
    public WebElement newUserNameTxt;
    @FindBy(id = "id_password1")
    public WebElement newPassTxt;
    @FindBy(id = "id_password2")
    public WebElement confirmNewPasswordTxt;
    @FindBy(xpath = "//button/span[text()='Confirm']")
    public WebElement confirmBtn;
    @FindBy(xpath = "//a/span[text()='Delete']")
    public WebElement deleteBtn;
    @FindBy(xpath = "//button/span[text()='Confirm and add another']")
    public WebElement confirmAndAddAnotherBtn;

    public CreateSystemUserPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutForm(String newUsername, String newPassword){
        newUserNameTxt.clear();
        newUserNameTxt.sendKeys(newUsername);
        newPassTxt.sendKeys(newPassword);
        confirmNewPasswordTxt.sendKeys(newPassword);
        confirmBtn.click();
    }


}

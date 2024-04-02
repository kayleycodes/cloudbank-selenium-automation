package cloudBankPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class CreateSystemUserPage {

    WebDriver driver;
//[text()='Create System User']
    @FindBy(xpath = "//h1")
    public WebElement createUserTitle;
    @FindBy(id = "id_username")
    public WebElement newUserNameTxt;
    @FindBy(id = "id_password1")
    public WebElement newPassTxt;
    @FindBy(id = "id_password2")
    public WebElement confirmNewPasswordTxt;
    @FindBy(xpath = "//button/span[text()='Confirm']")
    public WebElement confirmBtn;
    @FindBy(xpath = "//button/span[text()='Confirm and add another']")
    public WebElement confirmAndAddAnotherBtn;

    public CreateSystemUserPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createNewUser(String newUsername, String newPassword){
        newUserNameTxt.sendKeys(newUsername);
        newPassTxt.sendKeys(newPassword);
        confirmNewPasswordTxt.sendKeys(newPassword);
        confirmBtn.click();
    }

}

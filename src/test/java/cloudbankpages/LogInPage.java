package cloudbankpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertEquals;


public class LogInPage {

    WebDriver driver;
//    @FindBy(xpath = "//h1")
//    WebElement header;
//    @FindBy(id = "exampleInputUsername")
//    public WebElement userNameTxt;
//    @FindBy(id = "exampleInputPassword")
//    WebElement passwordTxt;
//    @FindBy(xpath = "//form[@id='login-form']/input[@value='Log in']")
//    WebElement logInBtn;
    By header = By.xpath("//h1[text()='Welcome to Cloud Bank']");
    By userNameTxt = By.id("exampleInputUsername");
    By passwordTxt = By.id("exampleInputPassword");
    By logInBtn = By.xpath("//form[@id='login-form']/input[@value='Log in']");

    public LogInPage(WebDriver driver){
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public void verifyHeader(){
        assertEquals("Welcome to Cloud Bank", driver.findElement(header).getText());
    }
    public void logIn(String username, String password){
        driver.findElement(userNameTxt).sendKeys(username);
        driver.findElement(passwordTxt).sendKeys(password);
        driver.findElement(logInBtn).click();
    }


}

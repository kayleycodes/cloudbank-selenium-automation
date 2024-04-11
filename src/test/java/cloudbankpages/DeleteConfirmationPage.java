package cloudbankpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteConfirmationPage {

    WebDriver driver;

    @FindBy(xpath = "//h1[text()='Delete Confirmation']")
    public WebElement deleteConfirmationTitle;
    @FindBy(xpath = "//button/span[text()='Yes, I’m sure']")
    public WebElement yesDeleteBtn;

    public DeleteConfirmationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

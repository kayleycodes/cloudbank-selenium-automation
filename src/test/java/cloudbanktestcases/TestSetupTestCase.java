package cloudbanktestcases;

import cloudbankpages.LogInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSetupTestCase {

    WebDriver driver;

    void chromeDriverSetUp(){
        //chrome driver setup

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    void logIn(){
        //login
        String loginUsername = "admin";
        String loginPassword = "password";

        LogInPage logInPage = new LogInPage(driver);
        logInPage.verifyHeader();
        logInPage.logIn(loginUsername, loginPassword);
    }


}

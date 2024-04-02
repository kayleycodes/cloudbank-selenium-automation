package cloudBankTestCases;

import cloudBankPages.ClientListPage;
import cloudBankPages.CreateClientPage;
import cloudBankPages.Homepage;
import cloudBankPages.LogInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class ClientCreationTestCase {

    WebDriver driver;

    LogInPage objLogInPage;
    Homepage objHomePage;
    CreateClientPage objCreateClientPage;
    ClientListPage objClientListPage;

    @BeforeTest
    void setUp() {

        //chrome driver setup
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath+ "/src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Kamille\\Chrome For Testing\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("http://localhost:8000/bank/login");

        //login
        String loginUsername = "admin";
        String loginPassword = "password";

        objLogInPage = new LogInPage(driver);
        objLogInPage.verifyHeader();
        objLogInPage.logIn(loginUsername, loginPassword);
    }

    @Test
    void createNewClient(){

        SoftAssert softAssert = new SoftAssert();

        objHomePage = new Homepage(driver);
        objCreateClientPage = new CreateClientPage(driver);
        objClientListPage = new ClientListPage(driver);

        //Navigate to Create Client Page from Dashboard
        assertTrue(objHomePage.welcomeUserTxt.isDisplayed());
        objHomePage.clientsBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(objHomePage.newClientBtn));
        objHomePage.newClientBtn.click();

        //Create New Client
        softAssert.assertTrue(objCreateClientPage.createClientTitle.getText().equals("Create Client"),
                "Create Client Title Not Found");

        //Fill out form
        objCreateClientPage.fillOutForm("Client", "Abc", "123 Philippines", "092712345678", "clientabc@gmail.com");

        //Verify Client successfully created
        assertTrue(objClientListPage.clientListTitle.isDisplayed());
        objClientListPage.verifyClientExists(objCreateClientPage.accountNum);

        softAssert.assertAll();

        driver.quit();

    }
}
